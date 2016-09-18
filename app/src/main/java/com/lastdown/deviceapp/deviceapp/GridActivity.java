package com.lastdown.deviceapp.deviceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    private GridItem clickedItem;

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

        registerForContextMenu(gridView);

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { @Override

        public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {

            return onLongListItemClick(v,pos,id); }

        });

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

    protected boolean onLongListItemClick(View v, int pos, long id) {

        clickedItem = (GridItem) adapter.getItem(pos); //Retrieve the (custom) item that the user clicked
        return false; //'False' means that Android will call other routines in the long click handling flow
        //in this case onCreateContextMenu

    }

    @Override

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        //Inflate the context menu from the resource file
        getMenuInflater().inflate(R.menu.context_menu, menu);
        //Find the delete MenuItem by its ID
        MenuItem deleteButton = menu.findItem(R.id.context_menu_delete_item);
        //Get the title from the menu button
        String originalTitle = deleteButton.getTitle().toString();
        //Make a new title combining the original title and the name of the clicked list item
        deleteButton.setTitle(originalTitle + " '" + clickedItem.getTitle() + "'?"); //NEW
        //Let Android do its magic
        super.onCreateContextMenu(menu, view, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //Retrieve info about the long pressed list item
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_menu_delete_item) {
            //Remove the item from the list
            items.remove(itemInfo.position);
            //Update the adapter to reflect the list change
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);

    }

}
