package com.elkpd.apps.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elkpd.apps.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class PetunjukFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_petunjuk, container, false);
        PDFView pdf = root.findViewById(R.id.pdf);
        pdf.fromAsset("petunjuk.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load();
        return root;
    }
}