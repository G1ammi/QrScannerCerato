package it.denina.itis.qrscannercerato;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent mioIntent = new Intent(getApplicationContext(),WebView.class);
        startActivity(mioIntent);
    }
}
