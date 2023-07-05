package sg.edu.rp.c346.id22018526.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;

    EditText tinput;
    EditText dinput;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        list = findViewById(R.id.taskView);
        tinput = findViewById(R.id.taskInput);
        dinput = findViewById(R.id.dateInput);

        ArrayList taskList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,taskList);
        list.setAdapter(adapter);

        tinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtask = tinput.getText().toString();


            }
        });
        dinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newdate = dinput.getText().toString();


            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                String newtask = tinput.getText().toString();
                String newdate = dinput.getText().toString();
                db.insertTask(newtask, newdate);

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                ArrayList<Task> tasks = db.getTasks();
                db.close();

                String tasktxt = "";
                taskList.clear();
                for (Task task : tasks) {
                    taskList.add(task.toString());
                }
                adapter.notifyDataSetChanged();




            }

        });

    }

}
