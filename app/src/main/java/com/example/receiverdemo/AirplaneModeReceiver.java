package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Cette méthode est déclenchée par le système quand le mode avion change
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {

            // Lecture de l'état réel du système (0 = OFF, 1 = ON)
            boolean isAirplaneOn = Settings.Global.getInt(
                    context.getContentResolver(), 
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;

            String message = isAirplaneOn
                    ? "✈️ Mode Avion ACTIVÉ"
                    : "🌐 Mode Avion DÉSACTIVÉ";

            // Affichage d'un message à l'écran
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}