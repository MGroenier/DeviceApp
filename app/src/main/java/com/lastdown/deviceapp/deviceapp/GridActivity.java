package com.lastdown.deviceapp.deviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter adapter;
    private List<GridItem> items;

    private EditText editText;
    private Spinner spinner;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        gridView = (GridView) findViewById(R.id.gridView);
        items = new ArrayList<GridItem>();
        adapter = new GridAdapter(this, R.layout.single_grid_item, items);
        gridView.setAdapter(adapter);

        items.add(new GridItem("laptop", R.drawable.laptop));
        items.add(new GridItem("phone", R.drawable.phone));
        adapter.notifyDataSetChanged();

        editText = (EditText) findViewById(R.id.editText);
        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);

        //Create the spinner items
        String[] spinnerItems = {"laptop", "phone"};

        //Create the spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerItems);

        //Set the adapter for the spinner
        spinner.setAdapter(spinnerAdapter);

        //Handle the button click

        button.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                //Check if the title and descriptions have text
                if (!TextUtils.isEmpty(editText.getText())) {

                    //Get the resource value for the selected icon from the spinner
                    int imageResource = 0;

                    switch (spinner.getSelectedItemPosition()) {
                        case 0:
                            imageResource = R.drawable.laptop;
                            break;
                        case 1:
                            imageResource = R.drawable.phone;
                            break;
                        default:
                            imageResource = R.mipmap.ic_launcher;
                            break;
                    }

                    items.add(new GridItem(editText.getText().toString(),imageResource));
                    adapter.notifyDataSetChanged();

                }
            }
        });
    }
}
