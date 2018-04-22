package com.example.kilia.testapp1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.kilia.testapp1.model.DataSearchAuthor;
import com.example.kilia.testapp1.requete.DeezerService;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText searchArtiste;

    private Context context =this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchArtiste = (EditText) findViewById(R.id.searchArtiste);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


searchArtiste.setOnKeyListener(new View.OnKeyListener() {
    Response.Listener<DataSearchAuthor> responseListener = new Response.Listener<DataSearchAuthor>() {
        @Override
        public void onResponse(DataSearchAuthor response) {

            mAdapter = new MyAdapter(MainActivity.this,response.getData());

            mRecyclerView.setAdapter(mAdapter);
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
            DeezerService.INSTANCE.searchAuthor(searchArtiste.getText().toString(),responseListener,errorListener,context);
            return true;
        }
        return false;
    }
});








    }
}
