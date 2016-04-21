package com.example.commet.booker;
import android.app.Application;

/**
 * Created by Jesse on 4/21/2016.
 */
public class UserData extends Application{
    private String password;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
