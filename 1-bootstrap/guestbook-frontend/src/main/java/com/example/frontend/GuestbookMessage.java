package com.example.frontend;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GuestbookMessage {

	private Long id;
	
	private String name;
	
	private String message;
	
	private String imageUri;
}

