package com.ayush.exception;

public class ResourceNotFoundException  extends RuntimeException{
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
