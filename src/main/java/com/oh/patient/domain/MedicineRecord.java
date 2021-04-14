package com.oh.patient.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicineRecord extends BaseEntity{
	private String medicine;
	private float amount;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date takenTime;
	
	private String pid;
	
	public MedicineRecord(String id, String medicine, float amount, Date takenTime,
			String pid) throws ParseException {
		super(id);
		this.medicine = medicine;
		this.amount = amount;
		this.takenTime = takenTime;
		this.pid = pid;
	}
	
	public MedicineRecord() {}
	
	public String getMedicine() {
		return medicine;
	}


	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}


	public Date getTakenTime() {
		return takenTime;
	}


	public void setTakenTime(Date takenTime) throws ParseException {
		this.takenTime = takenTime;
	}


	public String getPId() {
		return pid;
	}


	public void setPatientId(String pid) {
		this.pid = pid;
	}


	public MedicineRecord(String id) {
		super(id);
	}
	

}
