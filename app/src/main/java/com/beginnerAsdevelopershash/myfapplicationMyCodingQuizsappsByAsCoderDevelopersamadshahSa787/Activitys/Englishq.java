package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Adapters.CategoryAdapter;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.CategoryModel;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.User;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.ActivityEnglishqBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class Englishq extends AppCompatActivity {

    ActivityEnglishqBinding binding;
    String userid;
    FirebaseFirestore database;
    User user;
    FirebaseAuth auth;
    FirebaseFirestore categoriesdata;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnglishqBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        windowtoptollbar();
        auth = FirebaseAuth.getInstance();
        userid = auth.getCurrentUser().getUid();
        auth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        categoriesdata = FirebaseFirestore.getInstance();

        categoriesdatashowrecyclerview();



        this.categoriesdata = FirebaseFirestore.getInstance();

    }

    private void categoriesdatashowrecyclerview() {
        final ArrayList<CategoryModel> categories = new ArrayList<>();

        adapter = new CategoryAdapter(getApplicationContext(), categories);

        categoriesdata.collection("englishcategory")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            CategoryModel model = snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });


        binding.engrv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        binding.engrv.setAdapter(adapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void windowtoptollbar() {
        setSupportActionBar(binding.englishqtb);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = Englishq.this.getWindow();
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