package com.objectiva.englishmagnet.persistence.reference;

import org.hibernate.Session;

/**
 * Data access interface for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();
}