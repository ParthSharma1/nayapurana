package com.cognizant.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class MedicineRequestIdGenerator extends SequenceStyleGenerator{
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
	  

	     return StoreMedicineRequestId.getMedicineRequestId();
}
}