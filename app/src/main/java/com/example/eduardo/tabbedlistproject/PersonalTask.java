package com.example.eduardo.tabbedlistproject;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Eduardo on 5/2/2018.
 */

public class PersonalTask {

    private String personalTitleTask;
    private String personalNoteTask;
    static ArrayList<PersonalTask> taskPersonal;

    public PersonalTask(String taskName, String notes)
    {
            //referring to class fields
            this.personalTitleTask = taskName;
            this.personalNoteTask = notes;

    }

    //retrieve task Title
    public String getTitleOfTask()
        {
            return this.personalTitleTask;
        }

    //retrieve task Title
    public String getNoteOfTask()
        {
            return this.personalNoteTask;
        }

    public void setTaskTitle(String taskTitle)
        {
            this.personalTitleTask = taskTitle;
        }

    public void setTaskNote(String taskNote)
        {
            this.personalNoteTask = taskNote;
        }


    public static ArrayList<PersonalTask> getPersonalTask(PersonalTask x) {

        /*CREATING THE INTENT TO RECIEVE DATA FROM BUSINESS and populating The data for business Tab*/
        taskPersonal = new ArrayList<PersonalTask>();
        Log.d("getTask Method"  , "get Task Method executed Saved it to list" );

        taskPersonal.add(new PersonalTask("Harry", "San Diego"));
        taskPersonal.add(new PersonalTask("Marla", "San Francisco"));
        taskPersonal.add(new PersonalTask("Sarah", "San Marco"));
        taskPersonal.add(new PersonalTask("NewData0", "motnro porque"));
        taskPersonal.add(new PersonalTask("Harry", "San Diego"));
        taskPersonal.add(new PersonalTask("Marla", "San Francisco"));
        taskPersonal.add(new PersonalTask("Sarah", "San Marco"));
        taskPersonal.add(new PersonalTask("NewData0", "motnro porque"));
            // Log.d("im hereeee"  , x.toString() );

        taskPersonal.add(x);


        return taskPersonal;

        }

        public static void removePersonalTask(PersonalTask t) {

            taskPersonal.remove(t);
            Log.d("removeMethod"  , "Arraylist remove() executed" );
        }



        public void setPersonalTask(String x , String y)
        {
            //task = new ArrayList<Task>();
            taskPersonal.add(new PersonalTask(x,y));
        }
    }



