package com.example.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Map;

@RefreshScope //curl -XPOST http://localhost:8080/actuator/refresh
@Controller
@SessionAttributes("name")
public class FrontendController {
	@Autowired
	private GuestbookMessagesClient client;
	
	@Value("${greeting:Hello}")
	private String greeting;

	private static Logger log = LoggerFactory.getLogger(FrontendController.class);
	
	@GetMapping("/")
	public String index(Model model) {
		log.info("Method index called ");
		if (model.containsAttribute("name")) {
			String name = (String) model.asMap().get("name");
			model.addAttribute("greeting", String.format("%s %s", greeting, name));
			log.info("greeting param --> " + this.greeting);
		}
		model.addAttribute("messages", client.getMessages());
		return "index";
	}
	
	@PostMapping("/post")
	public String post(@RequestParam String name, @RequestParam String message, Model model) {
		log.info("adding new post now");
		model.addAttribute("name", name);
		if (message != null && !message.trim().isEmpty()) {
			// Post the message to the backend service
			Map<String, String> payload = new HashMap<>();
			payload.put("name", name);
			payload.put("message", message);
			client.add(GuestbookMessage.builder().name(name).message(message).build());
			log.info("Payload received --> " + payload.toString());
		}
		return "redirect:/";
  }
}

