package com.example.eduardo.tabbedlistproject;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    //
    private String mParam1;
    private String mParam2;

    private static final String TAG = "BusenessOnCreate";
    private OnFragmentInteractionListener mListener;




    public Buseness() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        /*FOR THE CUSTOM XML*/
        View view = inflater.inflate(R.layout.fragment_buseness, container, false);
        // Construct the data source
        ArrayList<Task> arrayOfBusinessTasks = Task.getTask();
        //bussness Adapter listView
        listView = (ListView) view.findViewById(R.id.busnessListView);
        // Create the adapter to convert the array to views
        businessAdapter = new CustomTasksAdapter(getActivity(),arrayOfBusinessTasks);
        //businessAdapter.addAll(arrayOfBusinessTasks);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.d(TAG, "onCreate: inside of bussness Fragment " + getArguments() );
        }


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
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    public static Buseness newInstance(String param1, String param2) {
        Buseness fragment = new Buseness();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Buseness newInstance(){
        return new Buseness();
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



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}