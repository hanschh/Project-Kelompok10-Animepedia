package com.dih.animepedia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescriptionActivity extends AppCompatActivity {
TextView name;
TextView synops;
TextView desc;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_anime);

        name = findViewById(R.id.txt_name);
        desc = findViewById(R.id.txt_description);
        synops = findViewById(R.id.txt_synopsis);
        image = findViewById(R.id.img_photo);

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("name"));
        desc.setText(intent.getStringExtra("desc"));
        synops.setText(intent.getStringExtra("synops"));
        image.setImageResource(intent.getIntExtra("image",0));

    }
}
