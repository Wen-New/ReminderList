package sg.edu.rp.c346.eseproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Reminder> reminder;

    public CustomAdapter(Context context, int resource,ArrayList<Reminder> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        reminder = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        TextView tvTitle = (TextView) rowView.findViewById(R.id.textViewTitle);
        TextView tvPurpose = (TextView) rowView.findViewById(R.id.textViewPur);
        TextView tvDateTime = (TextView) rowView.findViewById(R.id.textViewDateTimeStated);
        ImageView ivGreen = (ImageView) rowView.findViewById(R.id.imageViewGreen);

        Reminder currentItem = reminder.get(position);

        if (currentItem == null) {
            tvTitle.setText("");
            tvPurpose.setText("");
            tvDateTime.setText("");
        } else {
            String title = "Title: " + currentItem.getTitle();
            String object = "Objective: " + currentItem.getPurpose();
            String datetime = "Deadline: " + currentItem.getDeadline() + " " + currentItem.getTime() + " h";
            ivGreen.setImageResource(R.drawable.star);
            tvTitle.setText(title);
            tvPurpose.setText(object);
            tvDateTime.setText(datetime);
        }
        return rowView;
    }
}
