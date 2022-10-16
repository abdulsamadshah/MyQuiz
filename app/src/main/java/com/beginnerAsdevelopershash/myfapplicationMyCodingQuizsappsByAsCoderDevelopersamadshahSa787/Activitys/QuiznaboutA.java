package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.ActivityQuiznaboutBinding;
import com.squareup.picasso.Picasso;

public class QuiznaboutA extends AppCompatActivity {

    ActivityQuiznaboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuiznaboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        windowtoptollbar();
        String images = getIntent().getStringExtra("nimage");
        Picasso.get().load(images).placeholder(R.drawable.avatar).into(binding.nimage);
        binding.ntitle.setText(getIntent().getStringExtra("ntitles"));
        binding.ndescription.setText(getIntent().getStringExtra("ndesc"));




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void windowtoptollbar() {
        setSupportActionBar(binding.qabouttb);
        binding.qabouttb.setTitle(getIntent().getStringExtra("ntitles"));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = QuiznaboutA.this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.mainh));

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}