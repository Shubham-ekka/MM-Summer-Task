package com.example.shubhamekka.apis;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;


public class article extends Fragment {

    JSONObject json=new JSONObject();
    Boolean gotJson=false;
    Boolean gotImage=false;
    CountDownTimer timer;
    String postUrl="";

    int position;
    int post_id;
    String imageUrl;


    public static article create(int position,int post_id,String imageUrl) {
        article fragment = new article();
        Bundle args = new Bundle();
        args.putInt("position",position);
        args.putInt("post_id", post_id);
        args.putString("imageUrl",imageUrl);
        fragment.setArguments(args);
        Log.i("Here","Here");

        return fragment;
    }

    public article() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position=getArguments().getInt("position");
        post_id=getArguments().getInt("post_id");
        imageUrl=getArguments().getString("imageUrl");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_article, container, false);

        return view;

    }
    @Override
    public void onStart()
    {
        super.onStart();
        setUpView(imageUrl.replace("small","big"));
        getJSONFromInternet("http://mondaymorning.nitrkl.ac.in/api/post/get/"+String.valueOf(post_id));
        timer=new CountDownTimer(5000,300){
            @Override
            public void onTick(long millisUntilFinished)
            {
                if(gotJson)
                {
                    processJSON(json);

                }
                if(gotImage) {
                    getView().findViewById(R.id.expandImage).buildDrawingCache();
                    Palette palette = Palette.generate(getView().findViewById(R.id.expandImage).getDrawingCache(), 24);
                    CollapsingToolbarLayout collapse=(CollapsingToolbarLayout)getView().findViewById(R.id.collapsingtoolbar);
                    float[] hsv = new float[3];
                    int color = palette.getDarkVibrantColor(0xDCDCDC);
                    Color.colorToHSV(color, hsv);
                    hsv[2] *= 0.8f;
                    color = Color.HSVToColor(hsv);
                    collapse.setContentScrimColor(color);
                    gotImage=false;

                }
            }
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onFinish()
            {
                if(gotJson)
                    processJSON(json);

                else



            }
        };
        timer.start();

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setUpView(String url)
    {
        final ImageView iv=(ImageView)getView().findViewById(R.id.expandImage);
        Glide.with(getActivity())
                .load(url)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        gotImage=true;
                        return false;
                    }
                })
                .error(R.drawable.ic_glide_failed)
                .crossFade()
                .skipMemoryCache(true)
                .centerCrop()
                .into(iv);
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
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
        return json;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void processJSON(JSONObject json) {

        String content = "";
        try {
            for (int i = 0; i < json.getJSONObject("post").getJSONArray("post_content").length(); i++) {

                if(json.getJSONObject("post").getJSONArray("post_content").getJSONObject(i).getInt("type")==4)
                    content = content + json.getJSONObject("post")
                            .getJSONArray("post_content")
                            .getJSONObject(i)
                            .getString("content");
                else if(json.getJSONObject("post").getJSONArray("post_content").getJSONObject(i).getInt("type")==1) {
                    DisplayMetrics dm = new DisplayMetrics();
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                    int width = (int)(dm.widthPixels/dm.density)-16;
                    content = content + "<img width=\""+width+"\" src=\"" + json.getJSONObject("post")
                            .getJSONArray("post_content")
                            .getJSONObject(i)
                            .getString("content") + "\">";
                }
            }
            postUrl=json.getJSONObject("post").getString("post_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String head = "<head><meta name=\"viewport\" content=\"width=device-width, user-scalable=yes\" /></head>";
        String closedTag = "</body></html>";
        content=head+content+closedTag;
        WebView article=(WebView)getView().findViewById(R.id.articleview);
        article.loadData(content,"text/html",null);
        article.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        timer.cancel();

    }
}
