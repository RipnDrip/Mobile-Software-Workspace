package edu.uga.cs.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {

    public StateAdapter(Context context, List<State> states) {
        super(context, 0, states);
    }

    @Override
    public View getView(int position, View stateView, ViewGroup parent) {
        // Get the data item for this position
        State state = getItem(position);

        // if necessary, inflate the view to show the next state;  otherwise it will be reused
        if( stateView == null ) {
            stateView = LayoutInflater.from( getContext()).inflate(R.layout.state, parent, false);
        }
        // Lookup view for data population
        TextView stateTextView = (TextView) stateView.findViewById( R.id.stateView );
        TextView capitalTextView = (TextView) stateView.findViewById( R.id.capitalView );
        // Populate the data into the template view using the data object
        stateTextView.setText( state.getState() );
        capitalTextView.setText( state.getCapital() );

        // Return the completed view
        return stateView;
    }
}
