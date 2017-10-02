package com.baufest.josm2.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;

public class GenericDaoImpl<T, ID extends Serializable> extends
		HibernateSessionSupport implements GenericDao<T, ID> {

	@SuppressWarnings("unchecked")
	protected Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	@SuppressWarnings("unchecked")
	public ID save(T object) {
		return (ID) getSession().save(object);
	}

	public void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	public void delete(T object) {
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	public T getById(ID id) {
		return (T) getSession().get(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll() {
		return getSession().createCriteria(persistentClass)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	public void update(T object) {
		getSession().update(object);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(DetachedCriteria criteria) {
		List<T> results = (List<T>) criteria
				.getExecutableCriteria(getSession()).list();
		return results;
	}

	@SuppressWarnings("unchecked")
	public PageResult<T> findByPagedCriteria(Criteria crit,
			PagedQuery pagedQuery) {
		Long totalCount = (Long) crit.setProjection(Projections.rowCount())
				.uniqueResult();
		if (pagedQuery != null) {
			crit.setMaxResults(pagedQuery.getPageSize());
			crit.setFirstResult(pagedQuery.getOffset().intValue());
			if (pagedQuery.getSortFieldName() != null
					&& !pagedQuery.getSortFieldName().equals("")) {
				if (pagedQuery != null
						&& pagedQuery.getSortMode() == PagedQuery.SortMode.ASC) {
					crit.addOrder(Order.asc(pagedQuery.getSortFieldName()));
				} else {
					crit.addOrder(Order.desc(pagedQuery.getSortFieldName()));
				}
			}
		}
		crit.setProjection(null);
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<T> queryResult = crit.list();

		PageResult<T> result = new PageResult<T>(totalCount, queryResult);
		return result;
	}

	public void flush() {
		getSession().flush();
	}

	public void merge(T object) {
		getSession().merge(object);
	}

	@SuppressWarnings("unchecked")
	public List<T> listAll(String orderColumn) {
		return getSession().createCriteria(persistentClass)
				.addOrder(Property.forName(orderColumn).asc()).list();
	}

	protected Criteria createPersistentTypeCriteria(Criterion... restrictions) {
		Criteria criteria = this.getSession().createCriteria(persistentClass);
		for (Criterion criterion : restrictions) {
			criteria.add(criterion);
		}
		return criteria;
	}

	/**
	 * Creates a list of restrictions with the form
	 * "property=A or property=B or property=C or property=D" to emulate the SQL
	 * IN sentence avoiding any limitation imposed by the DBRM.
	 * 
	 * @param property
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Criterion createInRestriction(String property, Collection values) {
		Disjunction disjunction = Restrictions.disjunction();
		for (Object value : values) {
			disjunction.add(Restrictions.eq(property, value));
		}
		return disjunction;
	}

	public String getTableName() {
		String tableName = "";

		ClassMetadata hibernateMetadata = getSessionFactory().getClassMetadata(
				persistentClass);
		if (hibernateMetadata == null) {
			return tableName;
		}

		if (hibernateMetadata instanceof AbstractEntityPersister) {
			AbstractEntityPersister persister = (AbstractEntityPersister) hibernateMetadata;
			tableName = persister.getTableName();
		}

		return tableName;
	}

	public void evict(T object) {
		getSession().evict(object);
	}
}
