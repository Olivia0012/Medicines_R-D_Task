package com.oh.patient.domain;

import java.util.List;

public class Patient extends BaseEntity{
	private List<MedicineRecord> medicines;

	public Patient(String id) {
		super(id);
	}

	public List<MedicineRecord> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicineRecord> medicines) {
		this.medicines = medicines;
	}

}
