package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

public class StoreBranchAdminId {
	static List<Integer> idList=new ArrayList<Integer>();

	public static void addBranchAdminId(int id){
		idList.add(0,id); 
	}
	
	public static int getBranchAdminId(){
		return idList.get(0);
	}
}
