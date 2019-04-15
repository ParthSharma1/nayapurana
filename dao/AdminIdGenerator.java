package com.cognizant.dao;

import java.io.Serializable;
import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;




public class AdminIdGenerator extends SequenceStyleGenerator {
  public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
	

	     return "admin"+StoreAdminId.getAdminId();
	}
	
}
