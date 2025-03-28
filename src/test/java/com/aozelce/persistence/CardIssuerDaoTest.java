package com.aozelce.persistence;

import com.aozelce.entity.CardIssuer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Card issuer dao test.
 *
 * @author aozelce
 */
class CardIssuerDaoTest {

    private CardIssuerDao cardIssuerDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        cardIssuerDao = new CardIssuerDao();
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets all card issuers.
     */
    @Test
    void getAllCardIssuers() {
        List<CardIssuer> cardIssuers = cardIssuerDao.getAllCardIssuers();
        assertNotNull(cardIssuers);
        assertFalse(cardIssuers.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        CardIssuer cardIssuer = cardIssuerDao.getById(1); // Assume ID 1 exists in the database
        assertNotNull(cardIssuer);
        assertEquals("Visa", cardIssuer.getIssuerName());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        CardIssuer cardIssuer = cardIssuerDao.getById(1);
        assertNotNull(cardIssuer);
        cardIssuer.setIssuerName("UpdatedIssuerName");
        cardIssuerDao.update(cardIssuer);

        CardIssuer updatedCardIssuer = cardIssuerDao.getById(1);
        assertEquals("UpdatedIssuerName", updatedCardIssuer.getIssuerName());
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        CardIssuer newCardIssuer = new CardIssuer();
        newCardIssuer.setIssuerName("NewIssuer");

        int newIssuerId = cardIssuerDao.insert(newCardIssuer);
        assertTrue(newIssuerId > 0);

        CardIssuer insertedCardIssuer = cardIssuerDao.getById(newIssuerId);
        assertNotNull(insertedCardIssuer);
        assertEquals("NewIssuer", insertedCardIssuer.getIssuerName());
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        CardIssuer cardIssuer = cardIssuerDao.getById(3);
        assertNotNull(cardIssuer);

        cardIssuerDao.delete(cardIssuer);
        CardIssuer deletedCardIssuer = cardIssuerDao.getById(3);
        assertNull(deletedCardIssuer);
    }

    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        List<CardIssuer> cardIssuers = cardIssuerDao.getByPropertyEqual("issuerName", "Visa");
        assertNotNull(cardIssuers);
        assertFalse(cardIssuers.isEmpty());
        assertEquals("Visa", cardIssuers.get(0).getIssuerName());
    }

    /**
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        List<CardIssuer> cardIssuers = cardIssuerDao.getByPropertyLike("issuerName", "Vi");
        assertNotNull(cardIssuers);
        assertFalse(cardIssuers.isEmpty());
        assertTrue(cardIssuers.stream().allMatch(cardIssuer -> cardIssuer.getIssuerName().contains("Vi")));
    }

}