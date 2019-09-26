package com.example.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
// class for creating a item list adapter
public class itemAdapter extends ArrayAdapter <item>{
    private Context context;
    private List<item> itemList =  new ArrayList<>();
    public itemAdapter(@NonNull Context con, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<item> list){
        super(con,0,list);
        context = con;
        itemList = list;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        item currentitem = itemList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.item_image);
        image.setImageResource(currentitem.getImage_url());

        TextView name = (TextView) listItem.findViewById(R.id.imagetitle);
        name.setText(currentitem.getImage_title());

        return listItem;
    }
}
