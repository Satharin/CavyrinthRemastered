package com.example.rharasimiuk.djangorestlearn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter{

    private Context CTX;

    //Array with empty squares for depot
    public Integer image_id[] = {R.mipmap.empty,R.mipmap.empty,R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty,R.mipmap.empty,R.mipmap.empty,R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty,R.mipmap.empty,R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty,R.mipmap.empty,R.mipmap.empty,R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty,
            R.mipmap.empty, R.mipmap.empty, R.mipmap.empty, R.mipmap.empty};

    //Array with empty strings for quantity in depot
    public String[] texts = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

    private static LayoutInflater inflater=null;

    int imageCheck;

    //Needed for row layout of custom view
    public ImageAdapter(Context CTX) {

        this.CTX = CTX;

        inflater = ( LayoutInflater )CTX.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        return image_id.length;

    }

    @Override
    public Object getItem(int position) {

        return null;

    }

    @Override
    public long getItemId(int position) {

        return 0;

    }

    //Needed to display ImageView and TextView together
    public class Holder{

        TextView tv;
        ImageView img;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.quantity_item_depot, null);

        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.tv.setText(texts[position]);

        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.img.setPadding(1,1,1,1);
        holder.img.setImageResource(image_id[position]);

        return rowView;

    }

    public void changeImage(int position, int image){

        if(position >= 0 && position < image_id.length){
            image_id[position] = image;
            notifyDataSetChanged();
        }

    }

    public int checkImage(int position){

        if(position >= 0 && position < image_id.length){
            imageCheck = image_id[position];
            notifyDataSetChanged();

        }
        return imageCheck;
    }

    public void changeText(int position, String text){

        if(position >= 0 && position < image_id.length){
            texts[position] = text;
            notifyDataSetChanged();
        }

    }

}

