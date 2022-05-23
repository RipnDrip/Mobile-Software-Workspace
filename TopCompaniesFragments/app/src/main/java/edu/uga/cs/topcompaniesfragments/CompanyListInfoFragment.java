package edu.uga.cs.topcompaniesfragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple activity to show Companies utilizing fragments.
 */
public class CompanyListInfoFragment extends Fragment {

    // Create Company information;
    private final String[] info = {
            "Apple\n" +
                    "\n" +
                    "Work Culture:\n" +
                    "-  People work at Apple because they are design fanatics.\n" +
                    "   The elegant hardware products are seen by many as unmatched\n" +
                    "   in the tech industry.\n" +
                    "\n" +
                    "Headquarters Location:\n" +
                    "- Cupertino, California\n" +
                    "\n" +
                    "Number of Employees:\n" +
                    "- 147,000\n" +
                    "\n" +
                    "Annual revenue:\n" +
                    "- $274.52 billion\n" +
                    "\n" +
                    "Average Salary:\n" +
                    "- $128,000\n" +
                    "\n" +
                    "Average bonus:\n" +
                    "- $12,000\n" +
                    "\n" +
                    "Dress Code:\n" +
                    "- No dress code, very casual\n" +
                    "\n" +
                    "Benefits:\n" +
                    "- 401k\n" +
                    "- Health, Dental, and Life Insurance\n" +
                    "- Paid time off\n" +
                    "\n" +
                    "Work Environment:\n" +
                    "- At a glance, it can be described as fun, relaxed, and casual, however\n" +
                    "  Apple is heavily committed to achieving deadlines so it can still\n" +
                    "  be demanding",
            "Facebook\n" +
                    "\n" +
                    "Work Culture:\n" +
                    "- Transparency and diversity are the highlights of Facebook's work culture.\n" +
                    "  You learn how to invent and execute at a scale\n" +
                    "\n" +
                    "Headquarters Location:\n" +
                    "- Menlo Park, California\n" +
                    "\n" +
                    "Number of Employees:\n" +
                    "- 58,604\n" +
                    "\n" +
                    "Annual revenue:\n" +
                    "- $85.97 billion\n" +
                    "\n" +
                    "Average salary\n" +
                    "- $124,000\n" +
                    "\n" +
                    "Average bonus\n" +
                    "- $14,000\n" +
                    "\n" +
                    "Dress Code:\n" +
                    "- No dress code, very casual\n" +
                    "\n" +
                    "Benefits:\n" +
                    "- 401k\n" +
                    "- Health, Dental, and Life Insurance\n" +
                    "- Paid time off\n" +
                    "\n" +
                    "Work Environment:\n" +
                    "- Very loose and casual. Best suited for extroverted people.\n" +
                    "  Encourages openness and collaboration amongst other employees.",
            "Google \n" +
                    "\n" +
                    "Work Culture:\n" +
                    "- Their employees get the privilege to work in the latest technology\n" +
                    "  and are deeply involved in solving the world's huge problems with\n" +
                    "  the best possible use of technology.\n" +
                    "\n" +
                    "Headquarters Location:\n" +
                    "- Mountain View, California\n" +
                    "\n" +
                    "Number of Employees:\n" +
                    "- 135,501\n" +
                    "\n" +
                    "Annual revenue:\n" +
                    "- $182.53 billion\n" +
                    "\n" +
                    "Average salary:\n" +
                    "- $120,000\n" +
                    "\n" +
                    "Average bonus:\n" +
                    "- $16,000\n" +
                    "\n" +
                    "Dress Code:\n" +
                    "- No dress code, very casual\n" +
                    "\n" +
                    "Benefits:\n" +
                    "- 401k\n" +
                    "- Health, Dental, and Life Insurance\n" +
                    "- Paid time off\n" +
                    "\n" +
                    "Work Environment:\n" +
                    "- Laid back, not as competitive than other companies.\n" +
                    "  Promotes free work environment to keep employees happy\n" +
                    "  and promote creative thinking.",
            "LinkedIn\n" +
                    "\n" +
                    "Work Culture:\n" +
                    "- Focus on 5 main pillars – Transformation, Integrity, Collaboration, Humor, Results.\n" +
                    "\n" +
                    "Headquarters Location:\n" +
                    "- Sunnyvale, California\n" +
                    "\n" +
                    "Number of Employees:\n" +
                    "- 16,000\n" +
                    "\n" +
                    "Annual revenue:\n" +
                    "- $10.00 billion\n" +
                    "\n" +
                    "Average salary:\n" +
                    "- $123,000\n" +
                    "\n" +
                    "\n" +
                    "Average bonus:\n" +
                    "- $13,000\n" +
                    "\n" +
                    "Dress Code:\n" +
                    "- Business Casual\n" +
                    "\n" +
                    "Benefits:\n" +
                    "- 401k\n" +
                    "- Health, Dental, and Life Insurance\n" +
                    "- Paid time off\n" +
                    "\n" +
                    "Work Environment:\n" +
                    "- Very structured and hierarchical with various roles to fill.\n" +
                    "  Inclusive and focused on having growth mindset in the workplace.",
            "Microsoft\n" +
                    "\n" +
                    "Work Culture:\n" +
                    "- Microsoft employees are passionate about giving time,\n" +
                    "  money, and skills to address the issues facing our world.\n" +
                    "\n" +
                    "Headquarters Location:\n" +
                    "- Redmond, Washington\n" +
                    "\n" +
                    "Number of Employees:\n" +
                    "- 181,000\n" +
                    "\n" +
                    "Annual revenue:\n" +
                    "- $168.09 billion\n" +
                    "\n" +
                    "Average salary:\n" +
                    "$121,000\n" +
                    "\n" +
                    "Average bonus:\n" +
                    "$14,000\n" +
                    "\n" +
                    "Dress Code:\n" +
                    "- No dress code, very casual\n" +
                    "\n" +
                    "Benefits:\n" +
                    "- 401k\n" +
                    "- Health, Dental, and Life Insurance\n" +
                    "- Paid time off\n" +
                    "\n" +
                    "Work Environment:\n" +
                    "- Described by its employees as 'Comfortably fast.'\n" +
                    "  It has been know to demanding and typically requires a lot of hard work"
    };

    public CompanyListInfoFragment()
    {
        // required default constructor
    }

    // This method is similar to the Factory Method design pattern
    // to create new instances of this fragment.
    // There is a specific reason for having this method, though.  We want to send some data (VersionIndex, here) into the
    // new fragment.  Company List disallows overloaded constructors for fragments, and so we can't create a Fragment with
    // the companyIndex as argument.  But we can use the Bundle and send the data this way.  Also, the setArguments call
    // must happen BEFORE the fragment is attached an activity.
    public static CompanyListInfoFragment newInstance(int versionIndex ) {

        // this uses the default constructor (not defined in this class, but Java-supplied)
        CompanyListInfoFragment fragment = new CompanyListInfoFragment();

        // save the selected list companyIndex in the new fragment's Bundle data
        // the CompanyListInfoFragment needs to know the version to display
        Bundle args = new Bundle();
        args.putInt( "companyIndex", versionIndex );

        fragment.setArguments( args );

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {

        // Programmatically, create a scrollable TextView to show the company information
        ScrollView scroller = new ScrollView( getActivity()) ;
        TextView textView = new TextView( getActivity() );
        scroller.addView( textView );

        // Set the padding for the new TextView
        // These padding attributes are normally defined in the XML file
        // here, they are set programmatically.
        int padding = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 12, getActivity().getResources().getDisplayMetrics() );
        textView.setPadding( padding, padding, padding, padding );

        // set the text size
        textView.setTextSize( TypedValue.COMPLEX_UNIT_SP, 18f );

        // show the company info
        textView.setText( info[ getShownCompanyIndex() ] );

        return scroller;
    }

    public int getShownCompanyIndex() {
        return getArguments().getInt("companyIndex", 0 );
    }
}
