package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.User;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.WithdrawRequests;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;


public class Profile extends Fragment {

    FragmentProfileBinding binding;
    FirebaseFirestore database;
    String userid;
    User user;
    FirebaseAuth auth;
    FirebaseFirestore categoriesdata;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater(), container, false);


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
                    binding.usernames.setText(documentSnapshot.getString("name"));
                    binding.currentCoins.setText(String.valueOf(user.getCoins()));
                    Picasso.get().load(user.getUserimg()).placeholder(R.drawable.avatar).into(binding.myuserimg);
//                    binding.mycoins.setText(String.valueOf(user.getCoins()));

                } else {
                    Toast.makeText(getActivity(), "document is not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });






        binding.sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getCoins() > 50000) {
                    String uid = FirebaseAuth.getInstance().getUid();
                    String gpayn = binding.gpay.getText().toString();
                    String phonepayns = binding.phonepay.getText().toString();
                    WithdrawRequests request = new WithdrawRequests(userid, gpayn, phonepayns, user.getName());
                    database
                            .collection("withdraws")
                            .document(uid)
                            .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity(), "Request sent successfully.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "You need more coins to get withdraw.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return binding.getRoot();
    }
}