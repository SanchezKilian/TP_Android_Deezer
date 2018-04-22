package com.example.kilia.testapp1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kilia.testapp1.model.DataSearchAlbum;
import com.example.kilia.testapp1.model.DataSearchAuthor;
import com.example.kilia.testapp1.model.DataSearchListAlbum;
import com.example.kilia.testapp1.requete.DeezerService;

public class AfficheAlbum extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String nomArtiste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = this.getIntent();
        nomArtiste = intent.getStringExtra("nomArtiste");

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Response.Listener<DataSearchListAlbum> responseListener = new Response.Listener<DataSearchListAlbum>() {
            @Override
            public void onResponse(DataSearchListAlbum response) {

                mAdapter = new AlbumAddapteur(AfficheAlbum.this,response.getData());

                mRecyclerView.setAdapter(mAdapter);
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        DeezerService.INSTANCE.searchAlbum(Uri.encode(nomArtiste),responseListener,errorListener,this);





    }
}
