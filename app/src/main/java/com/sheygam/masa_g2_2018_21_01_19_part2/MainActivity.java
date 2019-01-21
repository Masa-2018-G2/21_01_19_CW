package com.sheygam.masa_g2_2018_21_01_19_part2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MenuItem doneItem, editItem;
    private EditText inputText;
    private TextView resultTxt;
    private boolean isEdit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = findViewById(R.id.input_text);
        resultTxt = findViewById(R.id.result_txt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        doneItem = menu.findItem(R.id.done_item);
        editItem = menu.findItem(R.id.edit_item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings_item){
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.logout_item){
            finish();
        }else if(item.getItemId() == R.id.edit_item){
            isEdit = true;
            String str = resultTxt.getText().toString();
            inputText.setText(str);
            resultTxt.setVisibility(View.INVISIBLE);
            inputText.setVisibility(View.VISIBLE);

            editItem.setVisible(false);
            doneItem.setVisible(true);
        }else if(item.getItemId() == R.id.done_item){
            String str = inputText.getText().toString();
            if(str.trim().isEmpty()){
                showDialog();
            }else {
                isEdit = false;
                resultTxt.setText(str);
                inputText.setVisibility(View.INVISIBLE);
                resultTxt.setVisibility(View.VISIBLE);

                doneItem.setVisible(false);
                editItem.setVisible(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(isEdit){
            Toast.makeText(this, "Save data", Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
    }

    private void showDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Input can't be empty")
                .setPositiveButton("Ok",null)
                .setCancelable(false)
                .create();
        dialog.show();
    }
}
