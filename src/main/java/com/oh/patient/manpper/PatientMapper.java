package com.oh.patient.manpper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.oh.patient.domain.Patient;

@Mapper
public interface PatientMapper {
	
	@Insert("insert into patient(pid,name) values (#{pid},#{medicine},#{name})")
	public String insert(Patient obj);

}
