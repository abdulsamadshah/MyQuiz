package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Codingqc;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Englishq;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Gkquizc;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Pictureq;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Setting;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Studyqc;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.User;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;


public class Home extends Fragment {

    FragmentHomeBinding binding;
    FirebaseFirestore database;
    RecyclerView categoryList;
    String userid;
    User user;
    FirebaseAuth auth;
    FirebaseFirestore categoriesdata;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        windowtoptollbar();
        Picasso.get().load("https://ugc.futurelearn.com/uploads/images/4d/c9/header_4dc9321b-f608-4196-9fb7-02f6c0029a5f.jpg").placeholder(R.drawable.avatar).into(binding.studyquizimg);
        Picasso.get().load("https://engineering.fb.com/wp-content/uploads/2013/03/andy-lg.png").placeholder(R.drawable.avatar).into(binding.quizimgq);
        Picasso.get().load("https://ritajms.com/wp-content/uploads/2019/11/52351011-english-british-england-language-education-concept.jpg").placeholder(R.drawable.avatar).into(binding.engquizimg);
        Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkdPFA6r_IbzQJcyXrKT5TSritv0S_iWwFmw&usqp=CAU").placeholder(R.drawable.avatar).into(binding.picquizcimg);
        Picasso.get().load("https://play-lh.googleusercontent.com/MfZdEFuB0safwCKgfrJUtdyHLzqn_hmhyYMIWKRLzKJqzeuwe3rASkRzEcQIcWPnww").placeholder(R.drawable.avatar).into(binding.gkquizcimg);
        binding.codingquizcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Codingqc.class);
                startActivity(intent);

            }
        });

        binding.studyquizsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Studyqc.class);
                startActivity(intent);
            }
        });


        binding.engquizc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Englishq.class);
                startActivity(intent);
            }
        });


        binding.picquizc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Pictureq.class);
                startActivity(intent);
            }
        });

        binding.gkquizc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Gkquizc.class);
                startActivity(intent);
            }
        });






        notification();

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userid = auth.getCurrentUser().getUid();
        categoriesdata = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        DocumentReference reference = database.collection("users").document(userid);
        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    user = documentSnapshot.toObject(User.class);
                    binding.username.setText(documentSnapshot.getString("name"));
                    Picasso.get().load(user.getUserimg()).placeholder(R.drawable.avatar).into(binding.userimg);
//                    binding.mycoins.setText(String.valueOf(user.getCoins()));

                } else {
                    Toast.makeText(getActivity(), "document is not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });


        FirebaseMessaging.getInstance().subscribeToTopic("mynotification");


        return binding.getRoot();
    }



    private void windowtoptollbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(binding.maintb);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.mainh));

        }
    }

    public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

        private int height;

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
            height = child.getHeight();
            return super.onLayoutChild(parent, child, layoutDirection);
        }

        @Override
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                           BottomNavigationView child, @NonNull
                                                   View directTargetChild, @NonNull View target,
                                           int axes, int type) {
            return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

        @Override
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                                   @NonNull View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed,
                                   @ViewCompat.NestedScrollType int type) {
            if (dyConsumed > 0) {
                slideDown(child);
            } else if (dyConsumed < 0) {
                slideUp(child);
            }
        }

        private void slideUp(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(0).setDuration(200);
        }

        private void slideDown(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(height).setDuration(200);
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.rightmenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent(getActivity(), Setting.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    public void notification(){
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }
                        Log.d(TAG, msg);
                    }
                });
    }
}