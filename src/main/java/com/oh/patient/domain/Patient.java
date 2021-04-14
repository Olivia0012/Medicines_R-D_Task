package com.oh.patient.domain;

import java.util.List;

public class Patient extends BaseEntity{
	private String name;
	private List<MedicineRecord> medicines;

	public Patient(String id, String name) {
		super(id);
		this.setName(name);
	}

	public List<MedicineRecord> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicineRecord> medicines) {
		this.medicines = medicines;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
