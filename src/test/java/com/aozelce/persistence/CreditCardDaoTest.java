package com.aozelce.persistence;

import com.aozelce.entity.CardIssuer;
import com.aozelce.entity.CreditCard;
import com.aozelce.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    /**
     * The Logger.
     */
    Logger logger = LogManager.getLogger(this.getClass());


    /**
     * The Generic dao.
     */
    private GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(CreditCard.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets all credit cards.
     */
    @Test
    void getAllCreditCards() {
        List<CreditCard> creditCards = genericDao.getAll();
        assertFalse(creditCards.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        CreditCard creditCard = (CreditCard)genericDao.getById(1);
        assertNotNull(creditCard);
        assertEquals("4111111111111111", creditCard.getCardNumber());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        CreditCard creditCard = (CreditCard) genericDao.getById(1);
        assertNotNull(creditCard);
        creditCard.setCurrentBalance(new BigDecimal("2000.00"));
        genericDao.update(creditCard);

        CreditCard updatedCard = (CreditCard) genericDao.getById(1);
        assertEquals(new BigDecimal("2000.00"), updatedCard.getCurrentBalance());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        // Create a new CreditCard entity
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber("0000000000000000");
        newCard.setExpDate(Date.valueOf("2025-12-31"));
        newCard.setCcv("123");
        newCard.setDueDate(Date.valueOf("2026-01-15"));
        newCard.setCreditLimit(new BigDecimal("12000.00"));
        newCard.setCurrentBalance(new BigDecimal("2500.00"));

        // Set the cardIssuer
        CardIssuer cardIssuer = (CardIssuer) new GenericDao<>(CardIssuer.class).getById(1);
        assertNotNull(cardIssuer);
        newCard.setCardIssuer(cardIssuer);

        User user = (User) new GenericDao<>(User.class).getById(1);
        assertNotNull(user);
        newCard.setUser(user);

        // Insert the new card
        int newCardId = genericDao.insert(newCard);
        assertTrue(newCardId > 0);

        // Verify the inserted card
        CreditCard insertedCard = (CreditCard) genericDao.getById(newCardId);
        assertNotNull(insertedCard);
        assertEquals(newCard, insertedCard);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        // Get the credit card by ID
        CreditCard creditCard = (CreditCard) genericDao.getById(2);
        assertNotNull(creditCard);
        logger.debug("Delete credit card : {}", creditCard.getCardNumber());

        // Get the associated user and card issuer IDs before deletion
        int userId = creditCard.getUser().getUserId();
        logger.info("User ID: " + userId);
        int issuerId = creditCard.getCardIssuer().getIssuerId();
        logger.info("Issuer ID: " + issuerId);

        logger.info("Before delete" + creditCard);

        // Delete the credit card
        genericDao.delete(creditCard);

        // Ensure the credit card is deleted
        CreditCard deletedCard = (CreditCard) genericDao.getById(2);
        assertNull(deletedCard);

        logger.info("After delete: " + deletedCard);

        // Verify that the user is not deleted
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(userId);
        assertNotNull(user);

        // Verify that the issuer is not deleted
        GenericDao<CardIssuer> issuerDao = new GenericDao<>(CardIssuer.class);
        CardIssuer issuer = issuerDao.getById(issuerId);
        assertNotNull(issuer);
    }


    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        // Fetch the credit card with a known card number
        List<CreditCard> creditCards = genericDao.getByPropertyEqual("cardNumber", "4111111111111111");

        // Ensure the list is not null and contains exactly one credit card
        assertNotNull(creditCards);
        assertEquals(1, creditCards.size());

        // Assert the card number matches the expected value
        CreditCard retrievedCard = creditCards.get(0);

        // Create an expected CreditCard instance for comparison
        CreditCard expectedCard;
        int id = retrievedCard.getCardId();
        expectedCard = (CreditCard) genericDao.getById(id);
        expectedCard.setUser(retrievedCard.getUser());
        expectedCard.setCardNumber("4111111111111111");
        expectedCard.setExpDate(Date.valueOf("2025-03-31"));
        expectedCard.setCcv("123");
        expectedCard.setDueDate(Date.valueOf("2025-04-10"));
        expectedCard.setCreditLimit(new BigDecimal("5000.00"));
        expectedCard.setCurrentBalance(new BigDecimal("1500.00"));

        assertEquals(expectedCard,retrievedCard);
    }


    /**
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        // Get credit cards where card number contains "4111"
        List<CreditCard> creditCards = genericDao.getByPropertyLike("cardNumber", "4111");

        // Assert that the list is not null and contains at least one result
        assertNotNull(creditCards);
        assertFalse(creditCards.isEmpty());

        // Assert that all card numbers contain "4111"
        // Reference: https://www.w3schools.com/java/java_lambda.asp
        creditCards.forEach(card -> assertTrue(card.getCardNumber().contains("4111")));
    }

}
