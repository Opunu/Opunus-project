package com.example.minuman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MenuAdapter.OnItemClickListener {

    public static final String EXTRA_IMG = "gambar";
    public static final String EXTRA_NAMA = "nama";
    public static final String EXTRA_KETERANGAN = "keterangan";
    public static final String EXTRA_HARGA = "harga";

    private MenuAdapter menuAdapater;
    private RecyclerView recyclerView;
    private ArrayList<Menu> menus;
    int jumdata;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tv_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menus = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

    private void parseJSON() {

        String urlweb = "https://opunyalak.000webhostapp.com/koneksi.php";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlweb, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        jumdata = response.length();
                        try {
                            for (int i = 0; i < jumdata; i++) {
                                JSONObject data = response.getJSONObject(i);
                                String gambarmenu = data.getString("gambar");
                                String namamenu = data.getString("nama");
                                String keteranganmenu = data.getString("keterangan");
                                String hargamenu = data.getString("harga");
                                menus.add(new Menu( gambarmenu, namamenu, keteranganmenu, hargamenu));
                            }
                            menuAdapater = new MenuAdapter(MainActivity.this, menus);
                            recyclerView.setAdapter(menuAdapater);

                            menuAdapater.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }

    @Override
    public void onItemclick(int position) {
        Intent intentdetail = new Intent(this,DetailActivity.class);
        Menu clickitem = menus.get(position);
        intentdetail.putExtra(EXTRA_IMG, clickitem.getGambar());
        intentdetail.putExtra(EXTRA_NAMA, clickitem.getNama());
        intentdetail.putExtra(EXTRA_KETERANGAN, clickitem.getKeterangan());
        intentdetail.putExtra(EXTRA_HARGA, clickitem.getHarga());

        startActivity(intentdetail);
    }
}