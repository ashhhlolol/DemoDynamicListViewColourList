package sg.edu.rp.c346.id22000765.demodynamiclistviewcolourlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etElement;
    EditText etIndexElement;
    Button btnAdd;
    ListView lvColour;
    Button btnRemove;
    Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> alColours = new ArrayList<String>();
        etElement = findViewById(R.id.editTextColour);
        btnAdd = findViewById(R.id.buttonAddItem);
        lvColour = findViewById(R.id.listViewColor);
        etIndexElement = findViewById(R.id.editTextIndex);
        btnRemove = findViewById(R.id.buttonRemoveColour);
        btnUpdate = findViewById(R.id.buttonUpdateColour);

        alColours.add("Red");
        alColours.add("Orange");


        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alColours);

        lvColour.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get the colour that user entered in the EditText
                String color = etElement.getText().toString();
                //Modify the data source – specifically, add the colour to the ArrayList
                alColours.add(color);
                //Call notifyDataSetChanged() of the adapter
                adapter.notifyDataSetChanged();
                etElement.setText("");


                int pos = Integer.parseInt(etIndexElement.getText().toString());
                alColours.add(pos, color);
                adapter.notifyDataSetChanged();
            }
        });
        lvColour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String colour = alColours.get(position);
                Toast.makeText(MainActivity.this, colour, Toast.LENGTH_SHORT).show();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String positionText = etIndexElement.getText().toString();
                if (!positionText.isEmpty()) {
                    int position = Integer.parseInt(positionText);
                    if (position >= 0 && position < alColours.size()) {
                        alColours.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item removed", Toast.LENGTH_SHORT).show();
                        etIndexElement.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a position", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String positionText = etIndexElement.getText().toString();
                if (!positionText.isEmpty()) {
                    int position = Integer.parseInt(positionText);
                    if (position >= 0 && position < alColours.size()) {
                        String updatedColour = etElement.getText().toString();
                        alColours.set(position,updatedColour);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item updated", Toast.LENGTH_SHORT).show();
                        etIndexElement.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid position", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a position", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
    }