package com.example.guestbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//http://localhost:8081/guestbookMessages
@RestController
@RequestMapping(path = "guestbookMessages")
public class GuestbookMessageController {

    private final static Logger log = LoggerFactory.getLogger(GuestbookMessageController.class);

    @Autowired
    private GuestbookMessageRepository repository;

    @GetMapping
    Iterable<GuestbookMessage> getMessages() {
        log.debug("GuestbookMessageController.getMessages");
        final Iterable<GuestbookMessage> messages = this.repository.findAll();
        log.info(messages.toString());
        return messages;
    }

    @GetMapping(path = "/{id}")
    Optional<GuestbookMessage> getMessage(@PathVariable("id") long messageId) {
        log.debug("GuestbookMessageController.getMessage/" + messageId);
        Optional<GuestbookMessage> message = this.repository.findById(messageId);
        log.info(message.toString());
        return message;
    }

    @PostMapping
    GuestbookMessage add(@RequestBody  GuestbookMessage guestbookMessage) {
        log.debug("GuestbookMessageController.add");
        GuestbookMessage save = this.repository.save(guestbookMessage);
        log.info(save.toString());
        return save;
    }

}
