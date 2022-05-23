package edu.uga.cs.jobstrackerrest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

// This is a DialogFragment to handle the addition of a JobLead.
public class AddJobLeadDialogFragment extends DialogFragment {

    private EditText companyNameView;
    private EditText phoneView;
    private EditText urlView;
    private EditText commentsView;

    // A callback listener interface to finish up the addition of a new JobLead.
    // It is implemented in the ReviewJobLeadsActivity.
    public interface AddJobLeadDialogListener {
        void onFinishAddJobLeadDialog(JobLead jobLead);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.add_job_lead_dialog,
                (ViewGroup) getActivity().findViewById(R.id.root));

        companyNameView = layout.findViewById( R.id.editText1 );
        phoneView = layout.findViewById( R.id.editText2 );
        urlView = layout.findViewById( R.id.editText3 );
        commentsView = layout.findViewById( R.id.editText4 );

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);

        // Set the title of the AlertDialog
        builder.setTitle( "New Job Lead" );
        builder.setNegativeButton( android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                // close the dialog
                dialog.dismiss();
            }
        });
        builder.setPositiveButton( "SAVE", new ButtonClickListener() );

        // Create the AlertDialog and show it
        return builder.create();
    }

    private class ButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            String companyName = companyNameView.getText().toString();
            String phone = phoneView.getText().toString();
            String url = urlView.getText().toString();
            String comments = commentsView.getText().toString();

            JobLead jobLead = new JobLead( companyName, phone, url, comments );

            // get the Activity's listener to add the new job lead
            AddJobLeadDialogListener listener = (AddJobLeadDialogListener) getActivity();

            // add the new job lead
            listener.onFinishAddJobLeadDialog( jobLead );

            // close the dialog
            dismiss();
        }
    }
}
