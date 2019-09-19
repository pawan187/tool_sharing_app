package com.example.demo;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
   // public Fragment home = ( Fragment) findViewById(R.id.home);


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
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
                    mTextMessage.setText(R.string.title_dashboard);
                    dashboard fragmenttwo =new dashboard();
                    FragmentManager fragmentmanager1 = getSupportFragmentManager();

                    FragmentTransaction transaction1 = fragmentmanager1.beginTransaction();

                    transaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    transaction1.replace(R.id.mainframe, fragmenttwo).addToBackStack("homepage")
                            .commit();

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
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
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
