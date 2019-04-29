package com.training.alert;

import com.training.flamyproject.R;
import com.training.twittertask.TweetTask;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostToTwitterDialog extends DialogFragment {
	String post;
	EditText editpost;
	public PostToTwitterDialog(String post) {
		this.post=post;
	}
	@Override
	@NonNull
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view=inflater.inflate(R.layout.post_dialog, null);
		editpost = (EditText) view.findViewById(R.id.editpost);
		editpost.setText(post);
		builder.setView(view);
		builder.setPositiveButton("Post", null);
		builder.setNegativeButton("Cancel", null);
		
		Dialog dialog = builder.create();
		dialog.setOnShowListener(new DialogInterface.OnShowListener() { 
			@Override
			public void onShow(final DialogInterface dlg) {
				final Button b = ((AlertDialog) dlg).getButton(AlertDialog.BUTTON_POSITIVE);
				final Button n = ((AlertDialog) dlg).getButton(AlertDialog.BUTTON_NEGATIVE);
				b.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String[] s = {editpost.getText().toString()};
						new TweetTask().execute(s);
						b.setText("posting ...");
						b.setEnabled(false);
						n.setEnabled(false);
						Toast.makeText(getActivity(), "Has been successfully posted.", Toast.LENGTH_SHORT).show();
						b.setEnabled(true);
						n.setEnabled(true);
						b.setText("Post");
						editpost.setText("");
						dlg.dismiss();
					}
				});
			}
		});
		setCancelable(false);
		return dialog;
	}
	
}
