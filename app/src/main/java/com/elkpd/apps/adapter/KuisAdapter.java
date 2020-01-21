package com.elkpd.apps.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elkpd.apps.R;
import com.elkpd.apps.model.Kuis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KuisAdapter extends RecyclerView.Adapter<KuisAdapter.KuisHolder> {
    private List<Kuis> listGetKuis = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;
    private int nilai = 0;

    public KuisAdapter(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void replaceAll(List<Kuis> items) {
        listGetKuis.clear();
        listGetKuis = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KuisHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_kuis, viewGroup, false);
        return new KuisHolder(view);
    }

    @Override
    public void onBindViewHolder(KuisHolder holder, int position) {
        holder.bind(listGetKuis.get(position), listGetKuis.get(position).getJawaban().get(0));
    }

    @Override
    public int getItemCount() {
        return listGetKuis.size();
    }

    class KuisHolder extends RecyclerView.ViewHolder {
        TextView nomer, soal1, soal2, soal3;
        RadioButton radioA, radioB, radioC, radioD, radioE;

        KuisHolder(View itemView) {
            super(itemView);
            nomer = itemView.findViewById(R.id.txtNomer);
            soal1 = itemView.findViewById(R.id.txtSoal1);
            soal2 = itemView.findViewById(R.id.txtSoal2);
            soal3 = itemView.findViewById(R.id.txtSoal3);
            radioA = itemView.findViewById(R.id.radioA);
            radioB = itemView.findViewById(R.id.radioB);
            radioC = itemView.findViewById(R.id.radioC);
            radioD = itemView.findViewById(R.id.radioD);
            radioE = itemView.findViewById(R.id.radioE);
        }

        void bind(final Kuis item, final String answer) {
            int nomor = getAdapterPosition() + 1;
            nomer.setText(nomor + ". ");
            soal1.setText(item.getSoal_1());
            soal2.setText(item.getSoal_2());
            soal3.setText(item.getSoal_3());
            if (item.getType().equals("0")) {
                soal2.setVisibility(View.GONE);
                soal3.setVisibility(View.GONE);
            } else {
                soal2.setVisibility(View.VISIBLE);
                soal3.setVisibility(View.VISIBLE);
            }

            Collections.shuffle(item.getJawaban());
            radioA.setText(item.getJawaban().get(0));
            radioB.setText(item.getJawaban().get(1));
            radioC.setText(item.getJawaban().get(2));
            radioD.setText(item.getJawaban().get(3));
            radioE.setText(item.getJawaban().get(4));
            radioA.setChecked(false);
            radioB.setChecked(false);
            radioC.setChecked(false);
            radioD.setChecked(false);
            radioE.setChecked(false);

            radioA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioA.setChecked(true);
                    radioB.setChecked(false);
                    radioC.setChecked(false);
                    radioD.setChecked(false);
                    radioE.setChecked(false);

                    System.out.println(answer);
                    if (radioA.getText().toString().equals(answer)) {
                        onItemClickCallback.onItemClicked(1, getAdapterPosition());
                    } else {
                        onItemClickCallback.onItemClicked(0, getAdapterPosition());
                    }
                }
            });

            radioB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioA.setChecked(false);
                    radioB.setChecked(true);
                    radioC.setChecked(false);
                    radioD.setChecked(false);
                    radioE.setChecked(false);

                    System.out.println(answer);
                    if (radioB.getText().toString().equals(answer)) {
                        onItemClickCallback.onItemClicked(1, getAdapterPosition());
                    } else {
                        onItemClickCallback.onItemClicked(0, getAdapterPosition());
                    }
                }
            });

            radioC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioA.setChecked(false);
                    radioB.setChecked(false);
                    radioC.setChecked(true);
                    radioD.setChecked(false);
                    radioE.setChecked(false);

                    System.out.println(answer);
                    if (radioC.getText().toString().equals(answer)) {
                        onItemClickCallback.onItemClicked(1, getAdapterPosition());
                    } else {
                        onItemClickCallback.onItemClicked(0, getAdapterPosition());
                    }
                }
            });

            radioD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioA.setChecked(false);
                    radioB.setChecked(false);
                    radioC.setChecked(false);
                    radioD.setChecked(true);
                    radioE.setChecked(false);

                    System.out.println(answer);
                    if (radioD.getText().toString().equals(answer)) {
                        onItemClickCallback.onItemClicked(1, getAdapterPosition());
                    } else {
                        onItemClickCallback.onItemClicked(0, getAdapterPosition());
                    }
                }
            });

            radioE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    radioA.setChecked(false);
                    radioB.setChecked(false);
                    radioC.setChecked(false);
                    radioD.setChecked(false);
                    radioE.setChecked(true);

                    System.out.println(answer);
                    if (radioE.getText().toString().equals(answer)) {
                        onItemClickCallback.onItemClicked(1, getAdapterPosition());
                    } else {
                        onItemClickCallback.onItemClicked(0, getAdapterPosition());
                    }
                }
            });
        }
    }

    public List<Kuis> getList() {
        return listGetKuis;
    }

    public void setKuisResult(List<Kuis> KuisResult) {
        this.listGetKuis = KuisResult;
    }

    public interface OnItemClickCallback {
        void onItemClicked(int nilai, int posisi);
    }
}
