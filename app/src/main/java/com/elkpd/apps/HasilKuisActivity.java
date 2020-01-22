package com.elkpd.apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.elkpd.apps.adapter.HasilEvaluasiAdapter;
import com.elkpd.apps.adapter.HasilKuisAdapter;
import com.elkpd.apps.model.HasilEvaluasi;
import com.elkpd.apps.tools.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HasilKuisActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private List<HasilEvaluasi> dataHasils = new ArrayList<>();
    private HasilKuisAdapter hasilKuisAdapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Hasil Kuis");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference("Kuis");

        RecyclerView rvKuis = findViewById(R.id.rvKuis);


        hasilKuisAdapter = new HasilKuisAdapter(dataHasils);
        rvKuis.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvKuis.setAdapter(hasilKuisAdapter);

        hasilKuisAdapter.OnItemClick(new HasilKuisAdapter.onItemClick() {
            @Override
            public void onItemClicked(HasilEvaluasi dataHasil) {

            }
        });

        requestData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void requestData() {
        Utils.showDialog(HasilKuisActivity.this);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot x : dataSnapshot.getChildren()) {
                        HasilEvaluasi dataHasil = new HasilEvaluasi();
                        dataHasil.setId(x.getKey());
                        dataHasil.setNama(Objects.requireNonNull(x.child("user").getValue()).toString());
                        dataHasil.setNilai(Objects.requireNonNull(x.child("nilai").getValue()).toString());
                        dataHasils.add(dataHasil);
                    }
                    hasilKuisAdapter.notifyDataSetChanged();
                    Utils.hideDialog();
                } else {
                    Toast.makeText(getBaseContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    Utils.hideDialog();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                Utils.hideDialog();
            }
        });
    }
}
