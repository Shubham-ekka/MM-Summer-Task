package com.example.shubhamekka.apis;

public class showing {
    int post_id;
    String post_title;
    String post_excerpt;
    String featured_image;
    String post_publish_date;
    String authors[]=new String[5];
    String categories[]=new String[5];
    int post_hits;
    int post_comment_count;
    showing(int post_id,String post_title,String post_excerpt,
             String featured_image,String post_publish_date,String authors[],
             String categories[],int post_hits,int post_comment_count)
    {
        this.post_id=post_id;
        this.post_title=post_title;
        this.post_excerpt=post_excerpt;
        this.featured_image=featured_image;
        this.post_publish_date=post_publish_date;
        for(int i=0;i<authors.length;i++)
            this.authors[i]=authors[i];
        for(int i=0;i<categories.length;i++)
            this.categories[i]=categories[i];
        this.post_hits=post_hits;
        this.post_comment_count=post_comment_count;
    }
}

