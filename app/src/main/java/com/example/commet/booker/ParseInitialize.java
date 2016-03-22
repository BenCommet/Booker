package com.example.commet.booker;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by bcom3_000 on 3/21/2016.
 */
public class ParseInitialize extends Application{
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "NpGnKkyFxdnd6ZHmLW9pBG16YtwOE7LtzskxCkg0", "yjTOPXcNIDTsswA3RQ4P8CAm797ykDhI30T57h7E");
    }
}
