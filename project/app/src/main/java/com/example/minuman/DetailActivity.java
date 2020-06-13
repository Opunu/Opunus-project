package com.example.minuman;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import static com.example.minuman.MainActivity.EXTRA_HARGA;
import static com.example.minuman.MainActivity.EXTRA_IMG;
import static com.example.minuman.MainActivity.EXTRA_KETERANGAN;
import static com.example.minuman.MainActivity.EXTRA_NAMA;

public class DetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent= getIntent();
        String foto= intent.getStringExtra(EXTRA_IMG);
        String nama= intent.getStringExtra(EXTRA_NAMA);
        String keterangan= intent.getStringExtra(EXTRA_KETERANGAN);
        String harga= intent.getStringExtra(EXTRA_HARGA);

        ImageView iviewgambar = findViewById(R.id.detail_gambar);
        TextView tvnama = findViewById(R.id.detail_nama);
        TextView tvket = findViewById(R.id.detail_keterangan);
        TextView tvharga = findViewById(R.id.detail_harga);

        Glide
                .with(this)
                .load(foto)
                .into(iviewgambar);

        tvnama.setText(nama);
        tvket.setText(keterangan);
        tvharga.setText(harga);

    }
}
