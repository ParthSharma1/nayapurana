package com.cognizant.dao;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.Medicine;
import com.cognizant.helper.SessionCreator;
@Repository
public class MedicineDAOImpl implements MedicineDAO {
	
	
	@Autowired
	SessionCreator sessionCreator;

	private static Logger logger=LoggerFactory.getLogger(AdminDAOImpl.class);
	
	public List<Medicine> getAllMedicines() {
		logger.info("-----------GETTING ALL MEDICINE DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		
		Query query=session.createQuery("from Medicine");
		List<Medicine> medList=query.list();
		
		tx.commit();
		return medList;
	}

	public int addMedicine(Medicine medicine) {
		logger.info("-----------REGISTER MEDICINE DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		generateMedicineId();
		Transaction tx=session.beginTransaction();
		
		session.persist(medicine);
		
		tx.commit();
		return 0;
	}
	

	public boolean updateMedicine(Medicine medicine) {
		// TODO Auto-generated method stub
		Session session=sessionCreator.sessionCreator();
		logger.info("-----------UPDATE MEDICINE DAO-----------------");
		Transaction tx=session.beginTransaction();
		session.merge(medicine);
		boolean res=true;
		tx.commit();
		
		return res;
		
	}

	public Medicine retrieveMedicine(int medicineId) {
		// TODO Auto-generated method stub
		logger.info("-----------RETRIEVE MEDICINE OBJECT DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Medicine medicine=(Medicine) session.load(Medicine.class, medicineId);
		tx.commit();
		

		return medicine;
		
		
	}


	public List<Medicine> getStockList() {
		// TODO Auto-generated method stub
	logger.info("-----------GETTING MEDICINE STOCK LIST DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query=session.createQuery("from Medicine");
		List<Medicine> stockList=query.list();
		tx.commit();
		return stockList;
			}


	public boolean updateMedicineQuantity(Medicine medicine) {
	     logger.info("-----------UPDATE MEDICINE QUANTITY DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx=session.beginTransaction();
			
		session.merge(medicine);
		
		boolean res=true;
		tx.commit();
		return res;
	}
	public void generateMedicineId(){
		logger.info("-----------GENRATE MEDICINE ID DAO-----------------");
		Session session=sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		 Query query = session.createSQLQuery("select MEDICINESEQ.nextval FROM DUAL" );
		 Long key = ((BigDecimal) query.uniqueResult()).longValue();
		 
		 StoreMedicineId.addMedicineId(key.intValue());
		 //System.out.println(getMedicineId());
			/*if(medicine.getMedicineId()==0)
			{
				sessionFactory.close();
				return 2;
			}*/
		 tx.commit();
	}  

	

}
