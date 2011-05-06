package com.juddsolutions.stalker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class FollowingActivity extends ListActivity {
    private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        List<String> tempList = Arrays.asList(getResources().getStringArray(R.array.twitter_handles));
        List<String> twitterHandles = new ArrayList<String>(tempList);
        
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, twitterHandles) {
        	@Override
        	public View getView(int position, View convertView, ViewGroup parent) {
        		TextView textView;
         
        		if (null == convertView) {
        			textView = (TextView) inflater.inflate(R.layout.list_item, null);
        		} else {
        			textView = (TextView) convertView;
        		}
         
        		textView.setText(getItem(position));
         
        		return textView;
        	}
        };
		setListAdapter(adapter);
        
        getListView().setTextFilterEnabled(true);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           		Intent intent = new Intent(FollowingActivity.this, TwitterActivity.class);
           		intent.putExtra("twitterUser", ((TextView)view).getText());
           		startActivity(intent);
           	}
           });

    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.following, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);
		alert.setView(input);
		input.setWidth(200);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString().trim();
				adapter.add(value);
			}
		});
		
		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
					}
				});
		alert.show();

		return super.onOptionsItemSelected(item);
	}	

}