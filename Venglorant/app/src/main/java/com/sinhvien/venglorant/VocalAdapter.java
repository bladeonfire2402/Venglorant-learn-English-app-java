package com.sinhvien.venglorant;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VocalAdapter extends RecyclerView.Adapter<VocalAdapter.VocalViewHolder> {

    private List<VolcabularyItem> VocalbularyList;

    public VocalAdapter(List<VolcabularyItem> vocalbularyList) {
        VocalbularyList = vocalbularyList;
    }

    @NonNull
    @Override
    public VocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.volcabulary_item,parent,false);
        return new VocalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VocalViewHolder holder, int position) {
        VolcabularyItem volcabularyItem=VocalbularyList.get(position);
        holder.VocalImage.setImageResource(volcabularyItem.getImageView());
        holder.volcabulary.setText(volcabularyItem.getVolcabulary());
        holder.vocalType.setText(volcabularyItem.getVolType());
        holder.spelling.setText(volcabularyItem.getSpelling());
        holder.mean.setText(volcabularyItem.getMean());

    }

    @Override
    public int getItemCount() {
        return VocalbularyList.size();
    }

    public static class VocalViewHolder extends RecyclerView.ViewHolder{
        ImageView VocalImage;
        TextView volcabulary,spelling,mean,vocalType;
        public VocalViewHolder(@NonNull View itemView){
            super(itemView);
            VocalImage= itemView.findViewById(R.id.VocalImage);
            volcabulary=itemView.findViewById(R.id.Volcalbulary);
            spelling=itemView.findViewById(R.id.Spelling);
            mean=itemView.findViewById(R.id.VocalMean);
            vocalType=itemView.findViewById(R.id.VocalType);

        }
    }
}
