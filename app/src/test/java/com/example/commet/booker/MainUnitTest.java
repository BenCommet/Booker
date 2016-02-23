package com.example.commet.booker;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import android.content.Context;
import android.util.Log;

import java_cup.Main;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class MainUnitTest {
    private book_list list;
    private bookListAdapter adapt;
    private bookData data;
    MainActivity act;


    @Before
    public void setup() {
        act = new MainActivity();
        list = new book_list();
        list.updateData();
    }

    @Test
    public void bookListTest() throws Exception
    {
        assertNotNull(list);
    }

    @Test
    public void passErrorCheckTestTrue() throws Exception {
        String s = "123456789";
        assertTrue(act.checkPassword(s));
    }

    @Test
    public void passErrorCheckTestFalse() throws Exception {
        String s = "1234";
        assertFalse(act.checkPassword(s));
    }

    @Test
    public void loginErrorCheckTestTrue() throws Exception {
        String s = "jesse@gmail";
        assertTrue(act.checkUsername(s));
    }

    @Test
    public void loginErrorCheckTestFalse() throws Exception {
        String s = "jessegmail.com";
        assertFalse(act.checkUsername(s));
    }
}

