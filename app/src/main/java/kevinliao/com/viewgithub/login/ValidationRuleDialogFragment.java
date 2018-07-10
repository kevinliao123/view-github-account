package kevinliao.com.viewgithub.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import kevinliao.com.viewgithub.R;

public class ValidationRuleDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.validation_rule_title)
                .setMessage(R.string.validation_rule)
               .setPositiveButton(R.string.ok, null);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}