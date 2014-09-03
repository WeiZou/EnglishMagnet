package com.objectiva.englishmagnet.persistence.collectionInfro;

import com.objectiva.englishmagnet.persistence.reference.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Collection entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.englishmagnet.collection.Collection
 * @author MyEclipse Persistence Tools
 */
public class CollectionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CollectionDAO.class);
	// property constants
	public static final String COLLECTION_ID = "collectionId";
	public static final String USER_ID = "userId";
	public static final String MOVE_ID = "moveId";
	public static final String START = "start";
	public static final String WORD = "word";

	public void save(Collection transientInstance) {
		log.debug("saving Collection instance");
		try {
			Session session;
			Transaction tx;
			session=getSession();
			tx=session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Collection persistentInstance) {
		log.debug("deleting Collection instance");
		try {
			Session session;
			Transaction tx;
			session=HibernateSessionFactory.getSession();
			tx=session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Collection findById(java.lang.Integer id) {
		log.debug("getting Collection instance with id: " + id);
		try {
			Collection instance = (Collection) getSession().get(
					"com.englishmagnet.collection.Collection", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Collection instance) {
		log.debug("finding Collection instance by example");
		try {
			List results = getSession()
					.createCriteria("com.englishmagnet.collection.Collection")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Collection instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Collection as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByMoveId(Object moveId) {
		return findByProperty(MOVE_ID, moveId);
	}

	public List findByStart(Object start) {
		return findByProperty(START, start);
	}

	public List findByWord(Object word) {
		return findByProperty(WORD, word);
	}
	
	public List findByCollection(Object collectionId){
		return findByProperty(COLLECTION_ID, collectionId);
	}

	public List findAll() {
		log.debug("finding all Collection instances");
		try {
			String queryString = "from Collection";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Collection merge(Collection detachedInstance) {
		log.debug("merging Collection instance");
		try {
			Collection result = (Collection) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Collection instance) {
		log.debug("attaching dirty Collection instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}