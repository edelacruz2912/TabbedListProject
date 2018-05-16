package com.example.eduardo.tabbedlistproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.LinkedList;



public class MainActivity extends AppCompatActivity implements Buseness.OnFragmentInteractionListener,Personal.OnFragmentInteractionListener{


    private final static int REQ_CODE = 1; //code to start activityForResult

    private static final String TAG = "MainActivity";


    /*TOOLBAR USE FOR THE SHARE PREFERENCE*/

    Toolbar toolbar;

    ///TO get data to be able to delete with the garbage can
    PersonalCustomTasksAdapter personalTaskNumber;


    PersonalTask personalTask;

    /*FOR THE DATABASE*/
    //TaskListOpenHelper = WordLIstOpenHelper
    private TaskListOpenHelper mDB;

    /*Share preference */
    Toolbar mToolBar;

    /*TabLayout*/

    TabLayout tabLayout;

    /*Camara*/

    FloatingActionButton camara;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*             DATABASE               */

        mDB = new TaskListOpenHelper(this);

        //////////////////////////

        /*Share Preference*/

        toolbar = (Toolbar) findViewById(R.id.toolbar);//use for the share preference
        setSupportActionBar(toolbar);

        /*for the title of the toolBar*/
        toolbar.setTitle(getResources().getString(R.string.app_name));


        /*Share preference Button*/

        /*TO CHECK IF ANY COLOR IS SAVE IN ShARE PREFERENCE*/

        if(getColor() != getResources().getColor(R.color.colorPrimary))
        {

            toolbar.setBackgroundColor(getColor());
            //tabLayout.setBackgroundColor(getColor());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getColor());
            }

        }

        ////////CAMARA//////////

        camara = (FloatingActionButton) findViewById(R.id.camera);

        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });


        // Create an instance of the tab layout from the view.

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(getColor());

        //Set the text for each tab
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));

        // Set the tabs to fill the entire layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Use PagerAdapter to manage page views in fragments

        // Using PagerAdapter to manage page views in fragments.
        // Each page is represented by its own fragment.
        // This is another example of the adapter pattern.

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        // Setting a listener for clicks.

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("onTabSelected", "onTabSelected method triggered");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("onTabUnselected", "onTabUNSelected method triggered");


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {



            }
        });

    }

    /* Share Preference */

    private void storeColor(int color)
    {
        SharedPreferences mSharePreferences = getSharedPreferences("ToolbarColor",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharePreferences.edit();
        mEditor.putInt("color",color);
        ///NEED TO BE SAVE TO MAKE THE CHANGES
        mEditor.apply();


    }

    private int getColor()
    {
        SharedPreferences mSharePreferences = getSharedPreferences("ToolbarColor",MODE_PRIVATE);
        //SharedPreferences.Editor mEditor = mSharePreferences.edit();

       int selectedColor =  mSharePreferences.getInt("color",getResources().getColor(R.color.colorPrimary));

       return selectedColor;
    }


    /*OPTION MENU*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        personalTaskNumber = new PersonalCustomTasksAdapter(getApplicationContext(),PersonalTask.taskPersonal);

        switch (item.getItemId())
        {
            /*Action to be perfomed when the plus sign */
            case R.id.plusSign:
                //showOrder();
                Intent taskCreatorIntent = new Intent(this, taskCreationActivity.class);
                startActivityForResult(taskCreatorIntent,REQ_CODE);
                Log.d(" Plus sign Selected", "Plus sign Selected");




                return true;


            case R.id.Red:

                Log.d(" RedColor", "Red Color Selected");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
                tabLayout.setBackgroundColor(getResources().getColor(R.color.colorRed));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
                }

                storeColor(getResources().getColor(R.color.colorRed));


                break;


            case R.id.Green:

                Log.d(" GreeColor", "Green Color Selected");
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                tabLayout.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreen));
                }

                storeColor(getResources().getColor(R.color.colorGreen));


                break;


            case R.id.deleteSign:
                Log.d(" Delete sign Selected", "Delete sign Selected");

                if(true)
                {
                    Log.d(TAG, "onOptionsItemSelected: deleting from main activity");
                    //int deleteElementAtIndex = personalTaskNumber.getPositionPersonal();
                    //PersonalTask.taskPersonal.remove(deleteElementAtIndex);

                    PersonalTask.removePersonalTask(personalTaskNumber.getPersonalElementTobeErase());

                }

                return true;


            default:
                return super.onOptionsItemSelected(item);

        }

        return false;
    }




    /*For business Fragment*/

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

