package com.cognizant.dao;

import java.util.ArrayList;
import java.util.List;

public class StoreAdminId {
	static List<Integer> idList=new ArrayList<Integer>();

	public static void addAdminId(int id){
		idList.add(0,id); 
	}
	
	public static int getAdminId(){
			if(idList.isEmpty()){
				return 5;
			}
		
		return idList.get(0);
	}
	

}
