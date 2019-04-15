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
import com.cognizant.entity.BranchAdmin;
import com.cognizant.helper.SessionCreator;

@Repository
public class BranchAdminDAOImpl implements BranchAdminDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private SessionCreator sessionCreator;
	
	private static Logger logger=LoggerFactory.getLogger(AdminDAOImpl.class);
	public boolean insertBranchAdmin(BranchAdmin branchAdmin) {
		// TODO Auto-generated method stub
		logger.info("-----------REGISTER BRANCHADMIN DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		generateBranchAdminId();
		Transaction tx = session.beginTransaction();
		session.persist(branchAdmin);
		tx.commit();
	
		return true;
	}

	public List<BranchAdmin> getBranchAdmin() {
		// TODO Auto-generated method stub
		logger.info("-----------GETTING BRANCHADMIN LIST DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from BranchAdmin");
		List<BranchAdmin> branchAdminList=query.list();
		tx.commit();
		return branchAdminList;
	}

	public int checkBranchAdmin(BranchAdmin branchAdmin) {
		// TODO Auto-generated method stub
		logger.info("-----------CHECKING BRANCHADMIN EMAIL AND CONTACT  DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from BranchAdmin o where o.branchAdminContactNo=:branchAdminContactNo");
		query.setString("branchAdminContactNo",branchAdmin.getBranchAdminContactNo());
		Query query1=session.createQuery("from BranchAdmin o where o.branchAdminEmailId=:branchAdminEmailId");
		query1.setString("branchAdminEmailId",branchAdmin.getBranchAdminEmailId());
		List<BranchAdmin> contactList=query.list();
		List<BranchAdmin> emailList=query1.list();
		tx.commit();
		if(!contactList.isEmpty()&&emailList.isEmpty())
		{
			logger.info("-----------IF BRANCHADMIN EMAIL EXIXSTS DAO-----------------");
			return 1;
		}	
		else if(contactList.isEmpty()&&!emailList.isEmpty())
		{
			logger.info("-----------IF BRANCHADMIN CONTACT EXISTS DAO-----------------");
			return 2;
		}	
		else if(!contactList.isEmpty()&&!emailList.isEmpty())
		{
			logger.info("-----------BOTH CONTACT AND EMAIL BRANCHADMIN DAO-----------------");
			return 3;
		}	
		logger.info("-----------BOTH CONTACT AND EMAIL DOESNOT BRANCHADMIN DAO-----------------");
		return 4;
	}

	public boolean updateBranchAdmin(BranchAdmin branchAdmin) {
		logger.info("-----------UPDATE BRANCHADMIN DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		
		session.merge(branchAdmin);
		tx.commit();
		
		return true;
	}

	public BranchAdmin fetchBranchAdminInfo(String branchAdminId) {
		// TODO Auto-generated method stub
		logger.info("-----------GETTING  BRANCHADMIN OBJECT DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		BranchAdmin branchAdmin=(BranchAdmin) session.load(BranchAdmin.class, branchAdminId);
		tx.commit();
		return branchAdmin;

	}
	public void generateBranchAdminId(){

		logger.info("-----------BRANCHADMIN ID GENRATE  DAO-----------------");
		
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		 Query query = session.createSQLQuery("select BRANCHADMINSEQ.nextval FROM DUAL" );
		 Long key = ((BigDecimal) query.uniqueResult()).longValue();
		 StoreBranchAdminId.addBranchAdminId(key.intValue());
		 tx.commit();
	  
	}  

}
