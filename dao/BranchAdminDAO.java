package com.cognizant.dao;

import java.util.List;

import com.cognizant.entity.BranchAdmin;

public interface BranchAdminDAO {
 
	boolean insertBranchAdmin(BranchAdmin branchAdmin);
    List<BranchAdmin> getBranchAdmin();
    int checkBranchAdmin(BranchAdmin branchAdmin);
    boolean updateBranchAdmin(BranchAdmin branchAdmin);
    BranchAdmin fetchBranchAdminInfo(String branchAdminId);
}
