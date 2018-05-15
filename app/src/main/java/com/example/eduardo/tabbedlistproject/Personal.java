package com.example.eduardo.tabbedlistproject;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Personal extends Fragment  implements AdapterView.OnItemClickListener{

    //PERSONAL CUSTOM ADAPTER
    PersonalCustomTasksAdapter personalAdapter;

    //Personal Adapter listView
    ListView listView;

    //tag for on create

    private static final String TAG = "Personal";

    //
    private String mParam1;
    private String mParam2;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /*FRAGMENT INTERFACE*/
    private OnFragmentInteractionListener mListener;




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
        ArrayList<PersonalTask> arrayOfPersonalTasks = PersonalTask.getPersonalTask();



        //bussness Adapter listView
        listView = (ListView) view.findViewById(R.id.personalListView);


        // Create the adapter to convert the array to views

        personalAdapter = new PersonalCustomTasksAdapter(getActivity(),arrayOfPersonalTasks);


        personalAdapter.notifyDataSetChanged();
        // Attach the adapter to a ListView
        listView.setAdapter(personalAdapter);


        if(arrayOfPersonalTasks.isEmpty())
        {
            Log.d("emptyLIst","the list is empty FROM Personal FRAGMENT");
            personalAdapter.notifyDataSetChanged();
        }
        else{
            Log.d("noEMPTYLIST","the list is not empty FROM Personal FRAGMENT");
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



    public static Personal newInstance(String param1,String param2) {
        Personal fragment = new Personal();
        Bundle args = new Bundle();
//        args.putSerializable(ARG_PARAM1, task);
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM1, param2);
        fragment.setArguments(args);
        //PersonalTask t= (PersonalTask) args.getSerializable(ARG_PARAM1);
        //addTask(t);
        //PersonalTask.taskPersonal.add(t);
        return fragment;
    }


        @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.d(TAG, "onCreate: inside of personal Fragment " + getArguments() );
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
        if (context instanceof Buseness.OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //AdapterView.OnItemClickListener interface
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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
