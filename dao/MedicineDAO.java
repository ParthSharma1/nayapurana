package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.entity.Medicine;

public interface MedicineDAO {
	List<Medicine> getAllMedicines();
	int addMedicine(Medicine medicine);
	boolean updateMedicine(Medicine medicine);
	Medicine retrieveMedicine(int medId);
	List<Medicine> getStockList();
	boolean updateMedicineQuantity(Medicine medicine);
	
	
}
