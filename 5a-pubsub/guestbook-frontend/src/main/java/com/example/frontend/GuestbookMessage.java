package com.example.frontend;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class GuestbookMessage implements Serializable {

	private Long id;
	
	private String name;
	
	private String message;
	
	private String imageUri;
}

