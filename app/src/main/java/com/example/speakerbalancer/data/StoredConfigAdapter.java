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
    EditItemClickListener editItemClickListener;
    DeleteItemClickListener deleteItemClickListener;

    public StoredConfigAdapter(Context context, List<StoredConfig> list, EditItemClickListener editItemClickListener, DeleteItemClickListener deleteItemClickListener) {
        this.context = context;
        this.list = list;
        this.editItemClickListener = editItemClickListener;
        this.deleteItemClickListener = deleteItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_load_config, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoredConfigAdapter.ViewHolder holder, final int position) {
        holder.name.setText(context.getString(R.string.nameDisplay, list.get(position).getName()));
        holder.systemType.setText(context.getString(R.string.systemTypeDisplay, list.get(position).getSystemType().getName()));
        holder.roomLength.setText(context.getString(R.string.roomLengthDisplay, list.get(position).getRoomLength()));
        holder.roomWidth.setText(context.getString(R.string.roomWidthDisplay, list.get(position).getRoomWidth()));
        holder.wallMaterial.setText(context.getString(R.string.wallMaterialDisplay, list.get(position).getWallMaterial().displayName()));

        holder.edit.setOnClickListener(view -> editItemClickListener.onConfigEdit(list.get(position).getId()));
        holder.delete.setOnClickListener(view -> deleteItemClickListener.onConfigDelete(list.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, systemType, roomLength, roomWidth, wallMaterial;
        Button edit, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_load);
            systemType = itemView.findViewById(R.id.systemType_load);
            roomLength = itemView.findViewById(R.id.roomLength_load);
            roomWidth = itemView.findViewById(R.id.roomWidth_load);
            wallMaterial = itemView.findViewById(R.id.wallMaterial_load);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public interface EditItemClickListener {
        void onConfigEdit(int id);
    }

    public interface DeleteItemClickListener {
        void onConfigDelete(int id);
    }
}
