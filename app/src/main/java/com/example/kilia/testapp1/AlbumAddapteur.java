package com.example.kilia.testapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kilia.testapp1.model.Album;
import com.example.kilia.testapp1.model.Author;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumAddapteur extends RecyclerView.Adapter<AlbumAddapteur.ViewHolder> {

    private List<Album> myDataset;
    final Intent intent;
    private Context contexte;

    public AlbumAddapteur(AfficheAlbum c, List<Album> myData) {
        this.myDataset = myData;
        Log.w("AlbumAdapter", "empty ?" + myDataset.size());
        intent = new Intent(c,LecteurActivity.class);
        contexte = c;
    }

    @Override
    public AlbumAddapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.affiche_auteur, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AlbumAddapteur.ViewHolder holder,final int position) {

        holder.textViewName.setText(myDataset.get(position).getTitle());
        Picasso.with(holder.itemView.getContext())
                .load(myDataset.get(position).getCover_big())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent.putExtra("id", ( myDataset.get(position).getId()));
                contexte.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return myDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView textViewName;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            textViewName = (TextView) mView.findViewById(R.id.Auteur);
            image = (ImageView) mView.findViewById(R.id.image);
        }
    }
}
