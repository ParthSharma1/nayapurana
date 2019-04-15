package com.cognizant.dao;

import java.util.Hashtable;
import java.util.List;

import com.cognizant.entity.MedicineRequest;

public interface MedicineRequestDAO {
	boolean insertMedicineRequest(MedicineRequest medicineRequest);
    List<MedicineRequest> getMedicineRequests();
    boolean checkMedicineRequest(Hashtable<Integer,Integer> hm);
    boolean updateBranchAdminRequest(MedicineRequest medicineRequest);
    MedicineRequest fetchMedicineRequestInfo(int requestId);
    List<String> getBranchAdminIds();
    List<Integer> getMedicineIds();
    int getRequestPendingCount();
   	List<MedicineRequest> getApprovedRequests();
   	List<MedicineRequest> getRejectedRequests();
}
