package com.example.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//http://localhost:8081/guestbookMessages
@RestController
@RequestMapping(path = "guestbookMessages")
public class GuestbookMessageController {

    @Autowired
    private GuestbookMessageRepository repository;

    @GetMapping
    Iterable<GuestbookMessage> getMessages() {
        return this.repository.findAll();
    }

    @GetMapping(path = "/{id}")
    Optional<GuestbookMessage> getMessage(@PathVariable("id") long messageId) {
        return this.repository.findById(messageId);
    }

    @PostMapping
    void add(@RequestBody  GuestbookMessage guestbookMessage) {
        this.repository.save(guestbookMessage);
    }

}
