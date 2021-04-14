package com.oh.patient.domain;

import java.util.UUID;

public abstract class BaseEntity {
	private String id;
	
	public BaseEntity(String id) {
		this.id = id;
	}

	public BaseEntity() {
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean AutoFill() {
		if(this.getId() == null) {
			this.setId(UUID.randomUUID().toString());
		}
		return true;
	}

}
