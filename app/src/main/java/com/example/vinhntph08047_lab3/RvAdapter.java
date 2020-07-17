package com.example.vinhntph08047_lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> implements Filterable {
    private Context context;
    private List<RootModel> list;
    private OnItemClickListener callback;

    public RvAdapter(Context context, List<RootModel> list, OnItemClickListener callback) {
        this.context = context;
        this.list = list;
        this.callback = callback;
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

    @Override
    public Filter getFilter() {
        Filter fRecords = null;
        if (fRecords == null) {
            fRecords = new RecordFilter();
        }
        return fRecords;
    }

    public void updateData(List<RootModel> rootModels) {
        this.list.clear();
        this.list = rootModels;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(RootModel rootModel);
    }

    public class RecordFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvId, tvType;
        ImageView img;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvModified);
            tvType = itemView.findViewById(R.id.tvType);
        }

        public void bindItem(int position) {
            tvId.setText(String.valueOf(list.get(position).getId()));
            tvTitle.setText(String.valueOf(list.get(position).getTitle()));
            tvType.setText(String.valueOf(list.get(position).getUrl()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(list.get(position));
                }
            });
        }
    }
}
