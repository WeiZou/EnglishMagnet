package com.objectiva.englishmagnet.persistence.movieInfro;

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
 * Movieinfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.englishmagnet.movieInfo.Movieinfo
 * @author MyEclipse Persistence Tools
 */
public class MovieinfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MovieinfoDAO.class);
	// property constants
	public static final String MOVIE_NAME = "movieName";
	public static final String TYPE = "type";
	public static final String AGE = "age";
	public static final String REGION = "region";
	public static final String ACTORS = "actors";
	public static final String OTHER = "other";

	public void save(Movieinfo transientInstance) {
		log.debug("saving Movieinfo instance");
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

	public void delete(Movieinfo persistentInstance) {
		log.debug("deleting Movieinfo instance");
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

	public Movieinfo findById(java.lang.Integer id) {
		log.debug("getting Movieinfo instance with id: " + id);
		try {
			Movieinfo instance = (Movieinfo) getSession().get(
					"com.englishmagnet.movieInfo.Movieinfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Movieinfo instance) {
		log.debug("finding Movieinfo instance by example");
		try {
			List results = getSession()
					.createCriteria("com.englishmagnet.movieInfo.Movieinfo")
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
		log.debug("finding Movieinfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Movieinfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMovieName(Object movieName) {
		return findByProperty(MOVIE_NAME, movieName);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List findByRegion(Object region) {
		return findByProperty(REGION, region);
	}

	public List findByActors(Object actors) {
		return findByProperty(ACTORS, actors);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all Movieinfo instances");
		try {
			String queryString = "from Movieinfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Movieinfo merge(Movieinfo detachedInstance) {
		log.debug("merging Movieinfo instance");
		try {
			Movieinfo result = (Movieinfo) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Movieinfo instance) {
		log.debug("attaching dirty Movieinfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}