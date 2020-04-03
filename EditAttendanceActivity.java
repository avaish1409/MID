package com.example.anirudh.mid.mid1707;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.anirudh.mid.mid1707.ui.login.LoginActivity;

public class EditAttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_attendance);

    }

    public void changeAtt(View view) {
        EditText e1= (EditText)findViewById(R.id.edit_att_att);
        EditText e2= (EditText)findViewById(R.id.edit_att_sub);
        EditText e3= (EditText)findViewById(R.id.indx);
        EditText e4= (EditText)findViewById(R.id.val);
        EditText e5= (EditText)findViewById(R.id.val2);

        int att= Integer.parseInt(e1.getText().toString()) ;
        String sub= e2.getText().toString();
        int index= Integer.parseInt(e3.getText().toString());
        int val= Integer.parseInt(e4.getText().toString());
        int val2= Integer.parseInt(e5.getText().toString());

        SharedPreferences data = this.getSharedPreferences("com.example.anirudh.mid.mid1707",MODE_PRIVATE);
        data.edit().putString("subject",sub).apply();
        data.edit().putInt("att",att).apply();

        data.edit().putInt("index",index).apply();
        data.edit().putInt("value",val).apply();
        data.edit().putInt("value2",val2).apply();


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);



    }
}
