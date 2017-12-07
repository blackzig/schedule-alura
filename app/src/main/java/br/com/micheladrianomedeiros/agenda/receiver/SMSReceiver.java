package br.com.micheladrianomedeiros.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.micheladrianomedeiros.agenda.R;
import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;

/**
 * Created by zigui on 06/12/2017.
 */

public class SMSReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String format = (String) intent.getSerializableExtra("format");
        SmsMessage smsMessage = SmsMessage.createFromPdu(pdu, format);
        String fone = smsMessage.getDisplayOriginatingAddress();

        StudentDAO studentDAO = new StudentDAO(context);
        if(studentDAO.isStudent(fone)){
            Toast.makeText(context, "Chegou um SMS de Aluno", Toast.LENGTH_SHORT).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.shouryuuken);
            mediaPlayer.start();
        }
    }
}
