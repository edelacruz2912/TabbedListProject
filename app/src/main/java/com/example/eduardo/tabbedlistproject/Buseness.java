package com.example.eduardo.tabbedlistproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.eduardo.tabbedlistproject.Task.task;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buseness extends Fragment implements AdapterView.OnItemClickListener {

    /*FOR GETTING THE VALUES FROM taskCreationActivty*/
    //String taskTitleFromClass;
    //String taskNoteFromClass;

    CustomTasksAdapter businessAdapter;
    ListView listView;
    String task_T;
    String task_N;




    public Buseness() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //task = new ArrayList<Task>();
        //task.add(new Task("lol","why"));

        /*GETTING DATA FROM TASKCREATION ACTIVITY*/

//        String data = getArguments().getString("dataTitle");




        /*FOR THE CUSTOM XML*/
        View view = inflater.inflate(R.layout.fragment_buseness, container, false);

        /*GETTING VALUES FROM taskCreationActivity*/
        //taskTitleFromClass = getArguments().getString("titleTask");
        //taskNoteFromClass = getArguments().getString("noteTask");


        //task_T = getActivity().getIntent().getExtras().getString("TASK_T");
        //task_N = getActivity().getIntent().getExtras().getString("TASK_T");


        // Construct the data source
        ArrayList<Task> arrayOfBusinessTasks = Task.getTask(new Task(task_T,task_N));





        //bussness Adapter listView
        listView = (ListView) view.findViewById(R.id.busnessListView);

        // Create the adapter to convert the array to views

        businessAdapter = new CustomTasksAdapter(getActivity(),arrayOfBusinessTasks);

        businessAdapter.notifyDataSetChanged();
        // Attach the adapter to a ListView
        listView.setAdapter(businessAdapter);

         /*UPDATING */
        //view.refreshDrawableState();

        if(arrayOfBusinessTasks.isEmpty())
        {
            Log.d("emptyLIst","the list is empty FROM BUSENESS FRAGMENT");
            businessAdapter.notifyDataSetChanged();
        }
        else{
            Log.d("noEMPTYLIST","the list is not empty FROM BUSENESS FRAGMENT");
//            notifyAll();
//            notify();
            // notifyDataSetChanged();
            businessAdapter.notifyDataSetChanged();
            view.refreshDrawableState();

        }

         // Attach the adapter to a ListView
        listView.setAdapter(businessAdapter);

        businessAdapter.notifyDataSetChanged();

        return view;
    }

    //To populate custom View

    public void populateTaskList()
    {
        // Construct the data source
        //ArrayList<Task> tasks = Task.getTask();


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
