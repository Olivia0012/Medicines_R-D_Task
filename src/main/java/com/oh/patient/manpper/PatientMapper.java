package com.oh.patient.manpper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.oh.patient.domain.Patient;

@Mapper
public interface PatientMapper {
	
	@Insert("insert into patient(pid,name) values (#{id},#{name})")
	public String insert(Patient obj);

}
