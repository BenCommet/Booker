package com.example.commet.booker;

import org.junit.Before;
import org.junit.Test;

//import java_cup.Main;

import static org.junit.Assert.*;

/**
 * Test basic methods of application activities and helper classes.
 * Creates instances of various objects and asserts conditions based on
 * method calls.
 */
public class MainUnitTest {
    BookList list;
    MainActivity act;
    GoogleQuery gq;
    MySQLController ctrl;
    UserData up;

    /**
    * Instatiation of objects used in following tests
    */
    @Before
    public void setup() {
        act = new MainActivity();
        list = new BookList();
        gq = new GoogleQuery();
        ctrl = new MySQLController();
        up = new UserData();
        up.setPassword("123466789");
        up.setEmail("jessedroe@gmail.com");
    }

    /**
    * Test to see if BookList is instantiated correctly.
    */
    @Test
    public void bookListTest() throws Exception {
        assertNotNull(list);
    }

    /**
     * Tests error checking helper function for passwords in MainActivity.
     */
    @Test
    public void passErrorCheckTestTrue() throws Exception {
        String s = "123456789";
        assertTrue(act.checkPassword(s));
    }

    /**
     * Tests error checking helper function passwords in MainActivity.
     */
    @Test
    public void passErrorCheckTestFalse() throws Exception {
        String s = "1234";
        assertFalse(act.checkPassword(s));
    }

    /**
     * Tests error checking helper function for username in MainActivity.
     */
    @Test
    public void loginErrorCheckTestTrue() throws Exception {
        String s = "jessedroe@gmail.com";
        assertTrue(act.checkUsername(s));
    }

    /**
     * Tests error checking helper function for username in MainActivity.
     */
    @Test
    public void loginErrorCheckTestFalse() throws Exception {
        String s = "jessegmail";
        assertFalse(act.checkUsername(s));
    }

    @Test
    public void userDataSetEmail() throws Exception {
        String s = "test@gmail.com";
        up.setEmail(s);
        assertTrue(s == up.getEmail());
    }

    @Test
    public void userDataSetPass() throws Exception {
        String s = "123466";
        up.setPassword(s);
        assertTrue(s == up.getPassword());
    }

    @Test
    public void userDataGetPass() throws Exception {
        String s = "123466789";
        assertEquals(s, up.getPassword());
    }

    @Test
    public void userDataGetEmail() throws Exception {
        String s = "jessedroe@gmail.com";
        assertEquals(s,up.getEmail());
    }
    @Test
    public void userDataSetEmailFalse() throws Exception {
        String s = "test@gmail.com";
        String s2 = "test2@gmail";
        up.setEmail(s);
        assertFalse(s2 == up.getEmail());
    }

    @Test
    public void userDataSetPassFalse() throws Exception {
        String s = "123466";
        String s2 = "3216478";
        up.setPassword(s);
        assertFalse(s2 == up.getPassword());
    }

    @Test
    public void userDataGetPassFalse() throws Exception {
        String s = "123466";
        assertNotEquals(s, up.getPassword());
    }

    @Test
    public void userDataGetEmailFalse() throws Exception {
        String s = "jroe@gmail.com";
        assertNotEquals(s,up.getEmail());
    }

}

