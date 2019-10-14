package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity  {
//    private TextView mTextMessage;
//    public Fragment home = findViewById(R.id.home);
    private DatabaseReference mDatabase;
//    private Firebase
private FirebaseAuth mAuth;
private FirebaseUser user;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    /*home frag = new home();
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.mainframe,frag,"Test Fragment");
                    transaction.commit();*/
                    FragmentManager fragmentmanager = getSupportFragmentManager();
                    home fragmentone=new home();
                    FragmentTransaction transaction = fragmentmanager.beginTransaction();
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction.replace(R.id.mainframe, fragmentone).addToBackStack("homepage")
                            .commit();

                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    dashboard fragmenttwo =new dashboard();
                    FragmentManager fragmentmanager1 = getSupportFragmentManager();

                    FragmentTransaction transaction1 = fragmentmanager1.beginTransaction();

                    transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction1.replace(R.id.mainframe, fragmenttwo).addToBackStack("homepage")
                            .commit();

                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    notif fragmentthree =new notif();
                    FragmentManager fragmentmanager2 = getSupportFragmentManager();
                    FragmentTransaction transaction2 = fragmentmanager2.beginTransaction();
                    transaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction2.replace(R.id.mainframe, fragmentthree).addToBackStack("homepage")
                            .commit();

                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentmanager0 = getSupportFragmentManager();
        home fragmentone0=new home();
        FragmentTransaction transaction1 = fragmentmanager0.beginTransaction();

        transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction1.replace(R.id.mainframe, fragmentone0).addToBackStack("homepage")
                .commit();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.singoutbtn:
                        Log.i("userid",user.getUid());
                        mAuth.signOut();
                        finish();
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onView(View view) {
        Viewproducts vp = new Viewproducts();
        FragmentManager fragmentmanager0 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentmanager0.beginTransaction();

        transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction1.replace(R.id.mainframe, vp).addToBackStack("homepage")
                .commit();
    }
    public void Addproduct(View view){
            Intent intent = new Intent(this,AddItem.class);
            startActivity(intent);
    }
    public void OnViewReqeust(){
        Viewrequest vp = new Viewrequest();
        FragmentManager fragmentmanager0 = getSupportFragmentManager();
        FragmentTransaction transaction1 = fragmentmanager0.beginTransaction();

        transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction1.replace(R.id.mainframe, vp).addToBackStack("homepage")
                .commit();
    }

    public void RequestItem(View view) {

    }

}
