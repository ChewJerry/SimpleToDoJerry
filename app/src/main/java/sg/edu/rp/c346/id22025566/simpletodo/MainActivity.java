package sg.edu.rp.c346.id22025566.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //declare variables
    EditText etToDo;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvToDo;

    ArrayList<String> toDoArray= new ArrayList<String>();
    Spinner spnOptions;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind variables to UI elements
        etToDo=findViewById(R.id.editTextToDo);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        btnDelete=findViewById(R.id.buttonDelete);
        lvToDo=findViewById(R.id.listViewToDo);
        spnOptions=findViewById(R.id.spinnerItems);

        // Create ArrayAdapter for Spinner using string array resource
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinnerItems,
                android.R.layout.simple_spinner_item
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOptions.setAdapter(spinnerAdapter);






        //create array adapter to link arraylist to listview. in the bracket only need to change 3rd parameter.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, toDoArray);

        //link the arrayadapter
        lvToDo.setAdapter(adapter);




        //button to add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get toDo user entered
                String toDo = etToDo.getText().toString();


                //Modify the data source – specifically, add the colour to the ArrayList
                //alColours.add(color);

                //int pos = Integer.parseInt(etIndexElement.getText().toString());
                toDoArray.add(toDo);




                //Call notifyDataSetChanged() of the adapter
                adapter.notifyDataSetChanged();

            }
        });

        //button to remove based on index
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve input from etToDo
                String toDo = etToDo.getText().toString();
                if (toDo.isEmpty()) {  // if it is empty
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }


               else { //if there is input
                   if (pos>=0 || pos<toDoArray.size()) { // if index exists in toDoArray
                       int pos = Integer.parseInt(etToDo.getText().toString());

                       toDoArray.remove(pos); //then remove
                       adapter.notifyDataSetChanged();
                   }
                   else { //if index is not in range
                       Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                   }
                }




            }
        });

        //button to clear
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get toDo user entered
                //String toDo = etToDo.getText().toString();


                //Modify the data source – specifically, add the colour to the ArrayList
                //alColours.add(color);

                //int pos = Integer.parseInt(etIndexElement.getText().toString());
                //toDoArray.add(toDo);

                //clear the entire listView
                toDoArray.clear();




                //Call notifyDataSetChanged() of the adapter
                adapter.notifyDataSetChanged();

            }
        });


        //handle selected item from spinner
        spnOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected item here
                switch (position) {
                    case 0: //add
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etToDo.setText("Type in a new task here");
                        break;
                    case 1: //remove
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etToDo.setText("Type in the index of the task to be removed");
                        break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case where nothing is selected (optional)
            }

        });
    }






}