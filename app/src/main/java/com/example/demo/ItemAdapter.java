package com.example.demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<item> requests;
    public ItemAdapter(Context context, ArrayList<item> requests) {
        this.context= context;
        this.requests = requests;
        Log.i("items:",String.valueOf(requests));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ItemAdapter.MyViewHolder vh = new ItemAdapter.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
       final item item = requests.get(position);
        holder.image_title.setText(String.valueOf(requests.get(position).getImage_title()));
        if(requests.get(position).getImage_url()!=""){
            Picasso.with(context).load(requests.get(position).getImage_url()).into(holder.image_url);
        }
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, String.valueOf(position)+"element selected", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context,ViewProduct.class);
//                context.startActivity(intent);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ViewItem myFragment = new ViewItem();
                myFragment.setItem(item);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, myFragment).addToBackStack("homepage").commit();
            }
        });
    }


    @Override
    public int getItemCount() {
            return requests.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        private ImageView image_url;
        private TextView image_title;
        public MyViewHolder(View v) {
                super(v);
            image_url =(ImageView) v.findViewById(R.id.image_url);
            image_title = (TextView) v.findViewById(R.id.item_title);
        }

    }
}
