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

import com.example.kilia.testapp1.model.Author;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kilian on 06/04/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Author> myDataset;
    final Intent intent;
    private Context contexte;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textViewName;
        public View mView;

        public ViewHolder(View v) {
            super(v);
            mView = v;

            textViewName = (TextView) mView.findViewById(R.id.Auteur);
            image = (ImageView) mView.findViewById(R.id.image);
        }
    }

    public MyAdapter(Context c,List<Author> myData) {
        this.myDataset = myData;
        Log.w("MyAdapter", "empty ?" + myDataset.size());
        intent = new Intent(c,AfficheAlbum.class);
        contexte = c;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view

        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.affiche_auteur, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.d("MyAdapter" , "texte : " +myDataset.get(position).getName() );
        Log.w("MyAdapter" , "image : " +myDataset.get(position).getPicture_big() );

        holder.textViewName.setText(myDataset.get(position).getName());


        Picasso.with(holder.itemView.getContext())
                .load(myDataset.get(position).getPicture_big())
                .into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              intent.putExtra("nomArtiste",myDataset.get(position).getName() );
              contexte.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return myDataset.size();
    }



}

