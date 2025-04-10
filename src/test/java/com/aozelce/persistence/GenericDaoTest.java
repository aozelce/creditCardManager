package com.aozelce.persistence;

import com.aozelce.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Generic dao test.
 *
 *  @author aozelce
 */
class GenericDaoTest {

    private GenericDao<User> userDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao<>(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        User user = userDao.getById(1); // Assume ID 1 exists in the database
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
    }

    /**
     * Gets all.
     */
    @Test
    void getAll() {
        List<User> users = userDao.getAll();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        List<User> users = userDao.getByPropertyEqual("username", "jane_smith");
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("jane_smith", users.get(0).getUsername());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        User user = userDao.getById(1);
        assertNotNull(user);
        user.setEmail("updated.email@example.com");
        userDao.update(user);

        User updatedUser = userDao.getById(1);
        assertEquals("updated.email@example.com", updatedUser.getEmail());
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        User user = userDao.getById(3);
        assertNotNull(user);

        userDao.delete(user);
        User deletedUser = userDao.getById(3);
        assertNull(deletedUser);
    }
}
