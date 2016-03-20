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
    private BookList list;
    private BookListAdapter adapt;
    private BookData data;
    MainActivity act;

    /**
    * Instatiation of objects used in following tests
    */
    @Before
    public void setup() {
        act = new MainActivity();
        list = new BookList();
        list.updateData();
    }

    /**
    * Test to see if BookList is instantiated correctly.
    */
    @Test
    public void bookListTest() throws Exception
    {
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
        String s = "jesse@gmail";
        assertTrue(act.checkUsername(s));
    }

    /**
     * Tests error checking helper function for username in MainActivity.
     */
    @Test
    public void loginErrorCheckTestFalse() throws Exception {
        String s = "jessegmail.com";
        assertFalse(act.checkUsername(s));
    }
}

