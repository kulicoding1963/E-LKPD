package com.elkpd.apps.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.elkpd.apps.HomeActivity;
import com.elkpd.apps.MateriActivity;
import com.elkpd.apps.R;

import java.util.Objects;

public class KegiatanPembelajaranFragment extends Fragment {


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kegiatan_pembelajaran, container, false);
        Button btn_materi_1 = root.findViewById(R.id.btn_materi_1);
        Button btn_materi_2 = root.findViewById(R.id.btn_materi_2);
        Button btn_materi_3 = root.findViewById(R.id.btn_materi_3);

        final String type = ((HomeActivity) Objects.requireNonNull(getActivity())).getType();
        final String name = ((HomeActivity) Objects.requireNonNull(getActivity())).getName();


        btn_materi_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MateriActivity.class);
                intent.putExtra(MateriActivity.MATERI,1);
                intent.putExtra(MateriActivity.LOGIN_HASH,type);
                intent.putExtra(MateriActivity.NAME,name);
                startActivity(intent);
            }
        });
        btn_materi_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MateriActivity.class);
                intent.putExtra(MateriActivity.MATERI,2);
                intent.putExtra(MateriActivity.LOGIN_HASH,type);
                intent.putExtra(MateriActivity.NAME,name);
                startActivity(intent);
            }
        });
        btn_materi_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), MateriActivity.class);
                intent.putExtra(MateriActivity.MATERI,3);
                intent.putExtra(MateriActivity.LOGIN_HASH,type);
                intent.putExtra(MateriActivity.NAME,name);
                startActivity(intent);
            }
        });
        return root;
    }
}