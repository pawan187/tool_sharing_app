package com.example.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
 * {@link dashboard.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link dashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dashboard extends Fragment {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView Full_name ;
    private TextView Contact;
    private TextView Dob;
    private ImageView ProfilePic;
    private TextView pin;
    private TextView address;
    private ImageView Profile;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
//    final FirebaseDatabase reference;
    private DatabaseReference reference;
    private user User;

    public dashboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dashboard.
     */
    // TODO: Rename and change types and number of parameters
    public static dashboard newInstance(String param1, String param2) {
        dashboard fragment = new dashboard();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Contact =(TextView) view.findViewById(R.id.contact2);
        Dob = (TextView) view.findViewById(R.id.dob2);
        Full_name = view.findViewById(R.id.fullName);
        address = view.findViewById(R.id.address);
        pin = view.findViewById(R.id.pin_number);
        Profile = view.findViewById(R.id.profile);
        final FirebaseUser user = mAuth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://share-o-72f35.firebaseio.com/user");
        reference.child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User = dataSnapshot.getValue(user.class);
                Log.i("userid", user.getUid()+"profile pic:"+User.getPic_url() + User.getUsername() + User.getAddress() + User.getDob());

                Picasso.with(getContext()).load(User.getPic_url()).into(Profile);
                Full_name.setText("Full name: " + User.getUsername());
                Contact.setText("Conctact: "+User.getContact());
                Dob.setText("DOB: "+User.getDob());
                address.setText("Address: " + User.getAddress());
                pin.setText("Pin: "+User.getPin());
//          }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error","while retriving data");
            }
        });
//        Full_name.setText("Full name: "+User.getUsername());
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
