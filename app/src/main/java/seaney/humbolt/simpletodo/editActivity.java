package seaney.humbolt.simpletodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static seaney.humbolt.simpletodo.MainActivity.ITEM_TEXT;
import static seaney.humbolt.simpletodo.MainActivity.ITEM_TEXT_POSITION;

public class editActivity extends AppCompatActivity {

    public int postion;
    EditText edit_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_Text = (EditText) findViewById(R.id.edit_Text);

        edit_Text.setText(getIntent().getStringExtra(ITEM_TEXT));

        postion = getIntent().getIntExtra(ITEM_TEXT_POSITION,0 );

        getSupportActionBar().setTitle("Edit task");

    }

    public void onSaveItem(View v)
    {
        Intent i = new Intent();

        i.putExtra(ITEM_TEXT, edit_Text.getText().toString());

        i.putExtra(ITEM_TEXT_POSITION, postion);

        setResult(RESULT_OK, i);

        finish();
    }

}
