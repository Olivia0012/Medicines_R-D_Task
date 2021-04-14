package com.oh.patient.domain;

import java.util.List;

public class ResponseEntity<E extends BaseEntity> {
	private Boolean success;
	private List<E> data;
	private int code;
	private String message;
	
	public ResponseEntity() {}
	
	public ResponseEntity(Boolean success, List<E> data, int code, String message) {
		super();
		this.success = success;
		this.data = data;
		this.code = code;
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
