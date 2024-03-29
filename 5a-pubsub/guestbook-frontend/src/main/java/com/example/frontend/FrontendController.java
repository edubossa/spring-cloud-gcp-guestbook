package com.example.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import org.springframework.cloud.gcp.pubsub.core.*;

@Controller
@SessionAttributes("name")
public class FrontendController {
	@Autowired
	private GuestbookMessagesClient client;

	@Autowired
	private PubSubTemplate pubSubTemplate;
	
	@Value("${greeting:Hello}")
	private String greeting;

	@GetMapping("/")
	public String index(Model model) {
		if (model.containsAttribute("name")) {
			String name = (String) model.asMap().get("name");
			model.addAttribute("greeting", String.format("%s %s", greeting, name));
		}
		model.addAttribute("messages", client.getMessages().getContent());
		return "index";
	}
	
	@PostMapping("/post")
	public String post(@RequestParam String name, @RequestParam String message, Model model) {
		model.addAttribute("name", name);
		if (message != null && !message.trim().isEmpty()) {
			// Post the message to the backend service
			Map<String, String> payload = new HashMap<>();
			payload.put("name", name);
			payload.put("message", message);
			client.add(payload);

			final GuestbookMessage guestbook = GuestbookMessage.builder().name(name).message(message).build();
			Map<String, String> headers = new HashMap<>();
			headers.put("KEY-01", UUID.randomUUID().toString());
			headers.put("KEY-02", UUID.randomUUID().toString());
			headers.put("KEY-03", UUID.randomUUID().toString());
			headers.put("KEY-04", UUID.randomUUID().toString());
			headers.put("KEY-05", UUID.randomUUID().toString());
			headers.put("KEY-06", UUID.randomUUID().toString());
			pubSubTemplate.publish("messages", guestbook.toString(), headers);
		}
		return "redirect:/";
  }
}

