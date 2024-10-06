package com.example.qrcode;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ConfirmacaoCorridaActivity extends AppCompatActivity {

    private TextView confirmationMessage;
    private TextView confirmationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacao_qr_code);

        confirmationMessage = findViewById(R.id.confirmationMessage);
        confirmationDetails = findViewById(R.id.confirmationDetails);

        // Mostra a mensagem de confirmação
        confirmationMessage.setText("Você está confirmado na corrida!");

        // Exibe a data e hora atuais
        String currentDate = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault()).format(new Date());
        confirmationDetails.setText("Confirmado no dia " + currentDate);
    }
}
