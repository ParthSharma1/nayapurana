package com.cognizant.dao;

import com.cognizant.entity.Admin;

public interface AdminDAO {
int getAdminDetails(Admin admin);
boolean registerAdmin(Admin admin);
int checkAdmin(Admin admin);
}
