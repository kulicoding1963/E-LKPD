package com.elkpd.apps.ui;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elkpd.apps.R;
import com.elkpd.apps.WebClientActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.util.FitPolicy;

/**
 * A simple {@link Fragment} subclass.
 */
public class LKPDFragment extends Fragment {


    public LKPDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lkpd, container, false);
        PDFView pdf = root.findViewById(R.id.pdf);
        pdf.fromAsset("e_lkpd.pdf")
                .spacing(0)
                .pageFitPolicy(FitPolicy.WIDTH)
                .linkHandler(new LinkHandler() {
                    @Override
                    public void handleLinkEvent(LinkTapEvent event) {
                        Intent intent = new Intent(requireActivity(), WebClientActivity.class);
                        intent.putExtra(WebClientActivity.URL,event.getLink().getUri());
                        startActivity(intent);
                    }
                })
                .load();
        return root;
    }

}
