package com.uevents.app.uevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("View Event");
        setSupportActionBar(toolbar);

        // Back button on toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewEventActivity.this.onBackPressed();
            }
        });


        // --- Retrieve Event information ---

        Bundle bundle = getIntent().getExtras();

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(bundle.getString("title"));

        ImageView icon = (ImageView) findViewById(R.id.icon);
        TextView subtitle = (TextView) findViewById(R.id.subtitle);
        switch(bundle.getString("category")) {
            case "SPORT":
                icon.setImageResource(R.mipmap.ic_sport);
                subtitle.setText("Sport event by @" + bundle.getString("creator"));
                break;
            case "STUDY":
                icon.setImageResource(R.mipmap.ic_study);
                subtitle.setText("Study event by @" + bundle.getString("creator"));
                break;
            case "SOCIAL":
                icon.setImageResource(R.mipmap.ic_social);
                subtitle.setText("Social event by @" + bundle.getString("creator"));
                break;
            case "CLUB":
                icon.setImageResource(R.mipmap.ic_club);
                subtitle.setText("Club event by @" + bundle.getString("creator"));
                break;
        }

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(bundle.getString("description"));

        Button buttonG = (Button) findViewById(R.id.btn_going);
        Button buttonNG = (Button) findViewById(R.id.btn_not_going);
        Button buttonS = (Button) findViewById(R.id.btn_share);




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action -- ", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }



}
