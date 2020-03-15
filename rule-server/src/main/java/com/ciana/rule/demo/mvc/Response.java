package com.ciana.rule.demo.mvc;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一消息回复
 * 
 * @author yinbo
 * @date: 2020/03/14 19:46
 */
@Data
public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private String extMessage;
	private Object data;
	private Response.Status status;

	public Response() {
		this.status = Response.Status.SUCCEED;
	}

	public static Response success() {
		return new Response();
	}

	public static Response success(Object result) {
		Response response = new Response();
		response.setData(result);
		return response;
	}

	public static Response failure(String errorCode, String errorMessage) {
		Response response = new Response();
		response.errorCode = errorCode;
		response.errorMessage = errorMessage;
		response.status = Status.FAILED;
		return response;
	}

	public static Response failure(String message) {
		Response response = new Response();
		response.setErrorMessage(message);
		response.status = Status.FAILED;
		return response;
	}

	public static Response warring(Object result) {
		Response response = new Response();
		response.setData(result);
		response.status = Status.WARRING;
		return response;
	}

	public enum Status {
		/**
		 * 状态
		 */
		SUCCEED, WARRING, FAILED;

		Status() {
		}
	}
}
