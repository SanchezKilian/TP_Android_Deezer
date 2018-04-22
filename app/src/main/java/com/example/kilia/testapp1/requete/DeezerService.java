package com.example.kilia.testapp1.requete;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.kilia.testapp1.model.DataSearchAlbum;
import com.example.kilia.testapp1.model.DataSearchAuthor;
import com.example.kilia.testapp1.model.DataSearchListAlbum;

public enum DeezerService {
    INSTANCE;
    private static final String TAG ="DeezerService";
    private static final String TAG_SEARCH_AUTOR = "AUTHOR";

    public void searchAuthor (String artist,
                              Response.Listener responseListener,
                                Response.ErrorListener errorListener,
                                Context context
    ){
        final String url = "https://api.deezer.com/search/artist?q="+artist;
        Log.w(TAG,"searchAuthor" + url );

        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<DataSearchAuthor> request = new GsonRequest<>(url,DataSearchAuthor.class,null,responseListener,errorListener);

        queue.add(request);
    }

    public void searchAlbum (String album,
                              Response.Listener responseListener,
                              Response.ErrorListener errorListener,
                              Context context
    ){
        final String url = "http://api.deezer.com/search/album?q="+album;
        Log.w(TAG,"searchAlbum" + url );

        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<DataSearchListAlbum> request = new GsonRequest<>(url,DataSearchListAlbum.class,null,responseListener,errorListener);

        queue.add(request);
    }

    public void searchTracks (int id,
                             Response.Listener responseListener,
                             Response.ErrorListener errorListener,
                             Context context
    ){
        final String url = "https://api.deezer.com/album/"+id+"/tracks";
        Log.w(TAG,"searchTracks" + url );

        RequestQueue queue = Volley.newRequestQueue(context);

        GsonRequest<DataSearchAlbum.Tracks> request = new GsonRequest<>(url,DataSearchAlbum.Tracks.class,null,responseListener,errorListener);

        queue.add(request);
    }
}
