package com.example.commet.booker;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import android.content.Context;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private book_list list;
    private bookListAdapter adapt;
    private bookData data;
//    Context con =  new Context();
    @Before
    public void setup() {
        data = new bookData();
//        data.getISBN();
        list = new book_list();
        list.updateData();
//        adapt = new bookListAdapter(this, list);
    }

    @Test
    public void bookListTest() throws Exception
    {
        assertNotNull(list);
    }

    @Test
    public void booklistReturnList () throws Exception {
//        assertNotNull(list.getList());
        System.out.println(list.getList());
    }

    @Test
    public void testBookData () throws Exception {
        System.out.println(data.getISBN());
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}

