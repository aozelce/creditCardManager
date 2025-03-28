package com.aozelce.persistence;

import com.aozelce.entity.CreditCard;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * The type Credit card dao.
 *
 *  @author aozelce
 */
public class CreditCardDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all credit cards.
     *
     * @return the all credit cards
     */
    public List<CreditCard> getAllCreditCards() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CreditCard> query = builder.createQuery(CreditCard.class);
        Root<CreditCard> root = query.from(CreditCard.class);
        List<CreditCard> creditCards = session.createQuery(query).getResultList();
        session.close();
        return creditCards;
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public CreditCard getById(int id) {
        Session session = sessionFactory.openSession();
        CreditCard creditCard = session.get(CreditCard.class, id);
        session.close();
        return creditCard;
    }

    /**
     * Update.
     *
     * @param creditCard the credit card
     */
    public void update(CreditCard creditCard) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(creditCard);
        transaction.commit();
        session.close();
    }

    /**
     * Insert int.
     *
     * @param creditCard the credit card
     * @return the int
     */
    public int insert(CreditCard creditCard) {
        int id;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(creditCard);
        id = creditCard.getCardId();
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete.
     *
     * @param creditCard the credit card
     */
    public void delete(CreditCard creditCard) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(creditCard);
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
    public List<CreditCard> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for CreditCard with " + propertyName + " = " + value);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CreditCard> query = builder.createQuery(CreditCard.class);
        Root<CreditCard> root = query.from(CreditCard.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<CreditCard> creditCards = session.createQuery(query).getResultList();
        session.close();
        return creditCards;
    }

    /**
     * Gets by property like.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<CreditCard> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();
        logger.debug("Searching for CreditCard with {} = {}", propertyName, value);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CreditCard> query = builder.createQuery(CreditCard.class);
        Root<CreditCard> root = query.from(CreditCard.class);
        query.where(builder.like(root.get(propertyName), "%" + value + "%"));
        List<CreditCard> creditCards = session.createQuery(query).getResultList();
        session.close();
        return creditCards;
    }
}