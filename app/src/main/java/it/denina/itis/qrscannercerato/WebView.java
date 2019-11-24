package it.denina.itis.qrscannercerato;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.TextView;
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

        mioIntent.initiateScan();

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

                if(mioResult.getContents().substring(0,4).equals("http")){
                    Toast.makeText(this, mioResult.getContents().substring(0,4), Toast.LENGTH_SHORT).show();
                    web.setWebViewClient(new WebViewClient());
                    web.loadUrl(mioResult.getContents());

                }else{
                    //cerco il file in locale


                }

            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //if (web.copyBackForwardList().getCurrentIndex() > 0) {
            web.goBack();

       // }
       // else {
          //  super.onBackPressed(); // finishes activity
         //   Toast.makeText(this, "errore backPress", Toast.LENGTH_SHORT).show();
       // }
    }
}
