package com.example.eduardo.tabbedlistproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Personal extends Fragment  {


    public Personal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         /*FOR THE CUSTOM XML*/
        View view = inflater.inflate(R.layout.fragment_personal, container, false);


        // Construct the data source
        ArrayList<PersonalTask> arrayOfPersonalTasks = PersonalTask.getPersonalTask(new PersonalTask("paasdasdasd","do well"));



        //bussness Adapter listView
        ListView listView = (ListView) view.findViewById(R.id.personalListView);


        // Create the adapter to convert the array to views

        PersonalCustomTasksAdapter personalAdapter = new PersonalCustomTasksAdapter(getActivity(),arrayOfPersonalTasks);


        personalAdapter.notifyDataSetChanged();
        // Attach the adapter to a ListView
        listView.setAdapter(personalAdapter);


        if(arrayOfPersonalTasks.isEmpty())
        {
            Log.d("emptyLIst","the list is empty FROM BUSENESS FRAGMENT");
            personalAdapter.notifyDataSetChanged();
        }
        else{
            Log.d("noEMPTYLIST","the list is not empty FROM BUSENESS FRAGMENT");
//            notifyAll();
//            notify();
            // notifyDataSetChanged();
            personalAdapter.notifyDataSetChanged();
            view.refreshDrawableState();

        }


        personalAdapter.notifyDataSetChanged();
        // Attach the adapter to a ListView
        listView.setAdapter(personalAdapter);


        return view;
    }

}
