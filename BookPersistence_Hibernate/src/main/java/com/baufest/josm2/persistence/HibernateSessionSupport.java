package com.baufest.josm2.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Hibernate session support
 * @author scortes
 *
 */
public class HibernateSessionSupport {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
	}
	
	protected SessionFactory getSessionFactory() {
        return sessionFactory;
	}

    /**
     * Get the current Hibernate session
     */
    protected Session getSession() {
            return sessionFactory.getCurrentSession();
    }
	
}
