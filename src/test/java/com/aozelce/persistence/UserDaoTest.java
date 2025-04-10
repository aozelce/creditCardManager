package com.aozelce.persistence;

import com.aozelce.entity.CreditCard;
import com.aozelce.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 *
 *  @author aozelce
 */
class UserDaoTest {

    private GenericDao genericDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets all users.
     */
    @Test
    void getAllUsers() {
        List<User> users = genericDao.getAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        User user = (User)genericDao.getById(1);
        assertNotNull(user);
        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setUsername("john_doe");
        expectedUser.setFirstName("John");
        expectedUser.setLastName("John");
        expectedUser.setEmail("john.doe@example.com");

        assertEquals(expectedUser, user);

    }

    /**
     * Update.
     */
    @Test
    void update() {
        User user = (User) genericDao.getById(1);
        assertNotNull(user);
        user.setFirstName("UpdatedName");
        genericDao.update(user);

        User updatedUser = (User) genericDao.getById(1);
        User expectedUser = new User();
        expectedUser.setUserId(1);
        expectedUser.setUsername("john_doe");
        expectedUser.setFirstName("UpdatedName");
        expectedUser.setLastName("John");
        expectedUser.setEmail("john.doe@example.com");
        assertEquals(expectedUser, updatedUser);
    }

    /**
     * Insert.
     */
    @Test
    void insert() {
        User newUser = new User();
        newUser.setUsername("new_user");
        newUser.setEmail("new.user@example.com");
        newUser.setFirstName("New");
        newUser.setLastName("User");

        int newUserId = genericDao.insert(newUser);
        assertTrue(newUserId > 0);

        User insertedUser = (User) genericDao.getById(newUserId);
        assertNotNull(insertedUser);
        assertEquals(newUser, insertedUser);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        User user = (User) genericDao.getById(3);
        assertNotNull(user);

        genericDao.delete(user);
        User deletedUser = (User) genericDao.getById(3);
        assertNull(deletedUser);

        // Verify that the user's credit cards are also deleted.
        GenericDao creditCardDao = new GenericDao(CreditCard.class);
        List<CreditCard> orphanedCards = creditCardDao.getByPropertyEqual("user", user);
        assertTrue(orphanedCards.isEmpty());
    }


    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        List<User> users = genericDao.getByPropertyEqual("username", "jane_smith");
        assertNotNull(users);
        assertEquals(1, users.size());
        User expectedUser = new User();
        expectedUser.setUserId(users.get(0).getUserId());
        expectedUser.setUsername("jane_smith");
        expectedUser.setEmail("jane.smith@example.com");
        expectedUser.setFirstName("Jane");
        expectedUser.setLastName("Smith");

        assertEquals(expectedUser, users.get(0));
    }

    /**
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        List<User> users = genericDao.getByPropertyLike("email", "example.com");
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(user -> user.getEmail().contains("example.com")));
    }
}