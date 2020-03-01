package com.elkpd.apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MateriActivity extends AppCompatActivity {

    public static final String MATERI = "MATERI";
    public static final String LOGIN_HASH = "LOGIN_HASH";
    public static final String NAME = "NAME";
    private int materi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        materi = getIntent().getIntExtra(MATERI, 0);
        final String hash = getIntent().getStringExtra(LOGIN_HASH);
        final String name = getIntent().getStringExtra(NAME);

        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_7 = findViewById(R.id.btn_7);

        if (materi == 1) {
            btn_7.setVisibility(View.INVISIBLE);
            btn_7.setEnabled(false);
            btn_1.setText("Tujuan Pembelajaran");
            btn_2.setText("Petunjuk Kegiatan Pembelajaran");
            btn_3.setText("Materi");
            btn_4.setText("Aktivitas 1 Perubahan Lingkungan");
            btn_5.setText("Aktivitas 2 Perubahan Lingkungan");
            btn_6.setText("Aktivitas 3 Pemanasan Global");
        } else if (materi == 2) {
            btn_5.setVisibility(View.VISIBLE);
            btn_6.setVisibility(View.VISIBLE);
            btn_7.setVisibility(View.VISIBLE);
            btn_5.setEnabled(true);
            btn_6.setEnabled(true);
            btn_7.setEnabled(true);
            btn_1.setText("Tujuan Pembelajaran");
            btn_2.setText("Petunjuk Kegiatan Pembelajaran");
            btn_3.setText("Materi");
            btn_4.setText("Aktivitas 1 Pencemaran Udara");
            btn_5.setText("Aktivitas 2 Pencemaran Air");
            btn_6.setText("Aktivitas 3 Pencemaran Tanah");
            btn_7.setText("Aktivitas 4 Pencemaran Suara");
        } else if (materi == 3) {
            btn_5.setVisibility(View.INVISIBLE);
            btn_6.setVisibility(View.INVISIBLE);
            btn_7.setVisibility(View.INVISIBLE);
            btn_5.setEnabled(false);
            btn_6.setEnabled(false);
            btn_7.setEnabled(false);
            btn_1.setText("Tujuan Pembelajaran");
            btn_2.setText("Petunjuk Kegiatan Pembelajaran");
            btn_3.setText("Aktivitas 1 Pengelompokan Limbah");
            btn_4.setText("Aktivitas 2 Daur Ulang Limbah");
        }

        assert hash != null;
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "1_1.pdf");
                    startActivity(intent);
                } else if (materi == 2) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "2_1.pdf");
                    startActivity(intent);
                } else if (materi == 3) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "3_1.pdf");
                    startActivity(intent);
                }
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "1_2.pdf");
                    startActivity(intent);
                } else if (materi == 2) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "2_2.pdf");
                    startActivity(intent);
                } else if (materi == 3) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "3_2.pdf");
                    startActivity(intent);
                }
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "1_3.pdf");
                    startActivity(intent);
                } else if (materi == 2) {
                    Intent intent = new Intent(MateriActivity.this, PendahuluanActivity.class);
                    intent.putExtra(PendahuluanActivity.URL, "2_3.pdf");
                    startActivity(intent);
                } else if (materi == 3) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 31);
                        intent.putExtra(EvaluasiActivity.URL, "3_3.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 31);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "3_3.pdf");
                        startActivity(intent);
                    }
                }
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 11);
                        intent.putExtra(EvaluasiActivity.URL, "1_4.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 11);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "1_4.pdf");
                        startActivity(intent);
                    }
                } else if (materi == 2) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 21);
                        intent.putExtra(EvaluasiActivity.URL, "2_4.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 21);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "2_4.pdf");
                        startActivity(intent);
                    }
                } else if (materi == 3) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 32);
                        intent.putExtra(EvaluasiActivity.URL, "3_4.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 32);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "3_4.pdf");
                        startActivity(intent);
                    }
                }
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 12);
                        intent.putExtra(EvaluasiActivity.URL, "1_5.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 12);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "1_5.pdf");
                        startActivity(intent);
                    }
                } else if (materi == 2) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 22);
                        intent.putExtra(EvaluasiActivity.URL, "2_5.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 22);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "2_5.pdf");
                        startActivity(intent);
                    }
                }
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (materi == 1) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 13);
                        intent.putExtra(EvaluasiActivity.URL, "1_6.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 13);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "1_6.pdf");
                        startActivity(intent);
                    }
                } else if (materi == 2) {
                    if (hash.equals("guru")) {
                        Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                        intent.putExtra(EvaluasiActivity.TYPE, 23);
                        intent.putExtra(EvaluasiActivity.URL, "2_6.pdf");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                        intent.putExtra(AktivitasActivity.TYPE, 23);
                        intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                        intent.putExtra(AktivitasActivity.NAME, name);
                        intent.putExtra(AktivitasActivity.URL, "2_6.pdf");
                        startActivity(intent);
                    }
                }
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hash.equals("guru")) {
                    Intent intent = new Intent(MateriActivity.this, EvaluasiActivity.class);
                    intent.putExtra(EvaluasiActivity.TYPE, 24);
                    intent.putExtra(EvaluasiActivity.URL, "2_7.pdf");
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MateriActivity.this, AktivitasActivity.class);
                    intent.putExtra(AktivitasActivity.TYPE, 24);
                    intent.putExtra(AktivitasActivity.LOGIN_HASH, hash);
                    intent.putExtra(AktivitasActivity.NAME, name);
                    intent.putExtra(AktivitasActivity.URL, "2_7.pdf");
                    startActivity(intent);
                }
            }
        });

    }
}
