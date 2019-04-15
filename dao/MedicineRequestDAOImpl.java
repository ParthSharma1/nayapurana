package com.cognizant.dao;

import java.math.BigDecimal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.entity.BranchAdmin;
import com.cognizant.entity.Medicine;
import com.cognizant.entity.MedicineRequest;
import com.cognizant.helper.SessionCreator;

import oracle.net.aso.q;

@Repository
/**
 * 
 * @author 768878
 *
 */
public class MedicineRequestDAOImpl implements MedicineRequestDAO {

	@Autowired
	SessionCreator sessionCreator;
        private static Logger logger=LoggerFactory.getLogger(AdminDAOImpl.class);
	public boolean insertMedicineRequest(MedicineRequest medicineRequest) {
                
		// TODO Auto-generated method stub
                 logger.info("----------INSERT MEDICINE REQUEST IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		generateMedicineRequestId();
		Transaction tx = session.beginTransaction();
		session.persist(medicineRequest);
		tx.commit();

		return true;
	}

	public List<MedicineRequest> getMedicineRequests() {
		// TODO Auto-generated method stub
		logger.info("----------GETTING ALL REQUEST IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Query query = session.createQuery("from MedicineRequest o where o.adminResponse='P'");
		List<MedicineRequest> medicineRequestList = query.list();

		return medicineRequestList;
	}

	public boolean checkMedicineRequest(Hashtable<Integer, Integer> hm) {
             logger.info("----------CHECKING MEDICINE REQUEST IN MEDICINEREQUESTDAO---------------");
		boolean quantityCheck = true;
		Session session = sessionCreator.sessionCreator();
		for (Map.Entry map : hm.entrySet()) {

			Query query = session.createQuery("from Medicine o where o.medicineId=:medicineId");
			query.setInteger("medicineId", (Integer) map.getKey());
			List<Medicine> medicineList = query.list();
			for (Medicine medicine : medicineList) {
				if (medicine.getQuantity() < Integer.parseInt("" + map.getValue())) {
					quantityCheck = false;
					return quantityCheck;
				}
			}

		}

		return quantityCheck;

	}

	public boolean updateBranchAdminRequest(MedicineRequest medicineRequest) {
		// TODO Auto-generated method stub
		logger.info("----------UPDATE MEDICINE REQUEST IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		session.merge(medicineRequest);
		tx.commit();

		return true;
	}

	public MedicineRequest fetchMedicineRequestInfo(int requestId) {
		// TODO Auto-generated method stub
		logger.info("----------GETTING MEDICINE REQUEST ID IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		MedicineRequest medicineRequest = (MedicineRequest) session.load(MedicineRequest.class, requestId);

		return medicineRequest;
	}

	public void generateMedicineRequestId() {
		logger.info("----------GETTING MEDICINE REQUEST ID IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Query query = session.createSQLQuery("select MedicineRequestId.nextval FROM DUAL");
		Long key = ((BigDecimal) query.uniqueResult()).longValue();
		StoreMedicineRequestId.addMedicineRequestId(key.intValue());

	}

	public List<String> getBranchAdminIds() {
		// TODO Auto-generated method stub
		logger.info("----------GETTING BRANCHADMINS IDS LIST IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Query query = session.createQuery("Select o.branchAdminId from BranchAdmin o");
		List<String> medicineIdList = query.list();

		return medicineIdList;

	}

	public List<Integer> getMedicineIds() {
		// TODO Auto-generated method stub
		logger.info("----------GETTING MEDICINES IDS IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Query query = session.createQuery("Select o.medicineId from Medicine o");
		List<Integer> medicineIdList = query.list();
		return medicineIdList;
	}

	public int getRequestPendingCount() {
		// TODO Auto-generated method stub
		logger.info("----------GETTING PENDING MEDICINE MEDICINE REQUEST IN MEDICINEREQUESTDAO---------------");
		Session session = sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select count(o.requestId) from MedicineRequest o where o.adminResponse='P'");
		Long count = (Long) query.uniqueResult();
		tx.commit();
		return count.intValue();
	}

	public List<MedicineRequest> getApprovedRequests() {
		// TODO Auto-generated method stub
		
		Session session = sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from MedicineRequest o where o.adminResponse='A'");
		List<MedicineRequest> medicineRequestList = query.list();
		tx.commit();
		return medicineRequestList;
	}

	@Override
	public List<MedicineRequest> getRejectedRequests() {
		// TODO Auto-generated method stub
		Session session = sessionCreator.sessionCreator();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from MedicineRequest o where o.adminResponse='R'");
		List<MedicineRequest> medicineRequestList = query.list();
		tx.commit();
		return medicineRequestList;
	}

}
