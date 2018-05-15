package com.example.eduardo.tabbedlistproject;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.eduardo.tabbedlistproject.Task.task;

public class taskCreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /*For spinner*/
    Spinner spinner;

    /*EDIT task*/
    EditText titleTask;
    EditText noteTask;

    /*To get element selected from spinner*/
    Object SpinnerItem = null;

    /**/
    String taskT;
    String taskN;
    //Task task;


    /*Fragment Manager to send data*/

    private static FragmentManager fragmentManager;




    /*key for the intent extra*/
    //public static final String BUSINESS_EXTRA_TITLE= "com.example.android.twoactivities.extra.Value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_task);

        /*Spinner*/
        spinner = (Spinner) findViewById(R.id.listSpinner);
        spinner.setOnItemSelectedListener(this);//spinner listener

        //task = new Task(taskT,taskN);

        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        /*Edit Task*/

        titleTask = findViewById(R.id.editTitleTask);
        noteTask = findViewById(R.id.notesEditText);

        //////////////////
        /*TO SEND DATA TO ACTIVITY*/
        fragmentManager = getSupportFragmentManager();


        /* */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /*Spinner method from the interface*/
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

        // An item was selected. You can retrieve the selected item using
       SpinnerItem = adapterView.getItemAtPosition(position);

        Log.i("selected from spinner" , " " +  adapterView.getItemAtPosition(position));

        }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    /*MENU FOR THE CREATE TASK ACTIVITY*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_creator_menu, menu);//to inflate the menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*Action to be perfomed when the plus sign */
            case R.id.comfirmationButton:
                //showOrder();
                /*CREATE TO A RETURN INTENT TO RETURN TO THE FRAGMENT */
                Log.d(" comfirmationButton", "comfirmationButton ");


                switch (SpinnerItem.toString())
                {
                    case "Buseness":
                        Log.d("buseness case", "buseness case executed");
                        taskT = titleTask.getText().toString();
                        taskN = noteTask.getText().toString();

                        Log.d("taskT", " " + taskT);

                        Log.d("taskN ", " " + taskN);


                        Task.task.add(new Task(taskT,taskN));

                        Intent result = new Intent();

                        setResult(RESULT_OK,result);
                        finish();

                        if(task.isEmpty())
                        {
                            Log.d("noItemList" , "no item added to the arrayList ni the task Creattion Activity");

                        }
                        else
                        {
                            Log.d("ItemAdded","the item has been added to the arrayLIst from taskCreationActivityClass");
                        }

                        break;

                    case "Personal":
                        Log.d("Personal case", "Personal case executed");


                        taskT = titleTask.getText().toString();
                        taskN = noteTask.getText().toString();

                        Log.d("taskT", " " + taskT);

                        Log.d("taskN ", " " + taskN);


                        //Task.task.add(new Task(taskT,taskN));
                        PersonalTask.taskPersonal.add(new PersonalTask(taskT,taskN));

                        Intent personalResult = new Intent();

                        setResult(RESULT_OK,personalResult);
                        finish();

                        if(task.isEmpty())
                        {
                            Log.d("noItemList" , "no item added to the arrayList ni the task Creattion Activity from personal Case");

                        }
                        else
                        {
                            Log.d("ItemAdded","the item has been added to the arrayLIst from taskCreationActivityClass from personal Case");
                        }


                        break;


                    default :
                        // Statements



                }


                return true;

            case R.id.denyButton:
                Log.d(" DeleteButton", "DelteBUtton triggered");
                //Send you to main Activity if button declined from creation of task is clicked
                Intent intentToMainActivity = new Intent(this, MainActivity.class);
                startActivity(intentToMainActivity);

                return true;


            default:
                return super.onOptionsItemSelected(item);


        }

    }

}