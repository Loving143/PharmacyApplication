package com.pharmacy.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pharmacy.entity.Medicine;
import com.pharmacy.entity.Subcategory;
import com.pharmacy.response.LowStockMedicineResponsible;

public interface MedicineRepository extends JpaRepository<Medicine,Integer>{

//	boolean existsByMedicineCodeAndBatchNo(String medicineCode, String batchNo);
//
//	@Query("Select med from Medicine med"
//			+ " where med.medicineCode =:medicineCode "
//			+ " AND med.batchNo=:batchNo ")
//	Medicine fetchMedicineByMedicneCodeAndBatchNo(String medicineCode, String batchNo);
//
//	boolean existsByMedicineCodeAndExpiryDate(String medicineCode, Date expiryDate);
//
//	@Query("  Select med.medicineName as medicineName,"
//			+ "med.medicineCode as medicineCode,"
//			+ "med.batchNo as batchNo, "
//			+ "med.description as description ,"
//			+ "med.brandName as brandName, "
//			+ "med.price as price,"
//			+ "med.expiryDate as expiryDate,"
//			+ "med.stockQuantity as stockQuantity "
//			+ "from Medicine med "
//			+ "where med.medicineName in "
//			+ "(Select medSub.medicineName from Medicine medSub "
//			+ "group By medSub.medicineName "
//			+ "having count(medSub.batchNo)=1 )"
//			+ "AND med.stockQuantity<:threshhold"
//			)
//	List<LowStockMedicineResponsible> fetchLowStockMedicine(Integer threshhold);
//
//	@Query(" Select "
//			+ "med.medicineName as medicineName,"
//			+ "med.medicineCode as medicineCode,"
//			+ "med.batchNo as batchNo, "
//			+ "med.description as description ,"
//			+ "med.brandName as brandName, "
//			+ "med.expiryDate as expiryDate,"
//			+ "med.price as price,"
//			+ "med.stockQuantity as stockQuantity "
//			+ "from Medicine med "
//			+ "where med.medicineCode =:medicineCode "
//			+ "AND med.batchNo =:batchNo"
//			)
//	Optional<LowStockMedicineResponsible> fetchMedicineByMedicineCodeAndBatchNo(String medicineCode, String batchNo);
//
//	@Query(" Select "
//			+ "med.medicineName as medicineName,"
//			+ "med.medicineCode as medicineCode,"
//			+ "med.batchNo as batchNo, "
//			+ "med.description as description ,"
//			+ "med.brandName as brandName, "
//			+ "med.expiryDate as expiryDate,"
//			+ "med.price as price,"
//			+ "med.stockQuantity as stockQuantity "
//			+ "from Medicine med "
//			+ "where med.expiryDate<:date "
//			)
//	Optional<LowStockMedicineResponsible> fetchExpiredMedicine(Date date);
//
//	@Query(" Select "
//			+ "med.medicineName as medicineName,"
//			+ "med.medicineCode as medicineCode,"
//			+ "med.batchNo as batchNo, "
//			+ "med.description as description ,"
//			+ "med.brandName as brandName, "
//			+ "med.expiryDate as expiryDate,"
//			+ "med.price as price,"
//			+ "med.stockQuantity as stockQuantity "
//			+ "from Medicine med "
//			)
//	List<LowStockMedicineResponsible> fetchAllMedicine();

}
