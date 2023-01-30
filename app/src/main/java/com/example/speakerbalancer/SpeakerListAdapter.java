package com.example.speakerbalancer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakerbalancer.data.TempConfig;
import com.example.speakerbalancer.speakers.Speaker;

import java.util.List;

public class SpeakerListAdapter extends RecyclerView.Adapter<SpeakerListAdapter.ViewHolder> {
    Context context;
    List<Speaker> list;
    TempConfig unsavedConfig;
    MoveItemClickListener moveItemClickListener;
    ResetItemPosClickListener resetItemClickListener;
    EditItemClickListener editItemClickListener;

    public SpeakerListAdapter(Context context, List<Speaker> list, TempConfig unsavedConfig, MoveItemClickListener moveItemClickListener, ResetItemPosClickListener resetItemClickListener, EditItemClickListener editItemClickListener) {
        this.context = context;
        this.list = list;
        this.unsavedConfig = unsavedConfig;
        this.moveItemClickListener = moveItemClickListener;
        this.resetItemClickListener = resetItemClickListener;
        this.editItemClickListener = editItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_speaker_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakerListAdapter.ViewHolder holder, final int position) {
        holder.speakerImage.setImageResource(list.get(position).getImageId());

        holder.infoLine1.setText(unsavedConfig.line1(position, list.get(position).getChannel().getId()));
        holder.infoLine2.setText(unsavedConfig.line2(position));

        holder.move.setOnClickListener(view -> moveItemClickListener.onSpeakerMove(
                list.get(position),
                holder.move,
                position
        ));
        holder.resetPosition.setOnClickListener(view -> resetItemClickListener.onSpeakerResetPos(
                position
        ));
        holder.editInfo.setOnClickListener(view -> editItemClickListener.onSpeakerEdit(
                position
        ));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView speakerImage;
        TextView infoLine1, infoLine2;
        Button move, resetPosition, editInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            speakerImage = itemView.findViewById(R.id.speakerImage);
            infoLine1 = itemView.findViewById(R.id.infoLine1);
            infoLine2 = itemView.findViewById(R.id.infoLine2);
            move = itemView.findViewById(R.id.move);
            resetPosition = itemView.findViewById(R.id.resetPosition);
            editInfo = itemView.findViewById(R.id.editInfo);
        }
    }

    public interface MoveItemClickListener {
        void onSpeakerMove(Speaker speaker, Button button, int position);
    }

    public interface ResetItemPosClickListener {
        void onSpeakerResetPos(int position);
    }

    public interface EditItemClickListener {
        void onSpeakerEdit(int position);
    }
}
