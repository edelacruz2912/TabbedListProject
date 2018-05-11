package com.example.eduardo.tabbedlistproject;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Eduardo on 4/28/2018.
 */

public class Task {

    private String titleOfTask;
    private String noteOfTask;
    static ArrayList<Task> task;

    public Task(String taskName, String notes)
    {
        //referring to class fields
        this.titleOfTask = taskName;
        this.noteOfTask = notes;


    }

    //retrieve task Title
    public String getTitleOfTask()
    {
        return this.titleOfTask;
    }

    //retrieve task Title
    public String getNoteOfTask()
    {
        return this.noteOfTask;
    }

    public void setTaskTitle(String taskTitle)
    {
       this.titleOfTask = taskTitle;
    }

    public void setTaskNote(String taskNote)
    {
        this.titleOfTask = taskNote;
    }


    public static ArrayList<Task> getTask(Task x) {

        /*CREATING THE INTENT TO RECIEVE DATA FROM BUSINESS and populating The data for business Tab*/
        task = new ArrayList<Task>();
        Log.d("getTask Method"  , "get Task Method executed Saved it to list" );

        task.add(new Task("Harry", "San Diego"));
        task.add(new Task("Marla", "San Francisco"));
        task.add(new Task("Sarah", "San Marco"));
        task.add(new Task("NewData0", "motnro porque"));
        task.add(new Task("yo soy un loco ", "motnro porque"));
      // Log.d("im hereeee"  , x.toString() );

        task.add(x);


        return task;
    }

    public static void removeTask(Task t) {

        task.remove(t);
        Log.d("removeMethod"  , "Arraylist remove() executed" );
    }



    public void setTask(String x , String y)
    {
        //task = new ArrayList<Task>();
        task.add(new Task(x,y));
    }
}
