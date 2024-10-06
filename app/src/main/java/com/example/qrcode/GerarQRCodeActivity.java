package com.example.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import android.content.Intent;


public class GerarQRCodeActivity extends AppCompatActivity {

    private ImageView qrCodeImageView;
    private Button btnGenerateQRCode, btnScanQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_qr_code);

        qrCodeImageView = findViewById(R.id.qrCodeImageView);
        btnGenerateQRCode = findViewById(R.id.btnGenerateQRCode);
        btnScanQRCode = findViewById(R.id.btnScanQRCode);

        // Gera o QR code
        btnGenerateQRCode.setOnClickListener(view -> {
            String qrData = "Confirmado na corrida";

            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap qrBitmap = barcodeEncoder.encodeBitmap(qrData, com.google.zxing.BarcodeFormat.QR_CODE, 300, 300);
                qrCodeImageView.setImageBitmap(qrBitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });

        // Vai para a tela de escanear QR code
        btnScanQRCode.setOnClickListener(view -> {
            Intent intent = new Intent(GerarQRCodeActivity.this, ScanQRCodeActivity.class);
            startActivity(intent);
        });
    }
}
