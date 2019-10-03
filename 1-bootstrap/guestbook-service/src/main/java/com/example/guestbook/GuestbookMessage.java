package com.example.guestbook;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "guestbook_message")
@Data
public class GuestbookMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String message;
	
	private String imageUri;
}

