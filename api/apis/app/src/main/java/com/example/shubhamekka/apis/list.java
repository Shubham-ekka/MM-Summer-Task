package com.example.shubhamekka.apis;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;

public class list extends Fragment {
    public list() {
        // Required empty public constructor
    }
    JSONObject json=new JSONObject();
    List<showing> display;
    RecyclerView rv;
    boolean gotJson=false;
    CountDownTimer timer;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_list, container, false);;
        rv=(RecyclerView)v.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        json=getJSONFromInternet("http://mondaymorning.nitrkl.ac.in/api/post/get/featured");
        timer=new CountDownTimer(4000,300){
            Snackbar snack;
            @Override
            public void onTick(long millisUntilFinished) {
                if (gotJson) {
                    initializeFeatured(json);
                    initializeAdapter();
                    mSwipeRefreshLayout.setRefreshing(false);

                }
            }
            @Override
            public void onFinish()
            {
                if(gotJson)
                {
                    initializeFeatured(json);
                    initializeAdapter();
                    mSwipeRefreshLayout.setRefreshing(false);

                }
                else
                    snack=Snackbar.make(rv,"Cannot connect",Snackbar.LENGTH_INDEFINITE);
                snack.setAction("Refresh", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refreshview();
                    }
                });
                snack.show();
                mSwipeRefreshLayout.setRefreshing(false);



            }
        };
        timer.start();
        mSwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gotJson=false;
                refreshview();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void initializeFeatured(JSONObject json){
        display = new ArrayList<>();
        try {
            for(int i=0;i<json.getJSONObject("slider").getJSONArray("posts").length();i++){
                JSONArray arr=json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getJSONArray("authors");
                String authors[]=new String[arr.length()];
                for(int j=0;j<arr.length();j++)
                    authors[j]=arr.getString(j);
                arr=json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getJSONArray("categories");
                String categories[]=new String[arr.length()];
                for(int j=0;j<arr.length();j++)
                    categories[j]=arr.getJSONObject(j).getString("post_category_name");
                display.add(new showing(json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getInt("post_id"),
                        json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getString("post_title"),
                        json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getString("post_excerpt"),
                        json.getJSONObject("slider").getString("imageUrlPrefix").replace("\\/","/").replace("big","small")
                                +json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getString("featured_image"),
                        json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getString("post_publish_date"),
                        authors,
                        categories,
                        json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getInt("post_hits"),
                        json.getJSONObject("slider").getJSONArray("posts").getJSONObject(i).getInt("post_comment_count")));
            }
            for(int i=0;i<4;i++){
                JSONArray arr=json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getJSONArray("authors");
                String authors[]=new String[arr.length()];
                for(int j=0;j<arr.length();j++)
                    authors[j]=arr.getString(j);
                arr=json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getJSONArray("categories");
                String categories[]=new String[arr.length()];
                for(int j=0;j<arr.length();j++)
                    categories[j]=arr.getJSONObject(j).getString("post_category_name");
                display.add(new showing(json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getInt("post_id"),
                        json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getString("post_title"),
                        json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getString("post_excerpt"),
                        json.getJSONObject("top4").getString("imageUrlPrefix").replace("\\/","/").replace("big","small")
                                +json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getString("featured_image"),
                        json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getString("post_publish_date"),
                        authors,
                        categories,
                        json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getInt("post_hits"),
                        json.getJSONObject("top4").getJSONArray("posts").getJSONObject(i).getInt("post_comment_count")));
            }

        }
        catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(display);
        rv.setAdapter(adapter);
        timer.cancel();
    }

    public void refreshview()
    {
        json=getJSONFromInternet("http://mondaymorning.nitrkl.ac.in/api/post/get/featured");
        timer.cancel();
        timer.start();
    }


    public JSONObject getJSONFromInternet(String url)
    {
        JsonObjectRequest jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        json=response;
                        gotJson=true;
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        try {
                            json.put("success","false");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        error.printStackTrace();
                    }
                });
        Volley.newRequestQueue(getActivity()).add(jsonRequest.setShouldCache(false));
        return json;
    }
}
