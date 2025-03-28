package com.aozelce.persistence;

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

    private UserDao userDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        userDao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");

    }

    /**
     * Gets all users.
     */
    @Test
    void getAllUsers() {
        List<User> users = userDao.getAllUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {
        User user = userDao.getById(1);
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        User user = userDao.getById(1);
        assertNotNull(user);
        user.setFirstName("UpdatedName");
        userDao.update(user);

        User updatedUser = userDao.getById(1);
        assertEquals("UpdatedName", updatedUser.getFirstName());
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

        int newUserId = userDao.insert(newUser);
        assertTrue(newUserId > 0);

        User insertedUser = userDao.getById(newUserId);
        assertNotNull(insertedUser);
        assertEquals("new_user", insertedUser.getUsername());
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
     * Gets by property like.
     */
    @Test
    void getByPropertyLike() {
        List<User> users = userDao.getByPropertyLike("email", "example.com");
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.stream().allMatch(user -> user.getEmail().contains("example.com")));
    }
}
