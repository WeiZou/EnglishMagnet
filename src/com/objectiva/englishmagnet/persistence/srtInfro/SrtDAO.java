package com.objectiva.englishmagnet.persistence.srtInfro;

import com.objectiva.englishmagnet.persistence.reference.*;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Srt
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.englishmagnet.srt.Srt
 * @author MyEclipse Persistence Tools
 */
public class SrtDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SrtDAO.class);
	// property constants
	public static final String SENE = "sene";
	public static final String SENC = "senc";
	public static final String END = "end";

	public void save(Srt transientInstance) {
		log.debug("saving Srt instance");
		try {
			Session session;
			Transaction tx;
			session = getSession();
			tx = session.beginTransaction();
			session.save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Srt persistentInstance) {
		log.debug("deleting Srt instance");
		try {
			Session session;
			Transaction tx;
			session = getSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Srt findById(
			com.objectiva.englishmagnet.persistence.srtInfro.SrtId id) {
		log.debug("getting Srt instance with id: " + id);
		try {
			Srt instance = (Srt) getSession().get(
					"com.objectiva.englishmagnet.persistence.srtInfro.Srt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Srt instance) {
		log.debug("finding Srt instance by example");
		try {
			List results = getSession()
					.createCriteria("com.englishmagnet.srt.Srt")
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
		log.debug("finding Srt instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Srt as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySene(Object sene) {
		return findByProperty(SENE, sene);
	}

	public List findBySenc(Object senc) {
		return findByProperty(SENC, senc);
	}

	public List findByEnd(Object end) {
		return findByProperty(END, end);
	}

	public List findAll() {
		log.debug("finding all Srt instances");
		try {
			String queryString = "from Srt";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Srt merge(Srt detachedInstance) {
		log.debug("merging Srt instance");
		try {
			Srt result = (Srt) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Srt instance) {
		log.debug("attaching dirty Srt instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}