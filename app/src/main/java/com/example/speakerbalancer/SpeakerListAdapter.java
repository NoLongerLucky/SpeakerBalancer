package com.example.speakerbalancer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        String speakerName = list.get(position).name;
        String speakerId = list.get(position).channel.id;

        holder.speakerName.setText(speakerName);
        holder.speakerId.setText(speakerId);

        holder.moveSpeaker.setOnClickListener(view -> moveItemClickListener.onItemMove(list.get(position).name, list.get(position).channel.id, holder.moveSpeaker));
        holder.editSpeakerInfo.setOnClickListener(view -> editItemClickListener.onItemEdit(list.get(position).name, list.get(position).channel.id));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView speakerName, speakerId;
        Button moveSpeaker, editSpeakerInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            speakerName = itemView.findViewById(R.id.speakerName);
            speakerId = itemView.findViewById(R.id.speakerId);
            moveSpeaker = itemView.findViewById(R.id.moveSpeaker);
            editSpeakerInfo = itemView.findViewById(R.id.editSpeakerInfo);
        }
    }

    public interface MoveItemClickListener {
        void onItemMove(String name, String id, Button button);
    }

    public interface EditItemClickListener {
        void onItemEdit(String name, String id);
    }
}
