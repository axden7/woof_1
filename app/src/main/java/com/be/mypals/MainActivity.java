package com.be.mypals;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.content.ContextCompat;

import com.be.mypals.Fragments.AccountFragment;
import com.be.mypals.Fragments.EventsFragment;
import com.be.mypals.Fragments.HomeFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MaterialSearchView searchView;
    ListView listView;

    String[] listSource = {
            "Abel",
            "Bravo",
            "Sam Sam",
            "Wave",
            "Mack Mack",
            "Destiny",
            "Starry",
            "Chester",
            "CX",
            "Gypsy",
            "CX",
            "Gypsy",
            "CX",
            "Gypsy",
            "Woofiey"
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = HomeFragment.newInstance();
                    System.out.println("home");
                    getSupportActionBar().setTitle(R.string.app_name);
                    return true;
                case R.id.navigation_event:
                    selectedFragment = EventsFragment.newInstance();
                    System.out.println("event");
                    getSupportActionBar().setTitle(R.string.title_events);
                    return true;
                case R.id.navigation_account:
                    selectedFragment = AccountFragment.newInstance();
                    System.out.println("account");
                    getSupportActionBar().setTitle(R.string.title_account);
                    return true;
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));


        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                // if closed SearchView, listView will return default
                listView = (ListView)findViewById(R.id.listView);
                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,listSource);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText != null && !newText.isEmpty()) {
                    List<String> listFound = new ArrayList<String>();
                    for(String item : listSource) {
                        System.out.println("newText : " + newText);
                        System.out.println("item.contains(newText) : " + item.contains(newText));
                        if (item.toLowerCase().contains(newText.toLowerCase())) {
                            listFound.add(item);
                        }
                    }
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, listFound);
                    listView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    listView.setAdapter(adapter);
                } else {
                    // else return default
                    ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1, listSource);

                }
                return true;
            }
        });
        listView = (ListView)findViewById(R.id.listView);

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        System.out.println("first fragment");
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

}
