package com.example.fady.onplace;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fady on 11/5/2017.
 */

public class LogStatus {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public LogStatus (Context ctx)
    {
        this.ctx= ctx;
        this.prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin (boolean loggedin)
    {
        editor.putBoolean("loggedinmode" , loggedin);
        editor.commit();
    }

    public boolean loggedin()
    {
        return prefs.getBoolean("loggedinmode",false);
    }
}
