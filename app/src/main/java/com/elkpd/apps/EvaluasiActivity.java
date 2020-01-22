package com.elkpd.apps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.elkpd.apps.adapter.HasilEvaluasiAdapter;
import com.elkpd.apps.model.HasilEvaluasi;
import com.elkpd.apps.tools.Utils;
import com.elkpd.apps.ui.Evaluasi.Evaluasi11Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi12Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi13Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi21Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi22Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi23Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi24Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi31Fragment;
import com.elkpd.apps.ui.Evaluasi.Evaluasi32Fragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EvaluasiActivity extends AppCompatActivity {

    public static final String URL = "URL";
    public static final String TYPE = "TYPE";
    private HasilEvaluasiAdapter adapterHasil;
    private List<HasilEvaluasi> dataHasils = new ArrayList<>();
    private DatabaseReference mDatabase;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);
        final int kegiatan = getIntent().getIntExtra(TYPE, 0);
        final String url = getIntent().getStringExtra(URL);

        RecyclerView rvEvaluasi = findViewById(R.id.rvEvaluasi);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Hasil Evaluasi Kegiatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        adapterHasil = new HasilEvaluasiAdapter(dataHasils);
        rvEvaluasi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvEvaluasi.setAdapter(adapterHasil);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(kegiatan == 11){
            requestData("KegiatanSatu","AktivitasSatu");
        }else if(kegiatan == 12){
            requestData("KegiatanSatu","AktivitasDua");
        }else if(kegiatan == 13){
            requestData("KegiatanSatu","AktivitasTiga");
        }else if(kegiatan == 21){
            requestData("KegiatanDua","AktivitasSatu");
        }else if(kegiatan == 22){
            requestData("KegiatanDua","AktivitasDua");
        }else if(kegiatan == 23){
            requestData("KegiatanDua","AktivitasTiga");
        }else if(kegiatan == 24){
            requestData("KegiatanDua","AktivitasEmpat");
        }else if(kegiatan == 31){
            requestData("KegiatanTiga","AktivitasSatu");
        }else if(kegiatan == 32){
            requestData("KegiatanTiga","AktivitasDua");
        }

        adapterHasil.OnItemClick(new HasilEvaluasiAdapter.onItemClick() {
            @Override
            public void onItemClicked(HasilEvaluasi dataHasil) {
                Intent intent = new Intent(EvaluasiActivity.this, AktivitasActivity.class);
                intent.putExtra(AktivitasActivity.TYPE, kegiatan);
                intent.putExtra(AktivitasActivity.LOGIN_HASH, "guru");
                intent.putExtra(AktivitasActivity.NAME, "admin");
                intent.putExtra(AktivitasActivity.URL, url);
                intent.putExtra(AktivitasActivity.DATA, dataHasil);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void requestData(String kegiatan, String aktivitas) {
        Utils.showDialog(EvaluasiActivity.this);
        mDatabase.child(kegiatan).child(aktivitas).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot x : dataSnapshot.getChildren()) {
                        HasilEvaluasi dataHasil = new HasilEvaluasi();
                        dataHasil.setId(x.getKey());
                        dataHasil.setNama(Objects.requireNonNull(x.child("user").getValue()).toString());
                        dataHasil.setNilai(Objects.requireNonNull(x.child("nilai").getValue()).toString());
                        dataHasil.setAnwswer1(x.child("answer1").getValue() == null ? "" : Objects.requireNonNull(x.child("answer1").getValue()).toString());
                        dataHasil.setAnwswer2(x.child("answer2").getValue() == null ? "" : Objects.requireNonNull(x.child("answer2").getValue()).toString());
                        dataHasil.setAnwswer3(x.child("answer3").getValue() == null ? "" : Objects.requireNonNull(x.child("answer3").getValue()).toString());
                        dataHasil.setAnwswer4(x.child("answer4").getValue() == null ? "" : Objects.requireNonNull(x.child("answer4").getValue()).toString());
                        dataHasil.setAnwswer5(x.child("answer5").getValue() == null ? "" : Objects.requireNonNull(x.child("answer5").getValue()).toString());
                        dataHasil.setAnwswer6(x.child("answer6").getValue() == null ? "" : Objects.requireNonNull(x.child("answer6").getValue()).toString());
                        dataHasils.add(dataHasil);
                    }
                    adapterHasil.notifyDataSetChanged();
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
