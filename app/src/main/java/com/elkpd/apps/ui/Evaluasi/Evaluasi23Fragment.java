package com.elkpd.apps.ui.Evaluasi;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.elkpd.apps.R;
import com.elkpd.apps.model.HasilEvaluasi;
import com.elkpd.apps.tools.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Evaluasi23Fragment extends Fragment {

    private DatabaseReference mDatabase;
    private EditText answer1,answer2,answer3,answer4,answer5;

    public Evaluasi23Fragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_evaluasi_23, container, false);
        assert getArguments() != null;
        String hash = getArguments().getString("HASH");
        final String name = getArguments().getString("NAME");
        mDatabase = FirebaseDatabase.getInstance().getReference("KegiatanDua");
        Button submit = root.findViewById(R.id.btn_submit);

        answer1 = root.findViewById(R.id.answer1);
        answer2 = root.findViewById(R.id.answer2);
        answer3 = root.findViewById(R.id.answer3);
        answer4 = root.findViewById(R.id.answer4);
        answer5 = root.findViewById(R.id.answer5);

        assert hash != null;
        if(!hash.equals("guru")){
            submit.setVisibility(View.VISIBLE);
            answer1.setEnabled(true);
            answer2.setEnabled(true);
            answer3.setEnabled(true);
            answer4.setEnabled(true);
            answer5.setEnabled(true);

        }else{
            submit.setVisibility(View.INVISIBLE);
            answer1.setEnabled(false);
            answer2.setEnabled(false);
            answer3.setEnabled(false);
            answer4.setEnabled(false);
            answer5.setEnabled(false);

            HasilEvaluasi hasilEvaluasi = getArguments().getParcelable("DATA");
            assert hasilEvaluasi != null;
            answer1.setText(hasilEvaluasi.getAnwswer1());
            answer2.setText(hasilEvaluasi.getAnwswer2());
            answer3.setText(hasilEvaluasi.getAnwswer3());
            answer4.setText(hasilEvaluasi.getAnwswer4());
            answer5.setText(hasilEvaluasi.getAnwswer5());
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jawab1 = answer1.getText().toString().trim();
                String jawab2 = answer2.getText().toString().trim();
                String jawab3 = answer3.getText().toString().trim();
                String jawab4 = answer4.getText().toString().trim();
                String jawab5 = answer5.getText().toString().trim();

                if(jawab1.isEmpty() || jawab2.isEmpty() || jawab3.isEmpty() || jawab4.isEmpty() || jawab5.isEmpty()){
                    Toast.makeText(requireContext(),"Isi jawaban yang kosong" , Toast.LENGTH_SHORT).show();
                }else{
                    sendAnswer(name,jawab1,jawab2,jawab3,jawab4,jawab5);
                }
            }
        });

        return root;
    }

    private void sendAnswer(String users,String jwb1,String jwb2,String jwb3,String jwb4,String jwb5) {
        Utils.showDialog(requireContext());
        HashMap<String, String> maps = new HashMap<>();
        maps.put("user", users);
        maps.put("answer1", jwb1);
        maps.put("answer2", jwb2);
        maps.put("answer3", jwb3);
        maps.put("answer4", jwb4);
        maps.put("answer5", jwb5);
        maps.put("status", "0");
        maps.put("nilai", "");
        mDatabase.child("AktivitasTiga").push().setValue(maps).addOnSuccessListener(new OnSuccessListener<Void>() {
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
