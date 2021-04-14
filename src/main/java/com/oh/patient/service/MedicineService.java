package com.oh.patient.service;

import java.util.Date;
import java.util.List;

import com.oh.patient.domain.MedicineRecord;

public interface MedicineService {
	boolean addMedicienRecord(MedicineRecord medicineRecord);
	List<MedicineRecord> findMedicineByPId_Date(String pId, Date Date);

}
