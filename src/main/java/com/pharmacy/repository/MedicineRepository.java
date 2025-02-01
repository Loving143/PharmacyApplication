package com.pharmacy.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharmacy.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine,Integer>{

	boolean existsByMedicineCodeAndBatchNo(String medicineCode, String batchNo);

	@Query("Select med from Medicine med"
			+ " where med.medicineCode =:medicineCode "
			+ " AND med.batchNo=:batchNo ")
	Medicine fetchMedicineByMedicneCodeAndBatchNo(String medicineCode, String batchNo);

	boolean existsByMedicineCodeAndExpiryDate(String medicineCode, Date expiryDate);

}
