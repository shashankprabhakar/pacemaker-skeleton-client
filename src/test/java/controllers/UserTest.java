package controllers;



import static models.Fixtures.users;
import static org.junit.Assert.*;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class UserTest {
    PacemakerAPI pacemaker = new PacemakerAPI("http://localhost:7000");
    User homer = new User("homer", "simpson", "homer@simpson.com", "secret");

    @Before
    public void setup() {
        pacemaker.deleteUsers();
        System.out.println("users" + pacemaker.getUsers());
    }
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateUser() {
        User user = pacemaker.createUser(homer.firstname, homer.lastname, homer.email, homer.password);
        assertEquals(user, homer);
        User user2 = pacemaker.getUserByEmail(homer.email);
        assertEquals(user2, homer);
    }

    @Test
    public void testCreateUsers() {
        users.forEach(
                user -> pacemaker.createUser(user.firstname, user.lastname, user.email, user.password));
        Collection<User> returnedUsers = pacemaker.getUsers();
        assertEquals(users.size(), returnedUsers.size());
    }
}


