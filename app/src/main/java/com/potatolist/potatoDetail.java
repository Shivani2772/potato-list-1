package com.potatolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class potatoDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.potato_detail);

        ImageView icon = (ImageView) findViewById(R.id.image);
        TextView name = (TextView) findViewById(R.id.name);
        TextView description = (TextView) findViewById(R.id.description);

        Potatoes potatoes = (Potatoes) getIntent().getSerializableExtra("POTATOE");
        Log.d("Potatoes" + potatoes.getIcon(), "" + (icon == null));
        icon.setBackgroundResource(potatoes.getIcon());
        name.setText(potatoes.getName());
        description.setText(potatoes.getDescription());
    }
}
