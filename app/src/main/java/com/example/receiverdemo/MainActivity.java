package com.example.receiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private AirplaneModeReceiver airplaneReceiver;
    private boolean isReceiverRegistered = false;
    private Button btnToggleAirplane, btnSendCustom;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneReceiver = new AirplaneModeReceiver();
        tvStatus = findViewById(R.id.tvStatus);
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane);
        btnSendCustom = findViewById(R.id.btnSendCustom);

        // Initialisation du texte
        tvStatus.setText("Receiver Mode Avion : DÉSACTIVÉ");

        btnToggleAirplane.setOnClickListener(v -> toggleAirplaneReceiver());
        btnSendCustom.setOnClickListener(v -> sendCustomBroadcast());
    }

    private void toggleAirplaneReceiver() {
        if (!isReceiverRegistered) {
            // Création du filtre pour le receiver dynamique
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

            // Pour les broadcasts système comme le Mode Avion, il est préférable d'utiliser RECEIVER_EXPORTED
            // car le broadcast provient d'un processus système (en dehors de l'application).
            ContextCompat.registerReceiver(this, airplaneReceiver, filter, ContextCompat.RECEIVER_EXPORTED);
            
            isReceiverRegistered = true;
            tvStatus.setText("Receiver Mode Avion : ACTIVÉ (En attente...)");
            btnToggleAirplane.setText("Désactiver le Receiver");
            Toast.makeText(this, "Receiver activé ! Basculez le mode avion pour tester.", Toast.LENGTH_SHORT).show();
        } else {
            unregisterReceiver(airplaneReceiver);
            isReceiverRegistered = false;
            tvStatus.setText("Receiver Mode Avion : DÉSACTIVÉ");
            btnToggleAirplane.setText("Activer le Receiver");
            Toast.makeText(this, "Receiver désactivé.", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendCustomBroadcast() {
        // Intent explicite pour le CustomEventReceiver
        Intent intent = new Intent(this, CustomEventReceiver.class);
        intent.setAction("com.example.receiverdemo.CUSTOM_EVENT");
        intent.putExtra("message", "Ceci est un message personnalisé !");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver);
        }
        super.onDestroy();
    }
}