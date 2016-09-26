package com.netsecl.nodesup;

import java.util.ArrayList;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.MXRecord;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MxRecords extends Activity implements OnClickListener {
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	ArrayList<String> mxResultList = null;
	/**
	 * Define the Host DNS pattern.
	 */
	private static final String DNS_PATTERN = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mxrec);
		Button checkmx = (Button) findViewById(R.id.checkmxbut);
		checkmx.setOnClickListener(this);
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mxList);
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow);
		// Set the ArrayAdapter as the ListView's adapter.
		mainListView.setAdapter(listAdapter);
	}

	/**
	 * A function that returns the MX records.
	 * 
	 * @return
	 */
	void getMXRecords(String dnshost) {
		Record[] records = null;
		{

			try {

				records = new Lookup(dnshost, Type.MX).run();

			} catch (TextParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < records.length; i++) {
				MXRecord mx = (MXRecord) records[i];
				listAdapter.add("Host " + mx.getTarget() + " has preference  "
						+ mx.getPriority());
			}
		}

	}

	/**
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.checkmxbut:
			Toast toast = Toast.makeText(this, "The DNS Record is not valid.", 3);
			EditText mxinput;
			mxinput = (EditText) findViewById(R.id.mxinput);
			boolean valid_mx = false;
			valid_mx = MainActivity.validate(mxinput.getText()
						.toString(), DNS_PATTERN);
			
			if (valid_mx == true) {
				listAdapter.clear();
				getMXRecords(mxinput.getText().toString());
				listAdapter.notifyDataSetChanged();
			} else {
				toast.show();
			}
			break;

		}

	}

}
