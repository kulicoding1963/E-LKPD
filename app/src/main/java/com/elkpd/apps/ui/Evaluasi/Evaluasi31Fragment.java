package com.elkpd.apps.ui.Evaluasi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Evaluasi31Fragment extends Fragment {


    private Uri uri1, uri2, uri3;
    private EditText answer1, answer2, answer4, answer6;
    private ImageView answer3, answer5, answer7;
    private StorageReference storageRef;
    private DatabaseReference mDatabase;


    public Evaluasi31Fragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_evaluasi_31, container, false);

        assert getArguments() != null;
        String hash = getArguments().getString("HASH");
        final String name = getArguments().getString("NAME");



        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        answer3 = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);
        answer5 = root.findViewById(R.id.answer5);
        answer6 = root.findViewById(R.id.answer6);
        answer7 = root.findViewById(R.id.answer7);
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
            answer5.setEnabled(true);
            answer6.setEnabled(true);
        } else {
            submit.setVisibility(View.INVISIBLE);
            answer1.setEnabled(false);
            answer2.setEnabled(false);
            answer3.setEnabled(false);
            answer4.setEnabled(false);
            answer5.setEnabled(false);
            answer6.setEnabled(false);

            final HasilEvaluasi hasilEvaluasi = getArguments().getParcelable("DATA");
            assert hasilEvaluasi != null;
            answer1.setText(hasilEvaluasi.getAnwswer1());
            answer2.setText(hasilEvaluasi.getAnwswer2());
            answer4.setText(hasilEvaluasi.getAnwswer3());
            answer6.setText(hasilEvaluasi.getAnwswer4());

            Utils.showDialog(requireContext());
            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasSatu").child(hasilEvaluasi.getId() + "1.jpg");
            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(requireActivity())
                            .load(uri)
                            .error(R.drawable.ic_error)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(answer3);

                    StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasSatu").child(hasilEvaluasi.getId() + "2.jpg");
                    pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(requireActivity())
                                    .load(uri)
                                    .error(R.drawable.ic_error)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(answer5);

                            StorageReference pathReference = storageRef.child("KegiatanTiga").child("AktivitasSatu").child(hasilEvaluasi.getId() + "3.jpg");
                            pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Glide.with(requireActivity())
                                            .load(uri)
                                            .error(R.drawable.ic_error)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .skipMemoryCache(true)
                                            .into(answer5);
                                    Utils.hideDialog();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(1);
                } else {
                    requestPermission();
                }
            }
        });

        answer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(2);
                } else {
                    requestPermission();
                }
            }
        });

        answer7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    showFileChooser(3);
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
                String jawab6 = answer6.getText().toString().trim();

                if (jawab1.isEmpty() || jawab2.isEmpty() || uri1 == null || jawab4.isEmpty() || uri2 == null || jawab6.isEmpty() || uri3== null) {
                    Toast.makeText(requireContext(), "Isi jawaban yang kosong", Toast.LENGTH_SHORT).show();
                } else {
                    sendAnswer(name, jawab1, jawab2, jawab4, jawab6, uri1, uri2,uri3);
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

    private void sendAnswer(final String users, final String jwb1, final String jwb2,
                            final String jwb3, final String jwb4, final Uri image1, final Uri image2, final Uri image3) {
        Utils.showDialog(requireContext());
        final String key = mDatabase.child("AktivatiasSatu").push().getKey();
        StorageReference path = storageRef.child("AktivitasSatu").child(key + "1.jpg");
        UploadTask uploadTask = path.putFile(image1);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageReference path = storageRef.child("AktivitasSatu").child(key + "2.jpg");
                UploadTask uploadTask = path.putFile(image2);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        StorageReference path = storageRef.child("AktivitasSatu").child(key + "3.jpg");
                        UploadTask uploadTask = path.putFile(image3);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                HashMap<String, String> maps = new HashMap<>();
                                maps.put("user", users);
                                maps.put("answer1", jwb1);
                                maps.put("answer2", jwb2);
                                maps.put("answer3", jwb3);
                                maps.put("answer4", jwb4);
                                maps.put("status", "0");
                                maps.put("nilai", "");
                                assert key != null;
                                mDatabase.child("KegiatanTiga").child("AktivatiasSatu").child(key).setValue(maps).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                Utils.hideDialog();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Utils.hideDialog();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Utils.hideDialog();
            }
        });
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
            if (requestCode == 1) {
                uri1 = Uri.fromFile(Utils.saveBitmapToFile(new File(filePath)));
                Utils.displayImageOriginal(requireActivity(), answer3, uri1);
            } else if (requestCode == 2) {
                uri2 = Uri.fromFile(Utils.saveBitmapToFile(new File(filePath)));
                Utils.displayImageOriginal(requireActivity(), answer5, uri2);
            }else if (requestCode == 3) {
                uri3 = Uri.fromFile(Utils.saveBitmapToFile(new File(filePath)));
                Utils.displayImageOriginal(requireActivity(), answer7, uri3);
            }
        }
    }

}
