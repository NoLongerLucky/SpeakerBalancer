package com.example.speakerbalancer.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.R;

import java.util.List;

public class StoredConfigAdapter extends RecyclerView.Adapter<StoredConfigAdapter.ViewHolder> {
    Context context;
    List<StoredConfig> list;
    DeleteItemClickListener deleteItemClickListener;

    public StoredConfigAdapter(Context context, List<StoredConfig> list, DeleteItemClickListener deleteItemClickListener) {
        this.context = context;
        this.list = list;
        this.deleteItemClickListener = deleteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoredConfigAdapter.ViewHolder holder, final int position) {
        String name = context.getString(R.string.name) + ": " + list.get(position).getName();
        String systemType = context.getString(R.string.systemType) + ": " + list.get(position).getSystemType();
        String roomLength = context.getString(R.string.roomLength) + ": " + list.get(position).getRoomLength();
        String roomWidth = context.getString(R.string.roomWidth) + ": " + list.get(position).getRoomWidth();

        holder.name.setText(name);
        holder.systemType.setText(systemType);
        holder.roomLength.setText(roomLength);
        holder.roomWidth.setText(roomWidth);

        holder.delete.setOnClickListener(view -> deleteItemClickListener.onItemDelete(position, list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, systemType, roomLength, roomWidth;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_load);
            systemType = itemView.findViewById(R.id.systemType_load);
            roomLength = itemView.findViewById(R.id.roomLength_load);
            roomWidth = itemView.findViewById(R.id.roomWidth_load);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public interface DeleteItemClickListener {
        void onItemDelete(int position, int id);
    }
}
