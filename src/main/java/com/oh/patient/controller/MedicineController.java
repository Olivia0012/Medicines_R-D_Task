package com.oh.patient.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.oh.patient.domain.MedicineRecord;
import com.oh.patient.domain.Patient;
import com.oh.patient.manpper.PatientMapper;
import com.oh.patient.service.MedicineService;

/**
 * 
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
    private MedicineService medicineService;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<MedicineRecord> findMedicineByPId_Date(@RequestParam(value = "pid", required = true) String pid,@RequestParam(value = "date", required = true) String date, HttpServletRequest request) throws ParseException{
        Date curDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
       
		int size = medicineService.findMedicineByPId_Date(pid, curDate).size();
		if (size > 0) {
			return medicineService.findMedicineByPId_Date(pid, curDate);
		}else {
			return null;
		}
        	
		
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public boolean addMedicineRecord(@RequestBody MedicineRecord medicineRecord) {
		medicineRecord.AutoFill();
		
		return medicineService.addMedicienRecord(medicineRecord);
    }
}
