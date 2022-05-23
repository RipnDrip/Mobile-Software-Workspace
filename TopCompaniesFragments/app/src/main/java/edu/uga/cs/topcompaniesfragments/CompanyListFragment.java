package edu.uga.cs.topcompaniesfragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

/**
 * A simple activity to show Companies utilizing fragments.
 */
public class CompanyListFragment extends ListFragment {

    // Array of Android versions strings for the list fragment
    private final String[] comapanyList = {
            "Apple",
            "Facebook",
            "Google",
            "Linkedin",
            "Microsoft"
    };

    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

    // list selection index
    int companyIndex = 0;


    public CompanyListFragment()
    {
        // required default constructor
    }

    @Override
    public void onViewCreated( @NonNull View view, Bundle savedInstanceState ) {

        super.onViewCreated(view, savedInstanceState);

        // create a new ArrayAdapter for the list
        setListAdapter( new ArrayAdapter<>( getActivity(), android.R.layout.simple_list_item_activated_1, comapanyList ) );


        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById( R.id.companyListInfo );


        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (Company List no), if available
        if( savedInstanceState != null ) {
            // Restore last state for checked position.
            companyIndex = savedInstanceState.getInt("companySelection", 0 );
        }

        // set the list mode as single choice and
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
        getListView().setItemChecked(companyIndex, true );

        // if we are in 2 fragment (landscape) orientation, show the Company List information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if( twoFragmentsActivity ) {

            // display the information about the selected Company List in the fragment on the right (details)
            showCompanyInfo(companyIndex);

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition(companyIndex);
        }

    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {
        // on a click on a list item, show the selected Android version info
        // store the list view and selection for coming back to the list (using the back button)
        //firstVisibleVersionIndex = l.getFirstVisiblePosition();
        //companyIndex = position;
        showCompanyInfo( position );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt( "companySelection", companyIndex);
    }

    void showCompanyInfo(int versionIndex ) {

        this.companyIndex = versionIndex;

        if( twoFragmentsActivity ) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked( versionIndex, true );

            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            CompanyListInfoFragment details = (CompanyListInfoFragment) getParentFragmentManager().findFragmentById( R.id.companyListInfo );

            if( details == null || details.getShownCompanyIndex() != versionIndex ) {

                // obtain a new Android info fragment instance
                details = CompanyListInfoFragment.newInstance( versionIndex );

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace( R.id.companyListInfo, details );

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_FADE );

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        }
        else {
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass( getActivity(), CompanyListInfoActivity.class );
            intent.putExtra("companyIndex", versionIndex);

            startActivity( intent );
        }
    }
}
