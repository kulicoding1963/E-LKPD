package com.elkpd.apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.elkpd.apps.adapter.SectionsPagerAdapter;
import com.elkpd.apps.model.HasilEvaluasi;
import com.google.android.material.tabs.TabLayout;

public class AktivitasActivity extends AppCompatActivity {

    public static final String URL = "URL";
    public static final String TYPE = "TYPE";
    public static final String NAME = "NAME";
    public static final String LOGIN_HASH = "LOGIN_HASH";
    public static final String DATA = "DATA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aktivitas);
        String url = getIntent().getStringExtra(URL);
        int type = getIntent().getIntExtra(TYPE,0);
        String hash = getIntent().getStringExtra(LOGIN_HASH);
        String name = getIntent().getStringExtra(NAME);
        HasilEvaluasi hasilEvaluasi = getIntent().getParcelableExtra(DATA);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),url,type,hash,name,hasilEvaluasi);
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
