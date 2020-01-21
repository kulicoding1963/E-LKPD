package com.elkpd.apps.ui.Evaluasi;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Evaluasi32Fragment extends Fragment {

    private EditText answer1, answer2, answer4;
    private ImageView answer3, answer5, answer6, answer7, answer8, answer9, answer10, answer11, answer12, answer13, answer14, answer15, answer16, answer17, answer18, answer19, answer20, answer21, answer22, answer23, answer24, answer25, answer26, answer27;
    private Uri[] uri;
    private ImageView[] images;
    private StorageReference storageRef;
    private DatabaseReference mDatabase;

    private boolean cek = true;
    private int i = 0;

    public Evaluasi32Fragment() {}


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
        images[3] = root.findViewById(R.id.answer6);
        images[4] = root.findViewById(R.id.answer7);
        images[5] = root.findViewById(R.id.answer8);
        images[6] = root.findViewById(R.id.answer9);
        images[7] = root.findViewById(R.id.answer10);
        images[8] = root.findViewById(R.id.answer11);
        images[9] = root.findViewById(R.id.answer12);
        images[10] = root.findViewById(R.id.answer13);
        images[11] = root.findViewById(R.id.answer14);
        images[12] = root.findViewById(R.id.answer15);
        images[13] = root.findViewById(R.id.answer16);
        images[14] = root.findViewById(R.id.answer17);
        images[15] = root.findViewById(R.id.answer18);
        images[16] = root.findViewById(R.id.answer19);
        images[17] = root.findViewById(R.id.answer20);
        images[18] = root.findViewById(R.id.answer21);
        images[19] = root.findViewById(R.id.answer22);
        images[20] = root.findViewById(R.id.answer23);
        images[21] = root.findViewById(R.id.answer24);
        images[22] = root.findViewById(R.id.answer25);
        images[23] = root.findViewById(R.id.answer26);
        images[24] = root.findViewById(R.id.answer27);

        Button submit = root.findViewById(R.id.btn_submit);

        mDatabase = FirebaseDatabase.getInstance().getReference("KegiatanTiga");
        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://e-lkpd.appspot.com");

        assert hash != null;
        if (!hash.equals("guru")) {
            submit.setVisibility(View.VISIBLE);
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
        } else {
            submit.setVisibility(View.INVISIBLE);
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
            answer5.setEnabled(true);
            answer6.setEnabled(true);

            final HasilEvaluasi hasilEvaluasi = getArguments().getParcelable("DATA");
            assert hasilEvaluasi != null;
            answer1.setText(hasilEvaluasi.getAnwswer1());
            answer2.setText(hasilEvaluasi.getAnwswer2());
            answer4.setText(hasilEvaluasi.getAnwswer3());

            Utils.showDialog(requireContext());
            while (cek){
                StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasSatu").child(hasilEvaluasi.getId() +i+".jpg");
                pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(requireActivity())
                                .load(uri)
                                .error(R.drawable.ic_error)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(images[i]);
                        i++;
                        if(i == 24){
                            cek = false;
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            if(cek){
                Utils.hideDialog();
            }
        }

        images[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(1);
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

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                Toast.makeText(requireContext(), "Thanks for allowing permission", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        }
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

    private void showFileChooser(int position) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), position);
    }
}
