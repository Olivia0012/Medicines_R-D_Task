package com.oh.patient.manpper;

import java.util.Date;
import java.util.List;
import com.oh.patient.domain.MedicineRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MedicineMapper {
	/**
	 * 
	 * @param patientId
	 * @param period
	 * @return
	 */
	@Select("SELECT * FROM medicine_records WHERE pid = #{patientId} and (takentime BETWEEN SYMMETRIC #{pastWeek} AND #{date}) ")
	public List<MedicineRecord> findByPatientIdandDate(String patientId, Date date, Date pastWeek);

	@Insert("insert into medicine_records(mrid,medicine,amount,pid,takentime) values (#{id},#{medicine},#{amount},#{pid},#{takenTime})")
	public Boolean insert(MedicineRecord obj);

	public Boolean update(MedicineRecord obj);

	@Delete(value = { "" })
	public Boolean delete(MedicineRecord obj);

	public MedicineRecord findById(String id);

}
