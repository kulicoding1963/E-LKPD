package com.elkpd.apps.ui.Evaluasi;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.elkpd.apps.R;
import com.elkpd.apps.model.HasilEvaluasi;
import com.elkpd.apps.tools.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

public class Evaluasi32Fragment extends Fragment {

    private EditText answer1, answer2, answer4;
    private Uri[] uri = new Uri[28];
    private ImageView[] images = new ImageView[28];
    private StorageReference storageRef;
    private DatabaseReference mDatabase;

    public Evaluasi32Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_evaluasi_32, container, false);

        assert getArguments() != null;
        String hash = getArguments().getString("HASH");
        final String name = getArguments().getString("NAME");

        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        images[0] = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);
        images[1] = root.findViewById(R.id.answer5);
        images[2] = root.findViewById(R.id.answer6);
        images[3] = root.findViewById(R.id.answer7);
        images[4] = root.findViewById(R.id.answer8);
        images[5] = root.findViewById(R.id.answer9);
        images[6] = root.findViewById(R.id.answer10);
        images[7] = root.findViewById(R.id.answer11);
        images[8] = root.findViewById(R.id.answer12);
        images[9] = root.findViewById(R.id.answer13);
        images[10] = root.findViewById(R.id.answer14);
        images[11] = root.findViewById(R.id.answer15);
        images[12] = root.findViewById(R.id.answer16);
        images[13] = root.findViewById(R.id.answer17);
        images[14] = root.findViewById(R.id.answer18);
        images[15] = root.findViewById(R.id.answer19);
        images[16] = root.findViewById(R.id.answer20);
        images[17] = root.findViewById(R.id.answer21);
        images[18] = root.findViewById(R.id.answer22);
        images[19] = root.findViewById(R.id.answer23);
        images[20] = root.findViewById(R.id.answer24);
        images[21] = root.findViewById(R.id.answer25);
        images[22] = root.findViewById(R.id.answer26);
        images[23] = root.findViewById(R.id.answer27);
        images[24] = root.findViewById(R.id.answer28);
        images[25] = root.findViewById(R.id.answer29);
        images[26] = root.findViewById(R.id.answer30);
        images[27] = root.findViewById(R.id.answer31);

        Button submit = root.findViewById(R.id.btn_submit);

        mDatabase = FirebaseDatabase.getInstance().getReference("KegiatanTiga");
        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://e-lkpd.appspot.com");

        assert hash != null;
        if (!hash.equals("guru")) {
            submit.setVisibility(View.VISIBLE);
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer4.setEnabled(true);
            for (int x = 0; x < 28; x++) {
                images[x].setEnabled(true);
            }
        } else {
            submit.setVisibility(View.INVISIBLE);
            answer1.setEnabled(false);
            answer2.setEnabled(false);
            answer4.setEnabled(false);
            for (int x = 0; x < 28; x++) {
                images[x].setEnabled(false);
            }

            final HasilEvaluasi hasilEvaluasi = getArguments().getParcelable("DATA");
            assert hasilEvaluasi != null;
            answer1.setText(hasilEvaluasi.getAnwswer1());
            answer2.setText(hasilEvaluasi.getAnwswer2());
            answer4.setText(hasilEvaluasi.getAnwswer3());

            Utils.showDialog(requireContext());
            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "0.jpg");
            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(requireContext())
                            .load(uri)
                            .error(R.drawable.ic_error)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(true)
                            .into(images[0]);
                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "1.jpg");
                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(requireContext())
                                    .load(uri)
                                    .error(R.drawable.ic_error)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .skipMemoryCache(true)
                                    .into(images[1]);
                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "2.jpg");
                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(requireContext())
                                            .load(uri)
                                            .error(R.drawable.ic_error)
                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                            .skipMemoryCache(true)
                                            .into(images[2]);
                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "3.jpg");
                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            Glide.with(requireContext())
                                                    .load(uri)
                                                    .error(R.drawable.ic_error)
                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                    .skipMemoryCache(true)
                                                    .into(images[3]);
                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "4.jpg");
                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Glide.with(requireContext())
                                                            .load(uri)
                                                            .error(R.drawable.ic_error)
                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                            .skipMemoryCache(true)
                                                            .into(images[4]);
                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "5.jpg");
                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                        @Override
                                                        public void onSuccess(Uri uri) {
                                                            Glide.with(requireContext())
                                                                    .load(uri)
                                                                    .error(R.drawable.ic_error)
                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                    .skipMemoryCache(true)
                                                                    .into(images[5]);
                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "6.jpg");
                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                @Override
                                                                public void onSuccess(Uri uri) {
                                                                    Glide.with(requireContext())
                                                                            .load(uri)
                                                                            .error(R.drawable.ic_error)
                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                            .skipMemoryCache(true)
                                                                            .into(images[6]);
                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "7.jpg");
                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                        @Override
                                                                        public void onSuccess(Uri uri) {
                                                                            Glide.with(requireContext())
                                                                                    .load(uri)
                                                                                    .error(R.drawable.ic_error)
                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                    .skipMemoryCache(true)
                                                                                    .into(images[7]);
                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "8.jpg");
                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                @Override
                                                                                public void onSuccess(Uri uri) {
                                                                                    Glide.with(requireContext())
                                                                                            .load(uri)
                                                                                            .error(R.drawable.ic_error)
                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                            .skipMemoryCache(true)
                                                                                            .into(images[8]);
                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "9.jpg");
                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                        @Override
                                                                                        public void onSuccess(Uri uri) {
                                                                                            Glide.with(requireContext())
                                                                                                    .load(uri)
                                                                                                    .error(R.drawable.ic_error)
                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                    .skipMemoryCache(true)
                                                                                                    .into(images[9]);
                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "10.jpg");
                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                @Override
                                                                                                public void onSuccess(Uri uri) {
                                                                                                    Glide.with(requireContext())
                                                                                                            .load(uri)
                                                                                                            .error(R.drawable.ic_error)
                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                            .skipMemoryCache(true)
                                                                                                            .into(images[10]);
                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "11.jpg");
                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                        @Override
                                                                                                        public void onSuccess(Uri uri) {
                                                                                                            Glide.with(requireContext())
                                                                                                                    .load(uri)
                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                    .skipMemoryCache(true)
                                                                                                                    .into(images[11]);
                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "12.jpg");
                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                @Override
                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                    Glide.with(requireContext())
                                                                                                                            .load(uri)
                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                            .skipMemoryCache(true)
                                                                                                                            .into(images[12]);

                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "13.jpg");
                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                        @Override
                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                            Glide.with(requireContext())
                                                                                                                                    .load(uri)
                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                    .into(images[13]);
                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "14.jpg");
                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                @Override
                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                    Glide.with(requireContext())
                                                                                                                                            .load(uri)
                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                            .into(images[14]);
                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "15.jpg");
                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                        @Override
                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                    .load(uri)
                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                    .into(images[15]);
                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "16.jpg");
                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                @Override
                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                            .load(uri)
                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                            .into(images[16]);
                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "17.jpg");
                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                    .load(uri)
                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                    .into(images[17]);
                                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "18.jpg");
                                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                                            .load(uri)
                                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                                            .into(images[18]);
                                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "19.jpg");
                                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                                    .load(uri)
                                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                                    .into(images[19]);
                                                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "20.jpg");
                                                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                                                            .load(uri)
                                                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                                                            .into(images[20]);
                                                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "21.jpg");
                                                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                        @Override
                                                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                                                    .load(uri)
                                                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                                                    .into(images[21]);
                                                                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "22.jpg");
                                                                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                @Override
                                                                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                                                                            .load(uri)
                                                                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                                                                            .into(images[22]);
                                                                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "23.jpg");
                                                                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                        @Override
                                                                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                                                                    .load(uri)
                                                                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                                                                    .into(images[23]);
                                                                                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "24.jpg");
                                                                                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                                                                                            .load(uri)
                                                                                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                                                                                            .into(images[24]);
                                                                                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "25.jpg");
                                                                                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                                                                                    .load(uri)
                                                                                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                                                                                    .into(images[25]);
                                                                                                                                                                                                                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "26.jpg");
                                                                                                                                                                                                                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                public void onSuccess(Uri uri) {
                                                                                                                                                                                                                                    Glide.with(requireContext())
                                                                                                                                                                                                                                            .load(uri)
                                                                                                                                                                                                                                            .error(R.drawable.ic_error)
                                                                                                                                                                                                                                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                                                            .skipMemoryCache(true)
                                                                                                                                                                                                                                            .into(images[26]);
                                                                                                                                                                                                                                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasDua").child(hasilEvaluasi.getId() + "27.jpg");
                                                                                                                                                                                                                                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                        public void onSuccess(Uri uri) {
                                                                                                                                                                                                                                            Glide.with(requireContext())
                                                                                                                                                                                                                                                    .load(uri)
                                                                                                                                                                                                                                                    .error(R.drawable.ic_error)
                                                                                                                                                                                                                                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                                                                                                                                                                                                                    .skipMemoryCache(true)
                                                                                                                                                                                                                                                    .into(images[27]);
                                                                                                                                                                                                                                            Utils.hideDialog();

                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    });

                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            });

                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                                        @Override
                                                                                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    });

                                                                                                                                                                                                                }
                                                                                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                                @Override
                                                                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                                }
                                                                                                                                                                                                            });

                                                                                                                                                                                                        }
                                                                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                        @Override
                                                                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                        }
                                                                                                                                                                                                    });

                                                                                                                                                                                                }
                                                                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                                @Override
                                                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                                }
                                                                                                                                                                                            });

                                                                                                                                                                                        }
                                                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                        @Override
                                                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                        }
                                                                                                                                                                                    });

                                                                                                                                                                                }
                                                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                                @Override
                                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                                }
                                                                                                                                                                            });

                                                                                                                                                                        }
                                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                        @Override
                                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                        }
                                                                                                                                                                    });

                                                                                                                                                                }
                                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                                }
                                                                                                                                                            });

                                                                                                                                                        }
                                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                        @Override
                                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                                            Utils.hideDialog();
                                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                        }
                                                                                                                                                    });

                                                                                                                                                }
                                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                                @Override
                                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                                    Utils.hideDialog();
                                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                                }
                                                                                                                                            });

                                                                                                                                        }
                                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                        @Override
                                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                                            Utils.hideDialog();
                                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                        }
                                                                                                                                    });

                                                                                                                                }
                                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                                @Override
                                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                                    Utils.hideDialog();
                                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                                }
                                                                                                                            });

                                                                                                                        }
                                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                                        @Override
                                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                                            Utils.hideDialog();
                                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                        }
                                                                                                                    });

                                                                                                                }
                                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                                @Override
                                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                                    Utils.hideDialog();
                                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                                }
                                                                                                            });

                                                                                                        }
                                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                                        @Override
                                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                                            Utils.hideDialog();
                                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                        }
                                                                                                    });

                                                                                                }
                                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                                @Override
                                                                                                public void onFailure(@NonNull Exception e) {
                                                                                                    Utils.hideDialog();
                                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                                }
                                                                                            });
                                                                                        }
                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                        @Override
                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                            Utils.hideDialog();
                                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                                }
                                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                                @Override
                                                                                public void onFailure(@NonNull Exception e) {
                                                                                    Utils.hideDialog();
                                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                                }
                                                                            });
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Utils.hideDialog();
                                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Utils.hideDialog();
                                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Utils.hideDialog();
                                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Utils.hideDialog();
                                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Utils.hideDialog();
                                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Utils.hideDialog();
                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Utils.hideDialog();
                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Utils.hideDialog();
                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        images[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(0);
                } else {
                    requestPermission();
                }
            }
        });

        images[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(1);
                } else {
                    requestPermission();
                }
            }
        });

        images[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(2);
                } else {
                    requestPermission();
                }
            }
        });

        images[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(3);
                } else {
                    requestPermission();
                }
            }
        });

        images[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(4);
                } else {
                    requestPermission();
                }
            }
        });

        images[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(5);
                } else {
                    requestPermission();
                }
            }
        });

        images[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(6);
                } else {
                    requestPermission();
                }
            }
        });

        images[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(7);
                } else {
                    requestPermission();
                }
            }
        });

        images[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(8);
                } else {
                    requestPermission();
                }
            }
        });

        images[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(9);
                } else {
                    requestPermission();
                }
            }
        });

        images[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(10);
                } else {
                    requestPermission();
                }
            }
        });
        images[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(11);
                } else {
                    requestPermission();
                }
            }
        });

        images[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(12);
                } else {
                    requestPermission();
                }
            }
        });

        images[13].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(13);
                } else {
                    requestPermission();
                }
            }
        });

        images[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(14);
                } else {
                    requestPermission();
                }
            }
        });

        images[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(15);
                } else {
                    requestPermission();
                }
            }
        });

        images[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(16);
                } else {
                    requestPermission();
                }
            }
        });

        images[17].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(17);
                } else {
                    requestPermission();
                }
            }
        });

        images[18].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(18);
                } else {
                    requestPermission();
                }
            }
        });

        images[19].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(19);
                } else {
                    requestPermission();
                }
            }
        });

        images[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(20);
                } else {
                    requestPermission();
                }
            }
        });

        images[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(21);
                } else {
                    requestPermission();
                }
            }
        });

        images[22].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(22);
                } else {
                    requestPermission();
                }
            }
        });

        images[23].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(23);
                } else {
                    requestPermission();
                }
            }
        });

        images[24].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(24);
                } else {
                    requestPermission();
                }
            }
        });

        images[25].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(25);
                } else {
                    requestPermission();
                }
            }
        });

        images[26].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(26);
                } else {
                    requestPermission();
                }
            }
        });

        images[27].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(27);
                } else {
                    requestPermission();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jawab1 = answer1.getText().toString().trim();
                String jawab2 = answer2.getText().toString().trim();
                String jawab4 = answer4.getText().toString().trim();
                boolean result = false;
                for (int x = 0; x < 28; x++) {
                    if (uri[x] == null) {
                        result = true;
                        break;
                    }
                }
                if (jawab1.isEmpty() || jawab2.isEmpty() || jawab4.isEmpty() || result) {
                    Toast.makeText(requireContext(), "Isi jawaban yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    sendAnswer(name, jawab1, jawab2, jawab4, uri);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Toast.makeText(requireContext(), "Thanks for allowing permission", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        }
        return root;
    }

    private void sendAnswer(final String users, final String jwb1, final String jwb2, final String jwb3, Uri[] imageUri) {
        Utils.showDialog(requireContext());
        final String key = mDatabase.child("AktivitasDua").push().getKey();
        for (int x = 0; x < imageUri.length; x++) {
            StorageReference path = storageRef.child("KegiatanTiga").child("AktivitasDua").child(key + x + ".jpg");
            UploadTask uploadTask = path.putFile(imageUri[x]);
            final int finalX = x;
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (finalX == 23) {
                        HashMap<String, String> maps = new HashMap<>();
                        maps.put("user", users);
                        maps.put("answer1", jwb1);
                        maps.put("answer2", jwb2);
                        maps.put("answer3", jwb3);
                        maps.put("status", "0");
                        maps.put("nilai", "");
                        assert key != null;
                        mDatabase.child("AktivitasDua").child(key).setValue(maps).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(requireContext(), "Berhasil mengirim data", Toast.LENGTH_SHORT).show();
                                Utils.hideDialog();
                                requireActivity().finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                Utils.hideDialog();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Utils.hideDialog();
                }
            });
        }
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

    private void showFileChooser(int position) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), position);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1 && data != null && data.getData() != null) {
            Uri convert = data.getData();
            String filePath = Utils.getPathFromUri(requireContext(), convert);
            assert filePath != null;
            uri[requestCode] = Uri.fromFile(Utils.saveBitmapToFile(new File(filePath)));
            Utils.displayImageOriginal(requireActivity(), images[requestCode], uri[requestCode], images[requestCode].getWidth(), images[requestCode].getWidth() * 2);
        }
    }
}
