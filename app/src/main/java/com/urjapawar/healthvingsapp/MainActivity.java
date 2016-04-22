package com.urjapawar.healthvingsapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public  Dialog dialog;
    ImageView image;
    TextView text;
    Button dialogButton;
    AlertDialog alertDialog;
    RecyclerView rv;
    public int x;
    Context context;
    LinearLayoutManager llm;
    private List<Person> persons;
    CustomAdapter adapter;
    public static final String[] titles = new String[] { "Bhagvad Geeta",
            "Quran", "Bible", "Budhhism",
            "Shri Guru Granth Sahib", "Story of my experiments with truth", "Long Walk of Freedom",
            "Wings of Fire", "Story of My life", "Vivekananda", "Devil may Care", "Harry Potter Series","Ignited Minds","2 States","Twilight" };

    public static final String[] descriptions = new String[] {

            "Meghnad Desai",
            "Abul Kaseem",
            "Moses",
            "Dalai Lama",
            "Guru Gobind Singh",
            "M K Gandhi",
            "Nelson Mandela",
            "Dr. A P J Abdul Kalam",
            "Helen Killer",
            "Swami Nikhilananda",
            "Sebastian Faulks",
            "J K Rowling",
            "dr A P J Abdul Kalam",
            "Chethan Bhagat",
            "Stephenie Meyer"

    };
    public static final Integer[] images = { R.drawable.geeta,
            R.drawable.namaaz, R.drawable.bible, R.drawable.buddhism,
            R.drawable.guru, R.drawable.gandhi, R.drawable.mandela,
            R.drawable.kalam, R.drawable.storyofmylife, R.drawable.vivek, R.drawable.bond, R.drawable.harry,
    R.drawable.minds, R.drawable.states, R.drawable.twilightbook};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom__dialog);
        dialog.setTitle("Title...");
        context =getApplicationContext();
        text = (TextView) dialog.findViewById(R.id.text);
        text.setText("Android custom dialog example!");
        image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(android.R.drawable.ic_popup_reminder);

        dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(x==5){
                initializeData(5);
                adapter = new CustomAdapter(persons);
                rv.setAdapter(adapter);}
                else if(x==10){
                    initializeData(10);
                    adapter = new CustomAdapter(persons);
                    rv.setAdapter(adapter);

                }
                else{
                    initializeData(0);
                    adapter = new CustomAdapter(persons);
                    rv.setAdapter(adapter);

                }
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);//Context parameter
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Toast.makeText(this, "okie", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setMessage("Simple Alert Dilaog");
         alertDialog = builder.create();
        alertDialog.setTitle("Alert Dialog");
        alertDialog.setMessage("Welcome!");
        rv = (RecyclerView)findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        initializeData(0);
       adapter = new CustomAdapter(persons);
        rv.setAdapter(adapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(rv,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipeLeft(int position) {
                                return true;
                            }

                            @Override
                            public boolean canSwipeRight(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
//                                    Toast.makeText(MainActivity.this, mItems.get(position) + " swiped left", Toast.LENGTH_SHORT).show();
                                    persons.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
//                                    Toast.makeText(MainActivity.this, mItems.get(position) + " swiped right", Toast.LENGTH_SHORT).show();
                                    persons.remove(position);
                                    adapter.notifyItemRemoved(position);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });

        rv.addOnItemTouchListener(swipeTouchListener);
    }


        private void initializeData(int i){
            persons = new ArrayList<>();
            for(x=i; x<i+5; x++){
                Person p = new Person(titles[x], descriptions[x],images[x]);
                persons.add(p);
            }
        }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toast_2) {
           Toast.makeText(this,"Toast 2 is of short duration", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.toast_1) {
            Toast.makeText(this,"Toast 1 is of long duration", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {

            dialog.show();
        }



        else if (id == R.id.nav_send) {
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
