package com.example.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Viewrequest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Viewrequest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Viewrequest extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView ownername,status,OwnerName,Contact;
    private TextView requestby;
    private ImageView itemImg;
    private Button Accept,Reject;

    private user User;

    private OnFragmentInteractionListener mListener;
    private FirebaseUser user;

    private DatabaseReference reference;
    private FirebaseDatabase mDatabase;
    public Viewrequest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Viewrequest.
     */
    // TODO: Rename and change types and number of parameters
    public static Viewrequest newInstance(String param1, String param2) {
        Viewrequest fragment = new Viewrequest();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewrequest, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("notifications").push();
        itemImg = view.findViewById(R.id.imgItem);
        DatabaseReference re = FirebaseDatabase.getInstance().getReferenceFromUrl("https://share-o-72f35.firebaseio.com/items");
        re.child(nt.getProductId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item item = dataSnapshot.getValue(item.class);
                Log.i("item pic url",item.getImage_url());
                Picasso.with(getContext()).load(item.getImage_url()).into(itemImg);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error","while retriving data");
            }
        });

        ownername = view.findViewById(R.id.title);
        requestby = view.findViewById(R.id.requestby);
        status = view.findViewById(R.id.status);
        OwnerName = view.findViewById(R.id.username);
        Contact = view.findViewById(R.id.Contact);

        Accept = view.findViewById(R.id.accept);
        Accept.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                notification not = new notification(user.getUid(),nt.getUserId(), nt.getProductId(),nt.getProductname(),"response","Accepted");
                Log.i("notificatoin product id",nt.getProductId());
                reference.setValue(not);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("notifications");
                ref.child(nt.getId()).child("status").setValue("Accepted");
                nt.setStatus("Accepted");

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                home myFragment = new home();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, myFragment).addToBackStack("homepage").commit();
                Toast.makeText(getContext(),"request Accepted",Toast.LENGTH_SHORT).show();

            }
        });

        Reject = view.findViewById(R.id.reject);
        Reject.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                notification not = new notification(user.getUid(),nt.getUserId(), nt.getProductId(),nt.getProductname(),"response","Rejected");
//                Log.i("notificatoin product id",nt.getProductId());
                reference.setValue(not);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("notifications");

                ref.child(nt.getId()).child("status").setValue("Rejected");


                nt.setStatus("Rejected");

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                home myFragment = new home();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, myFragment).addToBackStack("homepage").commit();
                Toast.makeText(getContext(),"request rejected",Toast.LENGTH_SHORT).show();;

            }
        });
//        Log.i("product Id: ",nt.getProductId());
        ownername.setText("Title: " + nt.getProductname());
        requestby.setText("Type: "+nt.getType());
        if(nt.getType().equals("response")){
            Accept.setVisibility(view.GONE);
            Reject.setVisibility(view.GONE);
            status.setVisibility(View.VISIBLE);

            status.setText("Owner has "+nt.getStatus()+" your request!");
            DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://share-o-72f35.firebaseio.com/user");
            ref.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User = dataSnapshot.getValue(user.class);
                    Log.i("userid", user.getUid());


                    OwnerName.setVisibility(View.VISIBLE);
                    Contact.setVisibility(View.VISIBLE);
                    OwnerName.setText("Full name: " + User.getUsername());
                    Contact.setText("Contact: "+ User.getContact());;
//          }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("error","while retriving data");
                }
            });
        }
        if(nt.getType().equals("request") && !nt.getStatus().equals("pending")) {
            Accept.setVisibility(view.GONE);
            Reject.setVisibility(view.GONE);
            status.setVisibility(View.VISIBLE);
            status.setText("You have " + nt.getStatus() + " this request!");


        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private notification nt;
    public void setNotif(notification nt) {
        this.nt = nt;
    }

    public void onReject(View view){

    }
    public void onAccept(View view){

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
