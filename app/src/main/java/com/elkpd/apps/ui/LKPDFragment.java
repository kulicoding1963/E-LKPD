package com.elkpd.apps.ui;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elkpd.apps.R;
import com.elkpd.apps.WebClientActivity;
import com.elkpd.apps.tools.Utils;
import com.elkpd.apps.tools.ViewAnimation;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class LKPDFragment extends Fragment {

    private FloatingActionButton fabAdd, fabDownload, fabShare;
    private boolean isRotate = false;

    public LKPDFragment() {
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
                        intent.putExtra(WebClientActivity.URL, event.getLink().getUri());
                        startActivity(intent);
                    }
                })
                .load();

        fabAdd = root.findViewById(R.id.fabAdd);
        fabDownload = root.findViewById(R.id.fabDownload);
        fabShare = root.findViewById(R.id.fabShare);

        ViewAnimation.init(fabDownload);
        ViewAnimation.init(fabShare);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRotate = ViewAnimation.rotateFab(view, !isRotate);
                if (isRotate) {
                    ViewAnimation.showIn(fabDownload);
                    ViewAnimation.showIn(fabShare);
                } else {
                    ViewAnimation.showOut(fabDownload);
                    ViewAnimation.showOut(fabShare);
                }
            }
        });

        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    Utils.showDialog(requireContext());
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    StorageReference storageRef = storage.getReferenceFromUrl("gs://e-lkpd.appspot.com");
                    StorageReference islandRef = storageRef.child("Konten").child("E-LKPD Perubahan Lingkungan.pdf");

                    File rootPath = new File(Environment.getExternalStorageDirectory(), "E-LKPD Perubahan Lingkungan");
                    if (!rootPath.exists()) {
                        rootPath.mkdirs();
                    }

                    final File localFile = new File(rootPath, "E-LKPD Perubahan Lingkungan.pdf");

                    islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.e("firebase ", ";local tem file created  created " + localFile.toString());
                            Utils.hideDialog();
                            Toast.makeText(requireContext(), "Berhasil mengunduh file", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.e("firebase ", ";local tem file not created  created " + exception.toString());
                            Utils.hideDialog();
                            Toast.makeText(requireContext(), "Gagal mengunduh file", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    requestPermission();
                }
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission()){
                    File apkStorage = new File(Environment.getExternalStorageDirectory(), "E-LKPD Perubahan Lingkungan");
                    if (!apkStorage.exists()) {
                        Toast.makeText(requireActivity(), "Anda belum mengunduh file ", Toast.LENGTH_SHORT).show();
                    } else {
                        File outputFile = new File(Environment.getExternalStorageDirectory() + "/E-LKPD Perubahan Lingkungan", "E-LKPD Perubahan Lingkungan.pdf");
                        Uri uri = Uri.fromFile(outputFile);
                        Intent share = new Intent();
                        share.setType("application/pdf");
                        share.putExtra(Intent.EXTRA_STREAM, uri);
                        share.putExtra(Intent.EXTRA_SUBJECT,
                                "Sharing File E-LKPD Perubahan Lingkungan...");
                        share.putExtra(Intent.EXTRA_TEXT, "Sharing File E-LKPD Perubahan Lingkungan...");
                        startActivity(Intent.createChooser(share, "Share File E-LKPD Perubahan Lingkungan"));
                    }
                }else{
                    requestPermission();
                }
            }
        });
        return root;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(requireContext(), " Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            int PERMISSION_REQUEST_CODE = 1;
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
