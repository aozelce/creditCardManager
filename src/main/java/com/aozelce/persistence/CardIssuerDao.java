package com.aozelce.persistence;

import com.aozelce.entity.CardIssuer;
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
 * The type Card issuer dao.
 *
 *  @author aozelce
 */
public class CardIssuerDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all card issuers.
     *
     * @return the all card issuers
     */
    public List<CardIssuer> getAllCardIssuers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CardIssuer> query = builder.createQuery(CardIssuer.class);
        Root<CardIssuer> root = query.from(CardIssuer.class);
        List<CardIssuer> cardIssuers = session.createQuery(query).getResultList();
        session.close();
        return cardIssuers;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public CardIssuer getById(int id) {
        Session session = sessionFactory.openSession();
        CardIssuer cardIssuer = session.get(CardIssuer.class, id);
        session.close();
        return cardIssuer;
    }

    /**
     * Update.
     *
     * @param cardIssuer the card issuer
     */
    public void update(CardIssuer cardIssuer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(cardIssuer);
        transaction.commit();
        session.close();
    }

    /**
     * Insert int.
     *
     * @param cardIssuer the card issuer
     * @return the int
     */
    public int insert(CardIssuer cardIssuer) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(cardIssuer);
        id = cardIssuer.getIssuerId();
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete.
     *
     * @param cardIssuer the card issuer
     */
    public void delete(CardIssuer cardIssuer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cardIssuer);
        transaction.commit();
        session.close();
    }

    /**
     * Gets by property equal.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<CardIssuer> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for CardIssuer with " + propertyName + " = " + value);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CardIssuer> query = builder.createQuery(CardIssuer.class);
        Root<CardIssuer> root = query.from(CardIssuer.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<CardIssuer> cardIssuers = session.createQuery(query).getResultList();
        session.close();
        return cardIssuers;
    }

    /**
     * Gets by property like.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<CardIssuer> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for CardIssuer with {} = {}", propertyName, value);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CardIssuer> query = builder.createQuery(CardIssuer.class);
        Root<CardIssuer> root = query.from(CardIssuer.class);
        query.where(builder.like(root.get(propertyName), "%" + value + "%"));
        List<CardIssuer> cardIssuers = session.createQuery(query).getResultList();
        session.close();
        return cardIssuers;
    }
}
