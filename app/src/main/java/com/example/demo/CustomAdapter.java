package com.example.demo;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.MyViewHolder>{
    ArrayList<notification> requests;
    Context context;
    public CustomAdapter(Context context, ArrayList<notification> personNames) {
        this.context = context;
        this.requests = personNames;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        notification vl = requests.get(i);
//        myViewHolder.username.setText(String.valueOf(vl.getUsername()));
        myViewHolder.ownername.setText(String.valueOf(vl.getOwnername()));
        myViewHolder.productname.setText(String.valueOf(vl.getProductname()));
    Log.i("object", String.valueOf(vl));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, String.valueOf(i)+"element selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return requests.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView username;// init the item view's
        TextView ownername;// init the item view's
        TextView productname;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
//            username = (TextView) itemView.findViewById(R.id.username);
            ownername = (TextView) itemView.findViewById(R.id.ownername);
            productname = (TextView) itemView.findViewById(R.id.productname);
        }
    }
}
