package com.example.commet.booker;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesse on 3/21/2016.
 */
public class ParseControl {

    public void saveToDb(String isbn, String email) {
        ParseObject gameScore = new ParseObject("Temp");
        gameScore.put("ISBN", isbn);
        gameScore.put("userEmail", "email");
        gameScore.saveInBackground();
        Log.d("ParsePost", "Post Complete");
    }

//    public Boolean isInDB(String isbn, String email) {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("bookData");
////        query.whereEqualTo("ISBN", "isbn");
//        final List<ParseObject> tempList = null;
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> scoreList, ParseException e) {
//                if (e == null) {
//                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
//                    tempList = scoreList;
//                } else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });
//
//        if (tempList.size() > 0) {
//            return true;
//        }
//    }



//    public ArrayList<String> findByEmail(String email) {
//
//    }
//
//    public ArrayList<String> findByISBN(String isbn) {
//
//    }



}
