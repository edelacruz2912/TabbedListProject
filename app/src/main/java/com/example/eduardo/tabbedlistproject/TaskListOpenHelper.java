package com.example.eduardo.tabbedlistproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Eduardo on 5/13/2018.
 */

public class TaskListOpenHelper extends SQLiteOpenHelper{


    //Instantiate a Cursor variable to null to hold the result from the database.
    Cursor cursor;

    //Tag for the catch
    //private static final String TAG = "Exectption";

    // has to be 1 first time or app will crash
    private static final int DATABASE_VERSION = 1;
    private static final String WORD_LIST_TABLE = "Task_entries";
    private static final String DATABASE_NAME = "TaskList";

    // Column names...
    public static final String KEY_ID = "_id";
    public static final String KEY_WORD = "task";


    // ... and a string array of columns.
    private static final String[] COLUMNS = { KEY_ID, KEY_WORD };


    // It's a good idea to always define a log tag like this.
    private static final String TAG = TaskListOpenHelper.class.getSimpleName();


    // Build the SQL query that creates the table.
    // id will auto-increment if no value passed
    private static final String WORD_LIST_TABLE_CREATE = "CREATE TABLE " + WORD_LIST_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_WORD + " TEXT );";

    /*
    * Add instance variables for the references to writable and readable databases.
    * Storing these references saves you to work of getting a database
    *  reference every time you need to read or write.
    * */

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;



    public TaskListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "TaskListOpenHelper: constructor of the database created");
    }



    @Override  // sqLiteDatabase = db
    public void onCreate(SQLiteDatabase db) {

        //In the onCreate method, add code to create a database and the table
        //(The helper class does not create another database, if one already exists.)

        db.execSQL(WORD_LIST_TABLE_CREATE);

        fillDatabaseWithData(db);


    }

    /*Filling data to DataBase*/

    private void fillDatabaseWithData(SQLiteDatabase db)
    {

        /*Mock Data*/

       /* String[] words = {"Android", "Adapter", "ListView", "AsyncTask",
                "Android Studio", "SQLiteDatabase", "SQLOpenHelper",
                "Data model", "ViewHolder","Android Performance",
                "OnClickListener"};*/



        ///EDUARDO YOU MIGHT BE ABLE TO USE THE static ArrayList.
       ;

        // Create a container for the data.
        ContentValues values = new ContentValues();


        for (int i=0; i <  Task.task.size(); i++) {
            // Put column/value pairs into the container.
            // put() overrides existing values.
            //values.put(KEY_WORD, words[i]);
            values.put(KEY_WORD,Task.task.get(i).getTitleOfTask());
//            values.put(KEY_WORD,Task.task.get(i).getNoteOfTask());
            db.insert(WORD_LIST_TABLE, null, values);
        }

    }



    //The query() method retrieves rows from the database as selected by a SQL query.
    //For this sample, in order to display words in the RecyclerView, we need to get them
    //from the database, one at a time, as needed. The word needed is identified by its
    //position in the view.
    //As such, the query method has a parameter for the requested position and returns a WordItem.

    public TaskItem query(int position)
    {
        //Construct a query that returns only the nth row of the result. Use LIMIT with
        //position as the row, and 1 as the number of rows.

        String query = "SELECT  * FROM " + WORD_LIST_TABLE + " ORDER BY " + KEY_WORD + " ASC " + "LIMIT " + position + ",1";

        cursor = null;

        TaskItem entry = new TaskItem();

        try {

            if (mReadableDB == null)
            {
                mReadableDB = getReadableDatabase();
            }

            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            //entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            //entry.setmWord(cursor.getString(cursor.getColumnIndex(KEY_WORD)));
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setmTask(cursor.getString(cursor.getColumnIndex(KEY_WORD)));




        }
        catch (Exception e)
        {
            Log.d(TAG, "EXCEPTION! " + e);
        }
        finally
        {

            cursor.close();
            return entry;

        }


    }

    /*Insert Method*/

    public long insert(Task task)
    {

        //Declare a variable for the id. If the insert operation fails, the method returns 0.
        long newId = 0;

        //As before, create a ContentValues value for the row data.
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, task.getTitleOfTask() );
        values.put(KEY_WORD, task.getNoteOfTask() );

        //Put your database operation into a try/catch block.

        try
        {

            //Get a writable database if one doesn't already exist.
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }

            //Insert the row.
            newId = mWritableDB.insert(WORD_LIST_TABLE, null, values);

        }
        catch (Exception e)
        {
            //Log the exception.

            Log.d(TAG, "INSERT EXCEPTION! " + e.getMessage());

        }


        return newId;

    }

    public long count(){
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }

        return DatabaseUtils.queryNumEntries(mReadableDB, WORD_LIST_TABLE);
        //return (int) mDB.count();
    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TaskListOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + WORD_LIST_TABLE);
        onCreate(db);

    }

    public int delete(int id) {
        int deleted = 0;
        try {
            if (mWritableDB == null) {
                mWritableDB = getWritableDatabase();
            }
            deleted = mWritableDB.delete(WORD_LIST_TABLE, //table name
                    KEY_ID + " =? ", new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d (TAG, "DELETE EXCEPTION! " + e.getMessage());
        }
        return deleted;
    }






    /*GETTING Data from Data  */

    public  ArrayList <Task> getTaskList()
    {
        //NEW OBJECTS
        //ArrayList<String> taskListFromDataBase = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Log.d("DbHelper", "GETTING THE ARRAYLIST '" + taskListFromDataBase + "'");
        Log.d("DbHelper", "GETTING THE ARRAYLIST");

        Cursor cursor = db.query(WORD_LIST_TABLE,new String[] {},null,null,null,null,null);

        while(cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(DATABASE_NAME );
            //taskListFromDataBase.add(cursor.getString(index));
            Task.task.add(new Task(cursor.getString(index),"Something"));
        }


        cursor.close();
        db.close();


        //return taskListFromDataBase;
        return Task.getTask();
    }
}
