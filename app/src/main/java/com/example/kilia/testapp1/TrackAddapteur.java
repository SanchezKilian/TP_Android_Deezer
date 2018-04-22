package com.example.kilia.testapp1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kilia.testapp1.model.Album;
import com.example.kilia.testapp1.model.Track;

import java.io.IOException;
import java.util.List;

class TrackAddapteur extends RecyclerView.Adapter<TrackAddapteur.ViewHolder> {

    private List<Track> myDataset;

    private Context contexte;

    private static MediaPlayer media = new MediaPlayer();

    public static MediaPlayer getMedia() {
        return media;
    }

    public TrackAddapteur(LecteurActivity c, List<Track> myData) {
        this.myDataset = myData;

        contexte = c;
    }


    @Override
    public TrackAddapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.affiche_lecteur, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TrackAddapteur.ViewHolder holder, final int position) {
        holder.textViewName.setText(myDataset.get(position).getTitle());
        holder.action.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(media.isPlaying()){
                    holder.action.setImageResource(android.R.drawable.ic_media_play);
                    media.stop();
                    media.reset();
                }else
                {
                    try{
                        holder.action.setImageResource(android.R.drawable.ic_media_pause);
                        media.setDataSource(contexte, Uri.parse(myDataset.get(position).getPreview()));
                        media.prepare();
                        media.start();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewName;
        private ImageButton action;
        private ProgressBar barre;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            textViewName = (TextView) mView.findViewById(R.id.Track);
            action = (ImageButton) mView.findViewById(R.id.action);
            barre = (ProgressBar) mView.findViewById(R.id.progressBar);
        }
    }
}
