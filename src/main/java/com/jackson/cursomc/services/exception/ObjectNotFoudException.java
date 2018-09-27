package com.jackson.cursomc.services.exception;

public class ObjectNotFoudException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoudException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoudException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
