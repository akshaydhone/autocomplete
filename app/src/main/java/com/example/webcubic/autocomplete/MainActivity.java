package com.example.webcubic.autocomplete;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

    /*
     * Change to type CustomAutoCompleteView instead of AutoCompleteTextView
     * since we are extending to customize the view and disable filter
     * The same with the XML view, type will be CustomAutoCompleteView
     */
    CustomAutoCompleteView myAutoComplete,myAutoComplete1;

    // adapter for auto-complete
    ArrayAdapter<String> myAdapter;

    // for database operations
    DatabaseHandler databaseH;

    // just to add some initial value
    String[] item = new String[] {"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            // instantiate database handler
            databaseH = new DatabaseHandler(MainActivity.this);

            // put sample data to database
            insertSampleData();

            // autocompletetextview is in activity_main.xml
            myAutoComplete = (CustomAutoCompleteView) findViewById(R.id.myautocomplete);
            myAutoComplete1 = (CustomAutoCompleteView) findViewById(R.id.myautocomplete1);

            // add the listener so it will tries to suggest while the user types
            myAutoComplete.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            myAutoComplete1.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));
            // set our adapter
            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
            myAutoComplete.setAdapter(myAdapter);
            myAutoComplete1.setAdapter(myAdapter);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSampleData(){

        // CREATE
        databaseH.create( new MyObject("Ajay sharma") );
        databaseH.create( new MyObject("Atul patil") );
        databaseH.create( new MyObject("Bhavesh singh") );
        databaseH.create( new MyObject("Kalpesh tiwari") );
        databaseH.create( new MyObject("Manoj Mishra") );
        databaseH.create( new MyObject("Siddhant jaiswal") );
        databaseH.create( new MyObject("Rajesh suhana") );
        databaseH.create( new MyObject("Vivek patel") );
        databaseH.create( new MyObject("Mayank mathur") );
        databaseH.create( new MyObject("Sagar kamble") );
        databaseH.create( new MyObject("Siddhesh Ghadge") );
        databaseH.create( new MyObject("Rahul Patel") );
        databaseH.create( new MyObject("Amit modi") );
        databaseH.create( new MyObject("Akash singh") );
        databaseH.create( new MyObject("Chandrakant kamble") );
        databaseH.create( new MyObject("Mahesh Dsouza") );
        databaseH.create( new MyObject("Anand Tupe") );
        databaseH.create( new MyObject("Amey Bhuvad") );
        databaseH.create( new MyObject("Akshay Dhone") );
        databaseH.create( new MyObject("Kishan more") );
        databaseH.create( new MyObject("Ganesh Shetty") );
        databaseH.create( new MyObject("Mahendra Patil") );



    }

    // this function is used in CustomAutoCompleteTextChangedListener.java
    public String[] getItemsFromDb(String searchTerm){

        // add items on the array dynamically
        List<MyObject> products = databaseH.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }

}