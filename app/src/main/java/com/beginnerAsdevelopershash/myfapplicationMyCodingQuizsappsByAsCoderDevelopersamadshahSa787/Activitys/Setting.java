package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;



import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.User;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.ActivitySettingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class Setting extends AppCompatActivity {

    FirebaseAuth auth;
    ActivitySettingBinding binding;
    FirebaseFirestore database;
    RecyclerView categoryList;
    String userid;
    User user;
    FirebaseFirestore categoriesdata;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        settingintegration();
        LoadLocale();

    }

    private void settingintegration() {
        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userid = auth.getCurrentUser().getUid();
        categoriesdata = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        windowtoptollbar();

        DocumentReference reference = database.collection("users").document(userid);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    user = documentSnapshot.toObject(User.class);
                    binding.usernames.setText(documentSnapshot.getString("name"));
                    Picasso.get().load(user.getUserimg()).placeholder(R.drawable.avatar).into(binding.userimages);
//                    binding.mycoins.setText(String.valueOf(user.getCoins()));

                } else {
                    Toast.makeText(Setting.this, "document is not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.selecttheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.selecttheme.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Light mode Enable", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                } else {
                    Toast.makeText(getApplicationContext(), "Night mode Enable", Toast.LENGTH_SHORT).show();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });

        //logout
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });

        binding.languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChanLanguage();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void windowtoptollbar() {
        setSupportActionBar(binding.settingtb);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = Setting.this.getWindow();
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
    private void ChanLanguage() {
        final String language[] = {"English", "Hindi", "Marathi","Punjabi"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(Setting.this);
        mbuilder.setTitle("Choose Language");
        mbuilder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (i == 0) {
                    setLocale("");
//                    requireContext();
                    recreate();
                } else if (i == 1) {
                    setLocale("hi");
//                    requireContext();
                    recreate();
                } else if (i == 2) {
                    setLocale("mr");
//                    requireContext();
                    recreate();
                }else if (i == 3) {
                    setLocale("pa");
//                    requireContext();
                    recreate();
                }
            }
        });
        mbuilder.create();
        mbuilder.show();


    }


    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = this.getApplicationContext().getSharedPreferences("Setting", Context.MODE_PRIVATE).edit();
        editor.putString("app_lang", language);
        editor.apply();
    }


    private void LoadLocale() {
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences("Setting", Context.MODE_PRIVATE);
        String languagess = sharedPreferences.getString("app_lang", "");
        setLocale(languagess);
    }


}