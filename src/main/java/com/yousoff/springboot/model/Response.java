package com.yousoff.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Response {
	
	private String message;
	private String status;
	private Object data;
	
}
