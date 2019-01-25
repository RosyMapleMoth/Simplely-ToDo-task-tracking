package seaney.humbolt.simpletodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> ToDoItems;
    ArrayAdapter<String> ToDoAddaptor;
    ListView LVitems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
        ToDoAddaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ToDoItems);
        LVitems = (ListView) findViewById(R.id.todoLVview);
        LVitems.setAdapter(ToDoAddaptor);


        setUpListviewListener();
    }


    public void addToDo(View LV)
    {
        EditText userInput = (EditText) findViewById(R.id.etNew);
        String itemText = userInput.getText().toString();
        ToDoItems.add((itemText));
        ToDoAddaptor.notifyDataSetChanged();
        userInput.setText("");
        writeItems();
        Toast.makeText(getApplicationContext(), "Item added to list", Toast.LENGTH_SHORT).show();

    }


    private void setUpListviewListener()
    {
        Log.i("MainActivity", "setting up listener for list view");
        LVitems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("MainActivity", "Item REmoved from list");
                ToDoItems.remove(position);
                ToDoAddaptor.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    private File getDataFile()
    {
        return new File(getFilesDir(), "todo.txt");

    }

    private void readItems()
    {
        try {
            ToDoItems = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch(IOException e) {
            Log.e("MainActvity", "Error readign file", e);
            ToDoItems = new ArrayList<>();
        }
    }

    private void writeItems()
    {
        try {
            FileUtils.writeLines(getDataFile(),ToDoItems);
        } catch(IOException e) {
            Log.e("MainActvity", "Error readign file", e);
            ToDoItems = new ArrayList<>();
        }
    }

}
