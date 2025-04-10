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

    /**
     * The Generic dao.
     */
    private GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(CardIssuer.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets all card issuers.
     */
    @Test
    void getAllCardIssuers() {
        List<CardIssuer> cardIssuers = genericDao.getAll();
        assertNotNull(cardIssuers);
        assertFalse(cardIssuers.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        CardIssuer cardIssuer = (CardIssuer) genericDao.getById(1);
        assertNotNull(cardIssuer);
        CardIssuer expectedCardIssuer = new CardIssuer();
        expectedCardIssuer.setIssuerId(1);
        expectedCardIssuer.setIssuerName("Visa");
        assertEquals(expectedCardIssuer, cardIssuer);

    }

    /**
     * Update.
     */
    @Test
    void update() {
        CardIssuer cardIssuer = (CardIssuer) genericDao.getById(1);
        assertNotNull(cardIssuer);
        cardIssuer.setIssuerName("UpdatedIssuerName");
        genericDao.update(cardIssuer);

        CardIssuer updatedCardIssuer = (CardIssuer) genericDao.getById(1);
        assertEquals(cardIssuer, updatedCardIssuer);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        CardIssuer newCardIssuer = new CardIssuer();
        newCardIssuer.setIssuerName("NewIssuer");

        int newIssuerId = genericDao.insert(newCardIssuer);
        assertTrue(newIssuerId > 0);

        CardIssuer insertedCardIssuer = (CardIssuer) genericDao.getById(newIssuerId);
        assertNotNull(insertedCardIssuer);
        assertEquals(newCardIssuer, insertedCardIssuer);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        CardIssuer cardIssuer = (CardIssuer) genericDao.getById(3);
        assertNotNull(cardIssuer);

        genericDao.delete(cardIssuer);
        CardIssuer deletedCardIssuer = (CardIssuer) genericDao.getById(3);
        assertNull(deletedCardIssuer);
    }

    /**
     * Gets by property equal.
     */
    @Test
    public void testCardIssuerEquals() {
        // Create a CardIssuer object with issuerName set
        CardIssuer expectedIssuer = new CardIssuer("Visa");

        // Insert the expectedIssuer into the database to get an auto-generated issuerId
        GenericDao<CardIssuer> cardIssuerDao = new GenericDao<>(CardIssuer.class);
        int id = cardIssuerDao.insert(expectedIssuer);
        expectedIssuer.setIssuerId(id);

        // Retrieve the CardIssuer by ID from the database
        CardIssuer foundIssuer = cardIssuerDao.getById(id);

        // Assert that the expectedIssuer is equal to the foundIssuer
        assertNotNull(foundIssuer, "Issuer not found in the list.");
        assertEquals(expectedIssuer, foundIssuer);
    }


    /**
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        List<CardIssuer> cardIssuers = genericDao.getByPropertyLike("issuerName", "Vi");
        assertNotNull(cardIssuers);
        assertFalse(cardIssuers.isEmpty());
        assertTrue(cardIssuers.stream().allMatch(cardIssuer -> cardIssuer.getIssuerName().contains("Vi")));
    }

}