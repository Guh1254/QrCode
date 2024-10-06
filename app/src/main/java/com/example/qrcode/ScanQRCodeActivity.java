package com.example.qrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.google.zxing.ResultPoint;

import java.util.List;

public class ScanQRCodeActivity extends AppCompatActivity {

    private CompoundBarcodeView barcodeScannerView;
    private static final int CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        barcodeScannerView = findViewById(R.id.barcodeScanner);

        // Verificar se a permissão da câmera foi concedida
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Se a permissão não foi concedida, solicitar permissão
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            iniciarLeituraQRCode();  // Iniciar leitura do QR Code se a permissão já foi concedida
        }
    }

    // Método para iniciar a leitura do QR Code
    private void iniciarLeituraQRCode() {
        barcodeScannerView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result != null) {
                    String scannedData = result.getText();
                    if (scannedData.equals("Confirmado na corrida")) {
                        // Vai para a tela de confirmação
                        Intent intent = new Intent(ScanQRCodeActivity.this, ConfirmacaoCorridaActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
            }
        });
    }

    // Método chamado quando o usuário responde ao pedido de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Se a permissão foi concedida, iniciar a leitura do QR Code
                iniciarLeituraQRCode();
            } else {
                // Se a permissão for negada, você pode mostrar uma mensagem ao usuário
                // dizendo que a câmera é necessária para ler o QR Code
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeScannerView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }
}
