package com.elkpd.apps.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkpd.apps.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarPustakaFragment extends Fragment {


    public DaftarPustakaFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_daftar_pustaka, container, false);
        PDFView pdf = root.findViewById(R.id.pdf);
        pdf.fromAsset("pustaka.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        return root;
    }

}
