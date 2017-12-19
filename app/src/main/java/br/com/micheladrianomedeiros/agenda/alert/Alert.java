package br.com.micheladrianomedeiros.agenda.alert;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zigui on 18/12/2017.
 */

public class Alert {

    public void ShortMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void LongMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
