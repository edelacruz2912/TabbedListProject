package com.example.eduardo.tabbedlistproject;

import android.content.Context;
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

import java.util.ArrayList;

/**
 * Created by Eduardo on 5/2/2018.
 */

public class PersonalCustomTasksAdapter extends ArrayAdapter <PersonalTask> {

    Context personalContext;

    /*FIXING THE CHECKBOX BUTTON*/

    //to know whether the checkBox was clicked
    Boolean isElementCheck;


    CheckBox personalCheckBox ;

    public PersonalTask getPersonalElementTobeErase() {
        return personalElementTobeErase;
    }

    private static final String TAG = "PersonalCustomTasksAdap";

    ///To know the position for the element to be deleted
    int positionPersonal;


    //THE OBJECT TO BE ERASE WHEN YOU CLICK ON GARBAGE ICON
    PersonalTask personalElementTobeErase;




    public PersonalCustomTasksAdapter(Context context, ArrayList<PersonalTask> personal_tasks)
    {
        super(context,0,personal_tasks);
        this.personalContext = context;


    }




    public boolean isPersonalElementCheck() {
        return isElementCheck;
    }

    public int getPositionPersonal() {
        return positionPersonal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        // Get the data item for this position
        PersonalTask personal_task = getItem(position);


        //check if an existing view is being reused,otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.personal_list_view, parent, false);
        }

         /*personal CheckBox*/
         personalCheckBox = (CheckBox) convertView.findViewById(R.id.personalCheckBox);


        // Cache row position inside the checkBox using `setTag`
        personalCheckBox.setTag(position);


         /*Listener for checkBox*/
        personalCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonButtonPersonal, boolean isCheckedPersonal) {

                isElementCheck = isCheckedPersonal;

                //take the selected element and send it to the method that gets triggered when garbage can get executed
                if(isCheckedPersonal)
                {
                    ///TESTING
                    positionPersonal = (Integer) buttonButtonPersonal.getTag();///TO DELETE WITH THE GARBAGE ICON
                    personalElementTobeErase = getItem(positionPersonal);///TO DELETE WITH THE GARBAGE ICON

                    notifyDataSetChanged();
                    Log.d(TAG, "onCheckedChanged: deleting from oncheckChanged from PersonalCustomerTaskAdapter");
                    /*THE CODE BELOW WORKS TO DELETE AN ELEMENT USING THE CHECKBOX*/

                    PersonalTask personalTask = getItem(positionPersonal);
                    PersonalTask.removePersonalTask(personalTask);
                    notifyDataSetChanged();//need to let the UI know
                }

            }
        });

        // Lookup view for data population
        TextView personalTextViewTaskTitle = (TextView) convertView.findViewById(R.id.personalTaskTitleId);
        TextView personalTextViewTaskNote = (TextView) convertView.findViewById(R.id.personalTaskNoteId);


        // Populate the data into the template view using the data object
        personalTextViewTaskTitle.setText(personal_task.getTitleOfTask());
        personalTextViewTaskNote.setText(personal_task.getNoteOfTask());

        notifyDataSetChanged();

        return convertView;
    }


}
