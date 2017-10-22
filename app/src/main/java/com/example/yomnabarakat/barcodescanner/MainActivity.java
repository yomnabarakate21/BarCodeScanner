package com.example.yomnabarakat.barcodescanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends Activity {
    TextView barCodeInfoTV;
    Button scanBarCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barCodeInfoTV = (TextView) findViewById(R.id.tv_barcode_info);
        scanBarCode = (Button) findViewById(R.id.btn_scan_barcode);

        scanBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarCode(view);
            }
        });

    }

    public void scanBarCode(View v) {
        Intent intent = new Intent(this, ScanBarCodeUtils.class);
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barCode= data.getParcelableExtra("barCode");
                    barCodeInfoTV.setText(barCode.displayValue);
                }
            } else {
                barCodeInfoTV.setText("No barcode detected");
            }
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }
}
