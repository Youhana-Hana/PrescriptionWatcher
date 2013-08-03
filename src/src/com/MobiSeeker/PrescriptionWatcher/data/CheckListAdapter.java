package com.MobiSeeker.PrescriptionWatcher.data;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.MobiSeeker.PrescriptionWatcher.R;

public class CheckListAdapter extends ArrayAdapter<NodeObject>  implements OnCheckedChangeListener{

	CheckBox checkbox;
    protected Context context;
    protected List<NodeObject> nodeObjects;
    NodeObject selectedNodeObject;
    public CheckListAdapter(Context context, int textViewResourceId, List<NodeObject> entries)
    {
        super(context, textViewResourceId, entries);
        this.context = context;
        this.nodeObjects = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.checkitem, null);

            checkbox = (CheckBox) rowView.findViewById(R.id.checkbox);
           rowView.setTag(convertView);
        }
        selectedNodeObject = this.nodeObjects.get(position);
        checkbox.setText(selectedNodeObject.nodeDisplayedValue);
        checkbox.setOnCheckedChangeListener(this);
        return rowView;
        
    }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		selectedNodeObject.isCheck=isChecked;
	}

}
