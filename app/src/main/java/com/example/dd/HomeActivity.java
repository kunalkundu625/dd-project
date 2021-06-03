package com.example.dd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);
        Button b3 = (Button) findViewById(R.id.b3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
                startActivity(intent);

                }





        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PrescriptionActivity.class);
                startActivity(intent);

                }


        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ReportActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.item3){
            Intent intent=new Intent(HomeActivity.this,MapsActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        else
        if(id==R.id.item1){
            Intent intent=new Intent(HomeActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
            return true;

        }
        else
        if(id==R.id.item2){
            Toast.makeText(this,"Contact To 8830584062, 9306411863",Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}