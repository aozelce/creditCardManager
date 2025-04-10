package com.aozelce.persistence;


import com.aozelce.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 */

public class GenericDao<T> {
    private final Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Gets all entities
     *
     * @return the all entities
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;

    }

    /**
     * Gets a entity by id
     * @param id entity id to search by
     * @return a entity
     */
    public <T> T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Inserts a new entity into the database.
     *
     * @param entity the entity to be inserted
     * @return the generated ID of the inserted entity
     */
    public int insert(T entity) {
        int id;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        id = (int) session.getIdentifier(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Inserts or updates the entity.
     *
     * @param entity entity to be inserted/saved
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get order by property (exact match)
     * sample usage: getByPropertyEqual("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value        value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();

        logger.debug("Searching for entity with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery(query).getResultList();

        session.close();
        return entities;
    }

    /**
     * Get entities by property (partial match)
     * sample usage: getByPropertyLike("lastName", "Cur")
     *
     * @param propertyName entity property to search by
     * @param value        value of the property to search for (partial match)
     * @return list of entities meeting the criteria search
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for entity with " + propertyName + " like " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.like(root.get(propertyName), "%" + value + "%"));
        List<T> entities = session.createQuery(query).getResultList();

        session.close();
        return entities;
    }



    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();

    }
}