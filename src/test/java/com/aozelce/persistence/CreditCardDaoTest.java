package com.aozelce.persistence;

import com.aozelce.entity.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Credit card dao test.
 *
 *  @author aozelce
 */
class CreditCardDaoTest {

    private CreditCardDao creditCardDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        creditCardDao = new CreditCardDao();
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets all credit cards.
     */
    @Test
    void getAllCreditCards() {
        List<CreditCard> creditCards = creditCardDao.getAllCreditCards();
        assertNotNull(creditCards);
        assertFalse(creditCards.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        CreditCard creditCard = creditCardDao.getById(1);
        assertNotNull(creditCard);
        assertEquals("4111111111111111", creditCard.getCardNumber());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        CreditCard creditCard = creditCardDao.getById(1);
        assertNotNull(creditCard);
        creditCard.setCurrentBalance(new BigDecimal("2000.00"));
        creditCardDao.update(creditCard);

        CreditCard updatedCard = creditCardDao.getById(1);
        assertEquals(new BigDecimal("2000.00"), updatedCard.getCurrentBalance());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber("0000000000000000");
        newCard.setExpDate(Date.valueOf("2025-12-31"));
        newCard.setCcv("123");
        newCard.setDueDate(Date.valueOf("2026-01-15"));
        newCard.setCreditLimit(new BigDecimal("12000.00"));
        newCard.setCurrentBalance(new BigDecimal("2500.00"));

        // Set User and CardIssuer
        newCard.setUser(creditCardDao.getById(1).getUser());
        newCard.setCardIssuer(creditCardDao.getById(1).getCardIssuer());

        int newCardId = creditCardDao.insert(newCard);
        assertTrue(newCardId > 0);

        CreditCard insertedCard = creditCardDao.getById(newCardId);
        assertNotNull(insertedCard);
        assertEquals("0000000000000000", insertedCard.getCardNumber());
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        CreditCard creditCard = creditCardDao.getById(2);
        assertNotNull(creditCard);

        creditCardDao.delete(creditCard);
        CreditCard deletedCard = creditCardDao.getById(2);
        assertNull(deletedCard);
    }

    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        List<CreditCard> creditCards = creditCardDao.getByPropertyEqual("cardNumber", "4111111111111111");
        assertNotNull(creditCards);
        assertEquals(1, creditCards.size());
        assertEquals("4111111111111111", creditCards.get(0).getCardNumber());
    }

    /**
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        List<CreditCard> creditCards = creditCardDao.getByPropertyLike("cardNumber", "4111");
        assertNotNull(creditCards);
        assertFalse(creditCards.isEmpty());
        assertTrue(creditCards.stream().allMatch(card -> card.getCardNumber().contains("4111")));
    }
}
