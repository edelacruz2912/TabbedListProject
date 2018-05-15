package com.example.eduardo.tabbedlistproject;

/**
 * Created by Eduardo on 5/13/2018.
 */

public class TaskItem {


    /*
    *    A data model is a class that encapsulates a complex data structure
    *   and provides an API for accessing and manipulating the data in the structure.
    */

    private int mId;
    private String mTask;

    public TaskItem()
    {



    }

    public int getmId() {
        return this.mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTask() {
        return this.mTask;
    }

    public void setmTask(String mTask) {
        this.mTask = mTask;
    }
}
