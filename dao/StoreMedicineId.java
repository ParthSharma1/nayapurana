package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

public class StoreMedicineId {
	static List<Integer> idList=new ArrayList<Integer>();

	public static void addMedicineId(int id){
		idList.add(0,id); 
	}
	
	public static int getMedicineId(){
		return idList.get(0);
	}
}
