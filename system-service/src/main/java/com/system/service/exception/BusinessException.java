package com.system.service.exception;

import com.system.service.exception.code.StatusCodes;

/**
 * @author 未知
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private StatusCodes statusCodes;

	public StatusCodes getStatusCodes() {
		return statusCodes;
	}

	public void setStatusCodes(StatusCodes statusCodes) {
		this.statusCodes = statusCodes;
	}

	public BusinessException(StatusCodes statusCodes){
		this.statusCodes = statusCodes;
	}
	public BusinessException(StatusCodes statusCodes, String message){
		super(message);
		this.statusCodes = statusCodes;
	}

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause)
	{
		super(cause);
	}
	
	public BusinessException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
