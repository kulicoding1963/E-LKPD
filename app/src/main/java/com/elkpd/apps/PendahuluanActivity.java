package com.elkpd.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class PendahuluanActivity extends AppCompatActivity {

    public static final String URL = "URL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendahuluan);

        String url = getIntent().getStringExtra(URL);
        PDFView pdf = findViewById(R.id.pdf);
        pdf.fromAsset(url)
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
    }
}
