package com.example.commet.booker;

import com.parse.ParseObject;
import com.parse.ParseClassName;
/**
*This class is used to grab data off of parse in addition to checking if an action is complete
*The getEmail() method pulls in the Email address stored in the database, with the getAuthor() pulling
* in the author. Finally our get ISBN pulls in the ISBN number.
*/
@ParseClassName("BookData")
public class BookData extends ParseObject{
    public BookData() {}

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
