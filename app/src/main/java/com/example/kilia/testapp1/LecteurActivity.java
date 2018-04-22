package com.example.kilia.testapp1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kilia.testapp1.model.DataSearchAlbum;
import com.example.kilia.testapp1.model.DataSearchListAlbum;
import com.example.kilia.testapp1.requete.DeezerService;

public class LecteurActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecteur);

        Intent intent = this.getIntent();
        id = intent.getIntExtra("id",0);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


            Response.Listener<DataSearchAlbum.Tracks> responseListener = new Response.Listener<DataSearchAlbum.Tracks>() {
                @Override
                public void onResponse(DataSearchAlbum.Tracks response) {
                    mAdapter = new TrackAddapteur(LecteurActivity.this,response.getData());

                    mRecyclerView.setAdapter(mAdapter);
                }
            };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        DeezerService.INSTANCE.searchTracks(id,responseListener,errorListener,this);
    }


    protected void onStop(){
        MediaPlayer media = TrackAddapteur.getMedia();
        media.stop();
        super.onStop();
    }
}
