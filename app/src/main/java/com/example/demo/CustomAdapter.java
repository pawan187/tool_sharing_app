package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
        final notification nt = requests.get(i);

        myViewHolder.productname.setText(String.valueOf(nt.getProductname()));
        myViewHolder.status.setText(String.valueOf(nt.getType()));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, nt.getProductname()+" selected", Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Viewrequest myFragment = new Viewrequest();
                myFragment.setNotif(nt);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, myFragment).addToBackStack(null).commit();
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
        TextView status;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
//            username = (TextView) itemView.findViewById(R.id.username);
//            ownername = (TextView) itemView.findViewById(R.id.ownername);
            productname = (TextView) itemView.findViewById(R.id.productname);
            status = (TextView) itemView.findViewById(R.id.type);
        }
    }
}
