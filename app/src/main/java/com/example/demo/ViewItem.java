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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewItem.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewItem extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static item item;
    private String title,availibilty,pin,description,url;

    private TextView Title,Availibilty,Pin,Description;
    private Button btn;
    private ImageView Image;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseUser user;
    private DatabaseReference reference;
    private OnFragmentInteractionListener mListener;

    public static com.example.demo.item getItem() {
        return item;
    }

    public static void setItem(item item) {
        ViewItem.item = item;
    }

    public ViewItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewItem.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewItem newInstance(String param1, String param2) {
        ViewItem fragment = new ViewItem();
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("notifications").push();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("item: ",item.getImage_title()+" item_id"+item.getId());
        Description = view.findViewById(R.id.description);
        Pin = view.findViewById(R.id.pin);
        Image = view.findViewById(R.id.productimage);
        Title = view.findViewById(R.id.title);
        Availibilty = view.findViewById(R.id.availibility);
        btn = view.findViewById(R.id.add);
        btn.setOnClickListener(this);

        if(user.getUid().equals(item.getOwner_id())){
            btn.setVisibility(View.GONE);
        }
        Description.setText("Description:"+item.getDescription());
        Pin.setText("Pin"+item.getPin());
        Title.setText("Title: "+item.getImage_title());
        Availibilty.setText("Availibilty: "+item.getAvailable());
        Picasso.with(getContext()).load(item.getImage_url()).into(Image);

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

    @Override
    public void onClick(View view) {

        notification nt = new notification(user.getUid(),item.getOwner_id(), item.getId(),item.getImage_title(),"request","pending");
        Log.i("notificatoin product id",nt.getProductId());
        reference.setValue(nt);
        AppCompatActivity activity = (AppCompatActivity) view.getContext();
        home myFragment = new home();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, myFragment).addToBackStack("homepage").commit();
        Toast.makeText(getContext(),"request made successfully!",Toast.LENGTH_SHORT).show();;
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
