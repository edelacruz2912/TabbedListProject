package com.example.eduardo.tabbedlistproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.eduardo.tabbedlistproject.R;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;
import static com.example.eduardo.tabbedlistproject.Task.getTask;

/**
 * Created by Eduardo on 4/28/2018.
 */

public class CustomTasksAdapter extends ArrayAdapter<Task>  {

    Context context;
    CheckBox businessCheckBox;

    /*To get data item for the giving position for from the listView*/
    Task task;


    public CustomTasksAdapter(Context context, ArrayList<Task> tasks)
    {
        super(context,0,tasks);
        this.context = context;

    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // Get the data item for this position
        Task task = getItem(position);



        //check if an existing view is being reused,otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.busness_list_view, parent, false);
        }

        /*Busness CheckBox*/
        businessCheckBox = (CheckBox) convertView.findViewById(R.id.businessCheckBox);
        // Cache row position inside the checkBox using `setTag`
        businessCheckBox.setTag(position);


        /*Listener for checkBox*/
        businessCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // update your model (or other business logic) based on isChecked
                Log.d("checkBox"  , "checkbox has been clicked" );
                //int position = (Integer) businessCheckBox.getTag();
                Log.d("position clicked"  ,  " " + (Integer) businessCheckBox.getTag());
                //take the selected element and send it to the method that gets triggered when garbage can get executed
                if(isChecked)
                {
                    Log.d("bolleanChecked"  , "checkBox is check " + businessCheckBox.isChecked() );
                    //if it is true take view and send it to the delete Button
//                    Intent i = new Intent(context,MainActivity.class);
//                    i.putExtra("busnessTab",(Integer) businessCheckBox.getTag());
//                    context.startActivity(i);

                    // Task.removeTask((Integer) businessCheckBox.getTag());
                    //Task.task.remove((Integer) businessCheckBox.getTag());
                    //Intent i = new Intent(context,MainActivity.class);
                    //context.startActivity(i);

                    /*TO ASK SOFIANOS*/
                    /*
                    * WHEN CHECKBOX IS CLICKED TO ERASE THE ELEMENT
                    * ONCE I CALL REMOVE method from arraylist how can re render the listview so it doesnt appear
                    *
                    * */
                    int position = (Integer) buttonView.getTag();

                    Task task = getItem(position);
                    Task.removeTask(task);
                    notifyDataSetChanged();//need to let the UI know


                }
            }
        });



        // Lookup view for data population
        TextView businessTextViewTaskTitle = (TextView) convertView.findViewById(R.id.bTaskTitleId);
        TextView businessTextViewTaskNote = (TextView) convertView.findViewById(R.id.businessTaskNoteId);



        // Populate the data into the template view using the data object
        businessTextViewTaskTitle.setText(task.getTitleOfTask());
        businessTextViewTaskNote.setText(task.getNoteOfTask());

        notifyDataSetChanged();

        // Return the completed view to render on screen
        return convertView;

    }
}