package persistence;

import entity.CardIssuer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

public class CardIssuerDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public List<CardIssuer> getAllCardIssuers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CardIssuer> query = builder.createQuery(CardIssuer.class);
        Root<CardIssuer> root = query.from(CardIssuer.class);
        List<CardIssuer> cardIssuers = session.createQuery(query).getResultList();
        session.close();
        return cardIssuers;
    }

    public CardIssuer getById(int id) {
        Session session = sessionFactory.openSession();
        CardIssuer cardIssuer = session.get(CardIssuer.class, id);
        session.close();
        return cardIssuer;
    }

    public void update(CardIssuer cardIssuer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(cardIssuer);
        transaction.commit();
        session.close();
    }

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

    public void delete(CardIssuer cardIssuer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(cardIssuer);
        transaction.commit();
        session.close();
    }

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
