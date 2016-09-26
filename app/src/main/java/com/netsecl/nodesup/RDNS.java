package com.netsecl.nodesup;

import java.io.IOException;
import java.util.ArrayList;
import org.xbill.DNS.DClass;
import org.xbill.DNS.ExtendedResolver;
import org.xbill.DNS.Message;
import org.xbill.DNS.Record;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.ReverseMap;
import org.xbill.DNS.Section;
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

public class RDNS extends Activity implements OnClickListener {
	private ListView mainListView;
	private ArrayAdapter<String> listAdapter;
	ArrayList<String> rdnsResultList = null;
	/**
	 * Define the Host DNS pattern.
	 */
	private static final String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rdns);
		Button checkrdns = (Button) findViewById(R.id.checkrdnsbut);
		checkrdns.setOnClickListener(this);
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.rdnsList);
		listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow);
		// Set the ArrayAdapter as the ListView's adapter.
		mainListView.setAdapter(listAdapter);
	}

	/**
	 * A function that returns the RDNS test results.
	 */
	 private void reverseDns(String hostIp) throws IOException {
         Resolver res = new ExtendedResolver();

         org.xbill.DNS.Name name = ReverseMap.fromAddress(hostIp);
         int type = Type.PTR;
         int dclass = DClass.IN;
         Record rec = Record.newRecord(name, type, dclass);
         Message query = Message.newQuery(rec);
         Message response = res.send(query);

         Record[] answers = response.getSectionArray(Section.ANSWER);
         if (answers.length == 0)
        	 listAdapter.add(hostIp);
         else
         listAdapter.add(answers[0].rdataToString());
   }
	/**
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		switch (view.getId()) {

		case R.id.checkrdnsbut:
			Toast toast = Toast.makeText(this, "Provide a valid IP address.", 3);
			EditText rdnsinput;
			rdnsinput = (EditText) findViewById(R.id.rdnsinput);
			boolean valid_rdns = MainActivity.validate(rdnsinput.getText()
					.toString(), IP_PATTERN);
			if (valid_rdns == true) {
				listAdapter.clear();
				try {
					reverseDns(rdnsinput.getText().toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				listAdapter.notifyDataSetChanged();
			} else {
				toast.show();
			}
			break;

		}

	}

}
