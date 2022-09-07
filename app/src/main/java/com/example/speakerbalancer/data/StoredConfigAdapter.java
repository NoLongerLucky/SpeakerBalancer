package com.example.speakerbalancer.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;

import java.util.List;

public class StoredConfigAdapter extends RecyclerView.Adapter<StoredConfigAdapter.ViewHolder> {
    Context context;
    List<StoredConfig> list;

    public StoredConfigAdapter(Context context, List<StoredConfig> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoredConfigAdapter.ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.systemType.setText(list.get(position).getSystemType());
        holder.roomLength.setText((int) list.get(position).getRoomLength());
        holder.roomWidth.setText((int) list.get(position).getRoomWidth());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, systemType, roomLength, roomWidth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            systemType = itemView.findViewById(R.id.systemType);
            roomLength = itemView.findViewById(R.id.roomLength);
            roomWidth = itemView.findViewById(R.id.roomWidth);
        }
    }
}
