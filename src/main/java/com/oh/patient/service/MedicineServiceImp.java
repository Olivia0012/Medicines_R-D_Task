package com.oh.patient.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oh.patient.domain.MedicineRecord;
//import com.oh.patient.domain.Patient;
import com.oh.patient.manpper.MedicineMapper;
//import com.oh.patient.manpper.PatientMapper;

@Service
public class MedicineServiceImp implements MedicineService{
	@Autowired
	private MedicineMapper medicineMapper;
	
//	@Autowired
//	private PatientMapper pm;

	@Override
	public boolean addMedicienRecord(MedicineRecord medicineRecord) {
		try {
		// test the foreign key (patient id)
		//	Patient p = new Patient("2", "Tom");
		//	pm.insert(p);
			
			
			medicineMapper.insert(medicineRecord);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<MedicineRecord> findMedicineByPId_Date(String pId, Date date) {
		
		Date pastWeek = date;
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(pastWeek);
	    calendar.add(Calendar.DATE, -7);
	    Date a = calendar.getTime();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, +1);
		return medicineMapper.findByPatientIdandDate(pId, calendar.getTime(), a);
	}

}
