package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Adapters.notificationAd;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.notification;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.FragmentSearchBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Search extends Fragment {

    FragmentSearchBinding binding;
    ArrayList<notification> notificationArrayList;
    FirebaseDatabase database;
    notificationAd notificationAds;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(getLayoutInflater(), container, false);

        database=FirebaseDatabase.getInstance();

        notificationArrayList=new ArrayList<>();
        binding.notircationrv.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAds=new notificationAd(getActivity(),notificationArrayList);


        database.getReference().child("mynotification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    notification notifications=dataSnapshot.getValue(notification.class);
                    notificationArrayList.add(notifications);

                }
                binding.notircationrv.setAdapter(notificationAds);
                notificationAds.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        return binding.getRoot();

    }


}