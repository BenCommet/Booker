package com.example.commet.booker;

import com.parse.ParseObject;
import com.parse.ParseClassName;

@ParseClassName("bookData")
public class bookData extends ParseObject{
    public bookData() {}

    public boolean isCompleted() {return getBoolean("completed");}

    public void setCompleted(boolean complete) {put ("completed", complete);}

    public String getISBN(){
        return getString("ISBN");
    }

    public String getEmail() {
        return getString("userEmail");
    }

    public String getAuthor() {
        return getString("author");
    }
}
