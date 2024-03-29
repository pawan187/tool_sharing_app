package com.example.demo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Addproduct.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Addproduct#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Addproduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText pin;
    private ImageView itemImage;
    private EditText title;
    private EditText desciption;
    private EditText imgPath;
    private EditText availability;
    private Button btnforPic;
    private Button btnforAdd;


    private OnFragmentInteractionListener mListener;
    private FirebaseUser mAuth = FirebaseAuth.getInstance().getCurrentUser();

    private DatabaseReference mDatabase;
    public Addproduct() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Addproduct.
     */
    // TODO: Rename and change types and number of parameters
    public static Addproduct newInstance(String param1, String param2) {
        Addproduct fragment = new Addproduct();
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
        mDatabase = FirebaseDatabase.getInstance().getReference().child("items");

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        pin = pin.findViewById(R.id.pin);
//        itemImage = itemImage.findViewById(R.id.item_image);
//        title= title.findViewById(R.id.title);
//        desciption = desciption.findViewById(R.id.description);
//        imgPath = imgPath.findViewById(R.id.imgpath);
//        availability = availability.findViewById(R.id.availibility);
//        btnforPic = btnforPic.findViewById(R.id.productimage);
//        btnforAdd = btnforAdd.findViewById(R.id.add);
        return inflater.inflate(R.layout.fragment_addproduct, container, false);
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
