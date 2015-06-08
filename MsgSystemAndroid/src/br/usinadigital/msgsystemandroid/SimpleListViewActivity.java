package br.usinadigital.msgsystemandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.usinadigital.msgsystemandroid.util.Constants;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SimpleListViewActivity extends Activity {

	private ListView mainListView;
	private SimpleAdapter sa;
	ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		String[][] StatesAndCapitals = { { "aaa", "aaaaaa" }, { "bbb", "bbbbbb" } };
		HashMap<String, String> item;
		for (int i = 0; i < StatesAndCapitals.length; i++) {
			item = new HashMap<String, String>();
			item.put("line1", StatesAndCapitals[i][0]);
			item.put("line2", StatesAndCapitals[i][1]);
			list.add(item);
		}
		
		// Find the ListView resource.
		mainListView = (ListView) findViewById(R.id.mainListView);
		
		sa = new SimpleAdapter(this, list, android.R.layout.two_line_list_item, new String[] { "line1","line2" }, new int[] { android.R.id.text1, android.R.id.text2 });
		mainListView.setAdapter(sa);
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int itemPosition = position;
				Map itemValue = (Map) mainListView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
			}

		});
		
		
//		sa.notifyDataSetChanged();
	}

}
