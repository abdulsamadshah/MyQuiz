package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Adapters.LeaderboardsAdapter;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.User;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.FragmentLeaderboardBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Leaderboard extends Fragment {


    FragmentLeaderboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLeaderboardBinding.inflate(getLayoutInflater(), container, false);




        FirebaseFirestore database = FirebaseFirestore.getInstance();

        final ArrayList<User> users = new ArrayList<>();
        final LeaderboardsAdapter adapter = new LeaderboardsAdapter(getContext(), users);

        binding.leaderboardrv.setAdapter(adapter);
        binding.leaderboardrv.setLayoutManager(new LinearLayoutManager(getContext()));

        database.collection("users")
                .orderBy("coins", Query.Direction.DESCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    User user = snapshot.toObject(User.class);
                    users.add(user);
                }
                adapter.notifyDataSetChanged();
            }
        });





        return binding.getRoot();
    }






}