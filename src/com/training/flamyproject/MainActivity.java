package com.training.flamyproject;

import com.training.alert.PostToTwitterDialog;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Fragment{
	EditText etyourname, ethishername;
	Button  btncalculate, btnPost, btnClear;
	ImageView imgflame,imgcandle ,imghope;
	LinearLayout ly;
	TextView txtflame, txtcandle, txthope;
	View v ;
	int total=0;
	
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.activity_main, container, false);
		etyourname= (EditText)v.findViewById(R.id.yourname);
		ethishername= (EditText)v.findViewById(R.id.herhisname);

		txtflame = (TextView) v.findViewById(R.id.txtflameresult);
		txtcandle = (TextView) v.findViewById(R.id.txtcandleresult);
		txthope = (TextView) v.findViewById(R.id.txthoperesult);
		
		imgflame= (ImageView)v.findViewById(R.id.imgflame);
		imghope= (ImageView)v.findViewById(R.id.imghope);
		imgcandle= (ImageView)v.findViewById(R.id.imgcandle);
		
		ly= (LinearLayout)v.findViewById(R.id.lyedittext);
		ly.getBackground().setAlpha(128);
		
		btncalculate = (Button) v.findViewById(R.id.btncalculate);
		btncalculate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (etyourname.getText().toString()!="" && etyourname.getText().toString()!= null 
						&& ethishername.getText().toString()!="" && ethishername.getText().toString()!=null ) {
					int your = convertArray(etyourname, ethishername);
					int hisher = convertArray(ethishername, etyourname);
					total=your + hisher;
					new CountDownTimer(5000, 1000) {
						@SuppressWarnings("deprecation")
						public void onTick(long millisUntilFinished) {
							imgflame.setImageDrawable(getResources().getDrawable(R.drawable.animation));
							imghope.setImageDrawable(getResources().getDrawable(R.drawable.hope));
							imgcandle.setImageDrawable(getResources().getDrawable(R.drawable.candle));
						}
	
						@SuppressWarnings("deprecation")
						public void onFinish() {
							String flames = flames(total);						
							String candle = candle(total);	
							String hope =  hope(total);

							if (flames.equals("Friends")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.friends));
							} else if (flames.equals("Lover")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.lover));
							} else if (flames.equals("Accept")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.accept));
							} else if (flames.equals("Marriage")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.married));
							} else if (flames.equals("Engaged")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.engaged));
							} else if (flames.equals("Sweet")) {
								imgflame .setImageDrawable(getResources().getDrawable(R.drawable.sweet));
							} 
							txtflame.setText(flames);
							if (candle.equals("Crush")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.crush));
							} else if (candle.equals("Accept")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.accept));
							} else if (candle.equals("Never")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.never));
							} else if (candle.equals("Darling")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.darling));
							} else if (candle.equals("Lonely")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.lonely));
							} else if (candle.equals("Enemy")) {
								imgcandle .setImageDrawable(getResources().getDrawable(R.drawable.enemy));
							} 
							txtcandle.setText(candle);
							
							if (hope.equals("Hindi")) {
								imghope .setImageDrawable(getResources().getDrawable(R.drawable.hinde));
							} else if (hope.equals("Oo")) {
								imghope .setImageDrawable(getResources().getDrawable(R.drawable.oo));
							} else if (hope.equals("Pwede")) {
								imghope .setImageDrawable(getResources().getDrawable(R.drawable.pwd));
							} else if (hope.equals("Ewan")) {
								imghope .setImageDrawable(getResources().getDrawable(R.drawable.ewan));
							}
							txthope.setText(hope);
							
						}
					}.start();
				} else {
					Toast.makeText(getActivity(), "Please fill up the textboxes", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btnPost = (Button) v.findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				postDialog(null, etyourname.getText().toString() + " + " 
						+ ethishername.getText().toString() + " = #" 
						+ flames(total) + " #" + candle(total) + " #"
						+ hope(total));
			}
		});
		
		btnClear = (Button) v.findViewById(R.id.btnclear);
		btnClear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				etyourname.setText("");
				ethishername.setText("");
			}
		});
	
		return v;
	}
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		setHasOptionsMenu (true);
		super.onCreate(savedInstanceState);

	}

	public int convertArray(EditText yourname, EditText hisher) {
		int number=0;
		char[] yournamearray = yourname.getText().toString().toCharArray();
		for(int i=0;i<yournamearray.length;i++) {
			Log.i("trial", "Data at [" + i + "]=" + yournamearray[i]);
			if (yournamearray[i] != ' ') {
				if (ifExist(yournamearray[i], hisher) == true) {
					number++;
				}
			}
		}
		return number;
	}
	public boolean ifExist(char n, EditText name) {
		char[] arrayname = name.getText().toString().toCharArray();
		for(int i=0;i<arrayname.length;i++) {
			if (arrayname[i]== n) {
				return true;
			}
		}
		return false;
	}
	public String flames(int count) {
		if (count%6 == 1 || count == 1) {
			return "Friends";
		} else if(count%6 == 2 || count == 2) {
			return "Lover";
		} else if(count%6 == 3 || count == 3) {
			return "Accept";
		} else if(count%6 == 4 || count == 4) {
			return "Marriage";
		} else if(count%6 == 5 || count == 5) {
			return "Engaged";
		} else if(count%6 == 0 || count == 6) {
			return "Sweet";
		} else {
			return "";
		}
	}
	public String hope(int count) {
		if (count%4 == 1 || count == 1) {
			return "Hindi";
		} else if(count%4 == 2 || count == 2) {
			return "Oo";
		} else if(count%4 == 3 || count == 3) {
			return "Pwede";
		} else if(count%4 == 0 || count == 4) {
			return "Ewan";
		} else {
			return "dfsdf";
		}
	}
	public String candle(int count) {
		if (count%6 == 1 || count == 1) {
			return "Crush";
		} else if(count%6 == 2 || count == 2) {
			return "Accept";
		} else if(count%6 == 3 || count == 3) {
			return "Never";
		} else if(count%6 == 4 || count == 4) {
			return "Darling";
		} else if(count%6 == 5 || count == 5) {
			return "Lonely";
		} else if(count%6 == 0 || count == 6) {
			return "Enemy";
		} else {
			return "";
		}
	}
	 public void postDialog(View view, String post){
	    	PostToTwitterDialog postdialog = new PostToTwitterDialog(post);
	    	postdialog.show(getFragmentManager(), "postdialog");
	    }

}
