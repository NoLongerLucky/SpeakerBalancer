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

import com.example.speakerbalancer.speakers.Speaker;
import com.example.speakerbalancer.systems.Channel;

import java.util.List;

public class SpeakerListAdapter extends RecyclerView.Adapter<SpeakerListAdapter.ViewHolder> {
    Context context;
    List<Speaker> list;
    MoveItemClickListener moveItemClickListener;
    ResetItemPosClickListener resetItemClickListener;
    EditItemClickListener editItemClickListener;

    public SpeakerListAdapter(Context context, List<Speaker> list, MoveItemClickListener moveItemClickListener, ResetItemPosClickListener resetItemClickListener, EditItemClickListener editItemClickListener) {
        this.context = context;
        this.list = list;
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

        holder.speakerName.setText(list.get(position).getName());
        holder.speakerId.setText(list.get(position).getChannel().getId());

        holder.move.setOnClickListener(view -> moveItemClickListener.onSpeakerMove(
                list.get(position),
                holder.move,
                position
        ));
        holder.resetPosition.setOnClickListener(view -> resetItemClickListener.onSpeakerResetPos(
                list.get(position).getChannel(),
                position
        ));
        holder.editInfo.setOnClickListener(view -> editItemClickListener.onSpeakerEdit(
                list.get(position)
        ));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView speakerImage;
        TextView speakerName, speakerId;
        Button move, resetPosition, editInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            speakerImage = itemView.findViewById(R.id.speakerImage);
            speakerName = itemView.findViewById(R.id.speakerName);
            speakerId = itemView.findViewById(R.id.speakerId);
            move = itemView.findViewById(R.id.move);
            resetPosition = itemView.findViewById(R.id.resetPosition);
            editInfo = itemView.findViewById(R.id.editInfo);
        }
    }

    public interface MoveItemClickListener {
        void onSpeakerMove(Speaker speaker, Button button, int position);
    }

    public interface ResetItemPosClickListener {
        void onSpeakerResetPos(Channel channel, int position);
    }

    public interface EditItemClickListener {
        void onSpeakerEdit(Speaker speaker);
    }
}
