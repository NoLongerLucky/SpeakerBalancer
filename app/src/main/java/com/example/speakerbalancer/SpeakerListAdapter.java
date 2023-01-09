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

import java.util.List;

public class SpeakerListAdapter extends RecyclerView.Adapter<SpeakerListAdapter.ViewHolder> {
    Context context;
    List<Speaker> list;
    MoveItemClickListener moveItemClickListener;
    EditItemClickListener editItemClickListener;

    public SpeakerListAdapter(Context context, List<Speaker> list, MoveItemClickListener moveItemClickListener, EditItemClickListener editItemClickListener) {
        this.context = context;
        this.list = list;
        this.moveItemClickListener = moveItemClickListener;
        this.editItemClickListener = editItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_speaker_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakerListAdapter.ViewHolder holder, final int position) {
        holder.speakerImage.setImageResource(list.get(position).imageId);

        holder.speakerName.setText(list.get(position).name);
        holder.speakerId.setText(list.get(position).channel.id);

        holder.moveSpeaker.setOnClickListener(view -> moveItemClickListener.onSpeakerMove(
                list.get(position).name,
                list.get(position).channel.id,
                holder.moveSpeaker,
                position,
                list.get(position).channel.xBias,
                list.get(position).channel.yBias
        ));
        holder.editSpeakerInfo.setOnClickListener(view -> editItemClickListener.onSpeakerEdit(
                list.get(position).name,
                list.get(position).channel.id
        ));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView speakerImage;
        TextView speakerName, speakerId;
        Button moveSpeaker, editSpeakerInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            speakerImage = itemView.findViewById(R.id.speakerImage);
            speakerName = itemView.findViewById(R.id.speakerName);
            speakerId = itemView.findViewById(R.id.speakerId);
            moveSpeaker = itemView.findViewById(R.id.moveSpeaker);
            editSpeakerInfo = itemView.findViewById(R.id.editSpeakerInfo);
        }
    }

    public interface MoveItemClickListener {
        void onSpeakerMove(String name, String id, Button button, int position, double xBias, double yBias);
    }

    public interface EditItemClickListener {
        void onSpeakerEdit(String name, String id);
    }
}
