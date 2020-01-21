package com.elkpd.apps.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.elkpd.apps.R;
import com.elkpd.apps.model.HasilEvaluasi;

import java.util.List;

public class HasilEvaluasiAdapter extends RecyclerView.Adapter<HasilEvaluasiAdapter.ViewHolder> {

    private List<HasilEvaluasi> data;
    private onItemClick onItemClick;

    public void OnItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public HasilEvaluasiAdapter(List<HasilEvaluasi> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_name, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;

        ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
        }

        void bind(final HasilEvaluasi dataHasil) {
            tvNama.setText(dataHasil.getNama());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClicked(dataHasil);
                }
            });
        }
    }

    public interface onItemClick {
        void onItemClicked(HasilEvaluasi dataHasil);
    }
}
