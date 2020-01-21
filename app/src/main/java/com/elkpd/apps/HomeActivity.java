package com.elkpd.apps;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import com.elkpd.apps.ui.BerandaFragment;
import com.elkpd.apps.ui.DaftarPustakaFragment;
import com.elkpd.apps.ui.HomeFragment;
import com.elkpd.apps.ui.KegiatanPembelajaranFragment;
import com.elkpd.apps.ui.KuisFragment;
import com.elkpd.apps.ui.KurikulumFragment;
import com.elkpd.apps.ui.LKPDFragment;
import com.elkpd.apps.ui.PetunjukFragment;
import com.elkpd.apps.ui.TentangFragment;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private boolean doubleBackToExitPressedOnce = false;
    public static boolean isLogged = false;
    private String type;
    private String name;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);
        final TextView titleBar = findViewById(R.id.titleBar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        titleBar.setText(getResources().getString(R.string.beranda));
        Fragment fragment = new BerandaFragment();
        displaySelected(fragment);

        View headerView = navigationView.getHeaderView(0);
        LinearLayout beranda = headerView.findViewById(R.id.beranda);
        LinearLayout welcome = headerView.findViewById(R.id.welcome);
        LinearLayout tentang = headerView.findViewById(R.id.tentang);
        LinearLayout petunjuk = headerView.findViewById(R.id.petunjuk);
        LinearLayout kurikulum = headerView.findViewById(R.id.kurikulum);
        LinearLayout kegiatan = headerView.findViewById(R.id.kegiatan);
        LinearLayout kuis = headerView.findViewById(R.id.kuis);
        LinearLayout daftar = headerView.findViewById(R.id.daftar_pustaka);
        LinearLayout e_lkpd = headerView.findViewById(R.id.e_lkpd);

        beranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.beranda));
                Fragment fragment = new BerandaFragment();
                displaySelected(fragment);
            }
        });
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.welcome));
                Fragment fragment = new HomeFragment();
                displaySelected(fragment);
            }
        });

        tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.tentang));
                Fragment fragment = new TentangFragment();
                displaySelected(fragment);
            }
        });

        petunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.petunjuk_penggunaan));
                Fragment fragment = new PetunjukFragment();
                displaySelected(fragment);
            }
        });

        kurikulum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.kurikulum));
                Fragment fragment = new KurikulumFragment();
                displaySelected(fragment);
            }
        });

        kegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogged){
                    titleBar.setText(getResources().getString(R.string.kegiatan_pembelajaran));
                    Fragment fragment = new KegiatanPembelajaranFragment();
                    displaySelected(fragment);
                }else{
                    showLoginDialog();
                }
            }
        });

        kuis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isLogged){
                    titleBar.setText(getResources().getString(R.string.kuis));
                    Fragment fragment = new KuisFragment();
                    displaySelected(fragment);
                }else {
                    showLoginDialog();
                }
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.daftar_pustaka));
                Fragment fragment = new DaftarPustakaFragment();
                displaySelected(fragment);
            }
        });

        e_lkpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleBar.setText(getResources().getString(R.string.e_lkpd));
                Fragment fragment = new LKPDFragment();
                displaySelected(fragment);
            }
        });

    }

    public void displaySelected(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        drawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.login_guru) {
            if(isLogged){
                Toast.makeText(getBaseContext(), "Anda suda login sebagai "+type, Toast.LENGTH_SHORT).show();
            }else{
                showLoginDialog();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Lagi Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showLoginDialog() {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_login);
        dialog.setCancelable(true);

        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final EditText edUsername = dialog.findViewById(R.id.ed_username);
        final EditText edPassword = dialog.findViewById(R.id.ed_password);
        Button btnLoginGuru = dialog.findViewById(R.id.btn_login_guru);
        Button btnLoginSiswa = dialog.findViewById(R.id.btn_login_siswa);

        btnLoginSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edUsername.getText().toString().equals("") || !edPassword.getText().toString().equals("")) {
                    dialog.dismiss();
                    setType("siswa");
                    setName(edUsername.getText().toString().trim());
                    isLogged = true;
                } else {
                    Toast.makeText(getBaseContext(), "Nama atau NIS tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLoginGuru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edUsername.getText().toString().equals("") || !edPassword.getText().toString().equals("")) {
                    if (edUsername.getText().toString().equals("admin") && edPassword.getText().toString().equals("lkpd2020")) {
                        dialog.dismiss();
                        setType("guru");
                        setName("admin");
                        isLogged = true;
                    } else {
                        Toast.makeText(getBaseContext(), "Username atau password salah!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public String getType() {
        return type;
    }

    public  void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
