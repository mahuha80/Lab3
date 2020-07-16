package com.example.vinhntph08047_lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> {
    private Context context;
    private List<RootModel> list;

    public RvAdapter(Context context, List<RootModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bindItem(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvId, tvType;
        ImageView img;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvType = itemView.findViewById(R.id.tvType);
            img = itemView.findViewById(R.id.imageView);
        }

        public void bindItem(int position) {
            tvId.setText(String.valueOf(list.get(position).getId()));
            tvTitle.setText(String.valueOf(list.get(position).getTitle()));
            tvType.setText(String.valueOf(list.get(position).getType()));
        }
    }
}
