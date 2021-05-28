package sg.edu.rp.c346.id20040654.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDBS;
    Button btnOCBC;
    Button btnUOB;
    View selectedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDBS = findViewById(R.id.buttonDBS);
        btnOCBC = findViewById(R.id.buttonOCBC);
        btnUOB = findViewById(R.id.buttonUOB);

        registerForContextMenu(btnDBS);
        registerForContextMenu(btnOCBC);
        registerForContextMenu(btnUOB);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.english) {
            btnDBS.setText("DBS");
            btnOCBC.setText("OCBC");
            btnUOB.setText("UOB");
            return true;
        } else if (id == R.id.chinese) {
            btnDBS.setText("星展銀行");
            btnOCBC.setText("華僑銀行");
            btnUOB.setText("大华银行");
            return true;
        } else {
            Log.d("translate", "Error with translate option menu");
            Toast.makeText(MainActivity.this, "Error found", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        Log.v("context", "Context Menu has been created");

        getMenuInflater().inflate(R.menu.menu_context, menu);

        if(v == btnDBS) {
            selectedView = btnDBS;
        } else if (v == btnOCBC) {
            selectedView = btnOCBC;
        } else if (v == btnUOB) {
            selectedView = btnUOB;
        }

    }

    public boolean onContextItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(selectedView == btnDBS) {
            contextChoice(btnDBS, id, "https://www.dbs.com.sg/index/default.page", "tel:18001111111");
        } else if (selectedView == btnOCBC) {
            contextChoice(btnOCBC, id, "https://www.ocbc.com/group/gateway", "tel:18003633333");
        } else if (selectedView == btnUOB) {
            contextChoice(btnUOB, id, "https://www.uobgroup.com/uobgroup/default.page", "tel:18002222121");
        }

        return super.onContextItemSelected(item); //pass menu item to the superclass implementation
    }

    public boolean contextChoice(Button btn, int id, String website, String phone) {
        boolean done = false;
        if(id == R.id.website) {
            Intent intentWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
            startActivity(intentWebsite);
            done = true;
        } else if (id == R.id.contact) {
            Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
            startActivity(intentDial);
            done = true;
        } else if (id == R.id.favourite) {
            btnDBS.setTextColor(Color.parseColor("#FFFFFF"));
            btnOCBC.setTextColor(Color.parseColor("#FFFFFF"));
            btnUOB.setTextColor(Color.parseColor("#FFFFFF"));
            btn.setTextColor(Color.parseColor("#FFFF00"));
        } else {
            Log.d("context","Error with context menu");
            Toast.makeText(MainActivity.this, "Error with Context Menu", Toast.LENGTH_SHORT).show();
        }
        return done;
    }
}