package com.dcp.musicsearcher.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dcp.musicsearcher.ItemActivity;
import com.dcp.musicsearcher.R;
import com.dcp.musicsearcher.api.ApiRequester;
import com.dcp.musicsearcher.api.pojo.lyrics.LyricSearch;
import com.dcp.musicsearcher.api.pojo.track.TrackSearch;
import com.dcp.musicsearcher.data.FavoritesController;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by Rage on 21.12.2016.
 */

public class MainActivity extends AppCompatActivity {

    EditText perf;
    EditText name;
    EditText itid;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perf= (EditText) findViewById(R.id.perf);
        name= (EditText) findViewById(R.id.name);
        itid= (EditText) findViewById(R.id.itid);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                String s="";
                s=name.getText().toString();
                intent.putExtra("itemName",s);
                s=perf.getText().toString();
                intent.putExtra("itemPerformer",s);
                Long i=Long.parseLong(itid.getText().toString());
                intent.putExtra("itemId",i);
                startActivity(intent);
            }
        });

    }
}
