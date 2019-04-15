package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

public class StoreMedicineRequestId {

	static List<Integer> idList=new ArrayList<Integer>();

	public static void addMedicineRequestId(int id){
		idList.add(0,id); 
	}
	
	public static int getMedicineRequestId(){
		return idList.get(0);
	}
}
