package it.denina.itis.qrscannercerato;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView web;
    private IntentIntegrator mioIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        web = findViewById(R.id.WebView);

        mioIntent = new IntentIntegrator(this); //creazione dell'oggetto IntentIntegrator
        mioIntent.initiateScan(); //fa partire la scansione

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //apre una activity e quando ha finito torna a questa
        super.onActivityResult(requestCode, resultCode, data);

        //creo un oggetto di tipo IntentResult
        IntentResult mioResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (mioResult != null) { //controllo se il QR code c'è

            if (mioResult.getContents() == null) { //se è nullo visualizzo un toast

                Toast.makeText(this, "Non riesco a risolvere questo QR!", Toast.LENGTH_LONG).show();

            } else { //se il qr code ha dei dati

                if(mioResult.getContents().substring(0,4).equals("http")){//se è online
                    Toast.makeText(this, mioResult.getContents(), Toast.LENGTH_SHORT).show(); //visualizza l'URL

                }else{  //cerco il file in locale
                    Toast.makeText(this, "Aperto il file in locale", Toast.LENGTH_SHORT).show();
                }

                web.setWebViewClient(new WebViewClient());
                web.loadUrl(mioResult.getContents());

            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

    @Override
    public void onBackPressed() {

        if (web.canGoBack()) {
            web.goBack();

       }
       else {
         super.onBackPressed(); // finishes activity
       }
    }
}
