package com.example.testapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.action_copy) {
                            Log.v("TAG", "Action copy");
                        } else if (id == R.id.action_cut) {
                            Log.v("TAG", "Action cut");
                        } else if (id == R.id.action_paste) {
                            Log.v("TAG", "Action paste");
                        }
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {

                    }
                });
            }
        });

        buttonPopup = findViewById(R.id.button_popup);
        buttonPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, buttonPopup);
                popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        if (id == R.id.action_copy) {
                            Log.v("TAG", "Action download");
                        } else if (id == R.id.action_cut) {
                            Log.v("TAG", "Action share");
                        } else if (id == R.id.action_paste) {
                            Log.v("TAG", "Action settings");
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        findViewById(R.id.button_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                View customView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
//                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
//                        .setView(customView)
//                        .create();
//                dialog.setCanceledOnTouchOutside(false);
//
//                EditText editUser = customView.findViewById(R.id.edit_username);
//                EditText editPass = customView.findViewById(R.id.edit_password);
//
//                customView.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                customView.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String username = editUser.getText().toString();
//                        String password = editPass.getText().toString();
//
//
//
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();

                boolean[] checked = new boolean[3];
                checked[0] = true;

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Hiển thị danh sách")
                        .setMultiChoiceItems(R.array.items, checked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                            }
                        })
                        .setPositiveButton("OK", null)
                        .create().show();
            }
        });

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            items.add("Item " + i);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        Log.v("TAG", "Position: " + menuInfo.position);

        int id = item.getItemId();
        if (id == R.id.action_copy) {
            Log.v("TAG", "Action download");
        } else if (id == R.id.action_cut) {
            Log.v("TAG", "Action share");
        } else if (id == R.id.action_paste) {
            Log.v("TAG", "Action settings");
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_copy) {
            Log.v("TAG", "Action download");
        } else if (id == R.id.action_cut) {
            Log.v("TAG", "Action share");
        } else if (id == R.id.action_paste) {
            Log.v("TAG", "Action settings");
        }

        return true;
    }
}