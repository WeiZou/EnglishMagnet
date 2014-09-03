package com.objectiva.englishmagnet.persistence.wordBook;

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
 * WordBook entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.objectiva.englishMagnet.persistance.word_book.WordBook
 * @author MyEclipse Persistence Tools
 */
public class WordBookDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WordBookDAO.class);
	public static final String USER_ID = "userId";
	public static final String WORD = "word";

	public void save(WordBook transientInstance) {
		log.debug("saving WordBook instance");
		try {
			Transaction thx;
			Session session = getSession();
			thx = session.beginTransaction();
			session.save(transientInstance);
			thx.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(WordBook persistentInstance) {
		log.debug("deleting WordBook instance");
		try {
			Transaction thx;
			Session session = getSession();
			thx = session.beginTransaction();
			session.delete(persistentInstance);
			thx.commit();
			// getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public WordBook findById(java.lang.Integer id) {
		log.debug("getting WordBook instance with id: " + id);
		try {
			WordBook instance = (WordBook) getSession()
					.get("com.objectiva.englishMagnet.persistance.word_book.WordBook",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(WordBook instance) {
		log.debug("finding WordBook instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.objectiva.englishMagnet.persistance.word_book.WordBook")
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
		log.debug("finding WordBook instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from WordBook as model where model."
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

	public List findByWord(Object word) {
		return findByProperty(WORD, word);
	}

	public List findAll() {
		log.debug("finding all WordBook instances");
		try {
			String queryString = "from WordBook";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public WordBook merge(WordBook detachedInstance) {
		log.debug("merging WordBook instance");
		try {
			WordBook result = (WordBook) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(WordBook instance) {
		log.debug("attaching dirty WordBook instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}