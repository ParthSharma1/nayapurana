package com.cognizant.dao;



import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Admin;
import com.cognizant.helper.SessionCreator;

@Repository
public class AdminDAOImpl implements AdminDAO {
    

	@Autowired
	private SessionCreator sessionCreator;
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Logger logger=LoggerFactory.getLogger(AdminDAOImpl.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public int getAdminDetails(Admin admin) {
		//Session session=sessionCreator.sessionCreator();
	
		logger.info("-----------GETTING ADMIN LIST DAO-----------------");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from Admin o where o.adminId=:adminId");
		query.setString("adminId", admin.getAdminId());
		Query query1=session.createQuery("from Admin o where o.adminId=:adminId And o.adminPassword=:adminPassword");
		query1.setString("adminId",admin.getAdminId());
		query1.setString("adminPassword",admin.getAdminPassword());
		List<Admin> idList=query.list();
		List<Admin> passwordList=query1.list();
		tx.commit();
		
		if(idList.isEmpty())
			return 1;
		else if(passwordList.isEmpty())
			return 2;
		else
			return 3;
	}


	public boolean registerAdmin(Admin admin) {
		logger.info("-----------REGISTER ADMIN DAO-----------------");
		//Session session=sessionCreator.sessionCreator();
		Session session = sessionFactory.openSession();
		generateAdminId();
		Transaction tx = session.beginTransaction();
		System.out.println(admin.getAdminId());
		session.persist(admin);
		tx.commit();
		return true;
		
	}


	public int checkAdmin(Admin admin) {
		//Session session=sessionCreator.sessionCreator();
		logger.info("-----------CHECKING ADMIN DETAILS DAO-----------------");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from Admin o where o.adminContactNo=:adminContactNo");
		query.setString("adminContactNo", admin.getAdminContactNo());
		Query query1=session.createQuery("from Admin o where o.adminEmailId=:adminEmailId");
		query1.setString("adminEmailId",admin.getAdminEmailId());
		List<Admin> contactList=query.list();
		List<Admin> emailList=query1.list();
		tx.commit();
		if(!contactList.isEmpty()&&emailList.isEmpty())
		{
			logger.info("-----------IF EMAIL EXSISTS DAO-----------------");
			return 1;
		}	
		else if(contactList.isEmpty()&&!emailList.isEmpty())
		{
			logger.info("-----------IF CONTACT NO EXISTS DAO-----------------");
			return 2;
		}	
		else if(!contactList.isEmpty()&&!emailList.isEmpty())
		{
			logger.info("-----------IF CONTACT NO AND EMAIL EXIXSTS DAO-----------------");
			return 3;
		}	
		return 4;
	}
	public void generateAdminId(){
		logger.info("-----------GENERATE ADMINID-----------------");
		Session session = sessionFactory.openSession();
		//Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query = session.createSQLQuery("select ADMINSEQ.nextval FROM DUAL" );
		 Long key = ((BigDecimal) query.uniqueResult()).longValue();
		StoreAdminId.addAdminId(key.intValue());
		tx.commit();
	 
	}  
	

	
}
