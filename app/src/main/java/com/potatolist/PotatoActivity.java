package com.potatolist;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PotatoActivity extends AppCompatActivity {

    private ArrayList<Potatoes> mypotatoes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato);

        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray jsonPotatoes = jsonObject.getJSONArray("potatoes");

            for(int i = 0; i<jsonPotatoes.length(); i++) {
                JSONObject individual = (JSONObject) jsonPotatoes.get(i);
                String name = individual.getString("name");
                String icon = individual.getString("icon");
                String description = individual.getString("description");
                Potatoes c = new Potatoes(name, icon, description);
                mypotatoes.add(c);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        PotatoAdapter myAdapter = new PotatoAdapter(this,mypotatoes);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),potatoDetail.class);
                intent.putExtra("POTATOE", mypotatoes.get(i));
                startActivity(intent);

            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_potato);
//
//        PotatoAdapter myAdapter = new PotatoAdapter(this);
//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(myAdapter);
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(PotatoActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONArray m_jArry = obj.getJSONArray("potatoes");
//            HashMap<String, String> p;
//
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                String p_name = jo_inside.getString("name");
//                String p_description = jo_inside.getString("description");
//                String p_icon = jo_inside.getString("icon");
//
//                Log.d("name-->", jo_inside.getString("name"));
//                Log.d("icon-->", jo_inside.getString("icon"));
//
//                //Add your values in your `ArrayList` as below:
//                p = new HashMap<String, String>();
//                p.put("name", p_name);
//                p.put("description", p_description);
//
//                myAdapter.potatoInfo.add(p);
//
//                Resources resources = getResources();
//                int pIconId = resources.getIdentifier(p_icon, "drawable", getPackageName());
//                myAdapter.potatoIcons.add(pIconId);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("potato.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
