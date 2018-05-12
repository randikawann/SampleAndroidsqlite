package com.example.randikawann.androidconnectsqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText userInput;
    TextView userText;
    MyDBHandler myDBHandler;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInput = (EditText) findViewById(R.id.user_input);
        userText = (TextView) findViewById(R.id.user_text);
        myDBHandler=new MyDBHandler(this,null,null,1);
        printDatabase();
        Log.i(TAG, "start application");
    }

    public void addButtonClicked(View view){
        Products products=new Products(userInput.getText().toString());
        myDBHandler.addProduct(products);
        printDatabase();
        Log.i(TAG, "add button clicked");
    }

    public void deleteButtonClicked(View view){
        String inputText=userInput.getText().toString();
        myDBHandler.deleteproduct(inputText);
        printDatabase();
        Log.i(TAG, "delete button clicked");
    }
    private void printDatabase() {
        String dbString =myDBHandler.databaseToString();
        userText.setText(dbString);
        userInput.setText("");
        Log.i(TAG, "print database");
    }
    @Override
    protected void onDestroy() {
        myDBHandler.close();
        super.onDestroy();
    }
}
