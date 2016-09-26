package com.netsecl.nodesup;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener {

	/**
	 * Store the IPs to the preferences.
	 */
	public static final String PREFS_IP = "StoreIP";

	/**
	 * Initialize all edit text fields.
	 */
	EditText row01, row02, row03, row04, row05, service, port;
	/**
	 * Initialize all toggle buttons.
	 */
	ToggleButton btn01, btn02, btn03, btn04, btn05;

	/**
	 * Set all the settings.
	 */
	private void setSettings() {
		SharedPreferences settings = getSharedPreferences(PREFS_IP, 0);
		row01.setText(settings.getString("row01", "127.0.0.1"));
		row02.setText(settings.getString("row02", "127.0.0.1"));
		row03.setText(settings.getString("row03", "127.0.0.1"));
		row04.setText(settings.getString("row04", "127.0.0.1"));
		row05.setText(settings.getString("row05", "127.0.0.1"));
		service.setText(settings.getString("service", "127.0.0.1"));
		port.setText(settings.getString("port", "25"));
	}

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		row01 = (EditText) findViewById(R.id.row1);
		btn01 = (ToggleButton) findViewById(R.id.row01_btn);
		row02 = (EditText) findViewById(R.id.row2);
		btn02 = (ToggleButton) findViewById(R.id.row02_btn);
		row03 = (EditText) findViewById(R.id.row3);
		btn03 = (ToggleButton) findViewById(R.id.row03_btn);
		row04 = (EditText) findViewById(R.id.row4);
		btn04 = (ToggleButton) findViewById(R.id.row04_btn);
		row05 = (EditText) findViewById(R.id.row5);
		btn05 = (ToggleButton) findViewById(R.id.row05_btn);
		service = (EditText) findViewById(R.id.service);
		port = (EditText) findViewById(R.id.port);
		/**
		 * 
		 */
		setSettings();
		/**
		 * Find all the buttons and set their listeners.
		 */
		Button refresh = (Button) findViewById(R.id.button1);
		refresh.setOnClickListener(this);
		Button wifibtn = (Button) findViewById(R.id.wifibtn);
		wifibtn.setOnClickListener(this);
		Button exit = (Button) findViewById(R.id.Exit);
		exit.setOnClickListener(this);
//		AdView adview = (AdView)findViewById(R.id.adView);
//		AdRequest re = new AdRequest();
////		re.setTesting(true);
//		adview.loadAd(re);
	}

	/**
	 * Define the IP pattern.
	 */
	private static final String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	/**
	 * Define the Host DNS pattern.
	 */
	private static final String DNS_PATTERN = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

	/**
	 * Function to validate the IPs.
	 */
	public static boolean validate(final String ip, String regpattern) {
		Pattern pattern = Pattern.compile(regpattern);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * Turn Wifi On.
	 */
	public void wifion(){
		/**
		 * Enable Wifi.
		 */
		WifiManager wifi = (WifiManager) getSystemService(MainActivity.WIFI_SERVICE);
		wifi.setWifiEnabled(true);
	}
	
	
	/**
	 * Validate all IPs.
	 */
	public void validateips() {
		Integer valid = 0;
		String toast01 = "first row";
		String separator01 = ",";
		boolean valid01_ip = validate(row01.getText().toString(), IP_PATTERN);
		boolean valid01_host = validate(row01.getText().toString(), DNS_PATTERN);
		if (valid01_ip == true | valid01_host == true) {
			btn01.setChecked(checkip(row01.getText().toString()));
			btn01.setClickable(false);
			toast01 = "";
			separator01 = "";
			valid++;
		}

		String toast02 = "second row";
		String separator02 = ",";
		boolean valid02_ip = validate(row02.getText().toString(), IP_PATTERN);
		boolean valid02_host = validate(row02.getText().toString(), DNS_PATTERN);
		if (valid02_ip == true | valid02_host == true) {
			btn02.setChecked(checkip(row02.getText().toString()));
			btn02.setClickable(false);
			toast02 = "";
			separator02 = "";
			valid++;
		}

		String toast03 = "third row";
		String separator03 = ",";
		boolean valid03_ip = validate(row03.getText().toString(), IP_PATTERN);
		boolean valid03_host = validate(row03.getText().toString(), DNS_PATTERN);
		if (valid03_ip == true | valid03_host == true) {
			btn03.setChecked(checkip(row03.getText().toString()));
			btn03.setClickable(false);
			toast03 = "";
			separator03 = "";
			valid++;
		}

		String toast04 = "4th row";
		String separator04 = ",";
		boolean valid04_ip = validate(row04.getText().toString(), IP_PATTERN);
		boolean valid04_host = validate(row04.getText().toString(), DNS_PATTERN);
		if (valid04_ip == true | valid04_host == true) {
			btn04.setChecked(checkip(row04.getText().toString()));
			btn04.setClickable(false);
			toast04 = "";
			separator04 = "";
			valid++;
		}

		String toast05 = "5th row";
		String separator05 = ",";
		boolean valid05_ip = validate(row05.getText().toString(), IP_PATTERN);
		boolean valid05_host = validate(row05.getText().toString(), DNS_PATTERN);
		if (valid05_ip == true | valid05_host == true) {
			btn05.setChecked(checkip(row05.getText().toString()));
			btn05.setClickable(false);
			toast05 = "";
			separator05 = "";
			valid++;
		}

		String toast06 = "6th row";
		boolean valid06_ip = validate(service.getText().toString(), IP_PATTERN);
		boolean valid06_host = validate(service.getText().toString(),
				DNS_PATTERN);
		if ((valid06_ip == true | valid06_host == true)
				&& (!(port.getText().toString().equals(""))==true)
				&& (service.getText().toString() != "")) {
			toast06 = "";
			valid++;
			int portint = Integer.parseInt(port.getText().toString());
			if (checkservice(service.getText().toString(), portint) == false) {
				Toast service_toast = Toast.makeText(this,
						"Service is Offline", 3);
				service_toast.show();
			} else {
				Toast service_toast = Toast.makeText(this, "Service is Online",
						3);
				service_toast.show();
			}
		}

		Toast toast = Toast.makeText(this, "The fields are not valid on: "
				+ toast01 + separator01 + toast02 + separator02 + toast03
				+ separator03 + toast04 + separator04 + toast05 + separator05
				+ toast06, 3);
		if (valid01_ip == false | valid02_ip == false | valid03_ip == false
				| valid04_ip == false | valid05_ip == false
				| valid06_ip == false | valid01_host == false
				| valid02_host == false | valid03_host == false
				| valid04_host == false | valid05_host == false
				| valid06_host == false)
		if (valid<6) toast.show();

	}

	/**
	 * Check if the IPs are online.
	 */
	boolean checkip(String host) {
		boolean online = false;
		InetAddress in;
		in = null;
		try {
			in = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (in != null) {
				if (in.isReachable(1500)) {
					online = true;
				} else {
					online = false;
				}
			} else
				online = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return online;
	}

	/**
	 * Check if the service is up and running on the specified port.
	 */
	public boolean checkservice(String server, int port) {
		Socket s = null;
		try {
			s = new Socket();
			s.setReuseAddress(true);
			SocketAddress sa = new InetSocketAddress(server, port);
			s.connect(sa, 1500);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param view
	 */
	public void onClick(View view) {
		SharedPreferences settings = getSharedPreferences(PREFS_IP, 0);
		SharedPreferences.Editor editor = settings.edit();
		switch (view.getId()) {

		case R.id.button1:
			validateips();
			break;

		case R.id.wifibtn:
			wifion();
			break;		
			
		case R.id.Exit:
			finish();
			break;

		}

		editor.putString("row01", row01.getText().toString());
		editor.putString("row02", row02.getText().toString());
		editor.putString("row03", row03.getText().toString());
		editor.putString("row04", row04.getText().toString());
		editor.putString("row05", row05.getText().toString());
		editor.putString("service", service.getText().toString());
		editor.putString("port", port.getText().toString());
		editor.commit();
	}

}