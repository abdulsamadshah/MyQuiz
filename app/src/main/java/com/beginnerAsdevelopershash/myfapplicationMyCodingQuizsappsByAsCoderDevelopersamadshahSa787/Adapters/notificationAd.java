package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.QuiznaboutA;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.notification;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class notificationAd extends RecyclerView.Adapter<notificationAd.notificationviewholder> {


    Context context;
    ArrayList<notification> notificationArrayList;

    public notificationAd(Context context, ArrayList<notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public notificationviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_layout, parent, false);
        return new notificationviewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull notificationviewholder holder, int position) {
        notification notifications = notificationArrayList.get(position);
        holder.ntitle.setText(notifications.getTitle());
        holder.ndesc.setText(notifications.getDescription());
        Picasso.get().load(notifications.getNimage()).placeholder(R.drawable.avatar).into(holder.nimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuiznaboutA.class);
                intent.putExtra("nimage", notifications.getNimage());
                intent.putExtra("ntitles", notifications.getTitle());
                intent.putExtra("ndesc", notifications.getDescription());
                context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });

    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }

    public class notificationviewholder extends RecyclerView.ViewHolder {
        TextView ntitle, ndesc;
        ImageView nimg;


        public notificationviewholder(@NonNull View itemView) {
            super(itemView);
            ntitle = itemView.findViewById(R.id.ntitle);
            ndesc = itemView.findViewById(R.id.ndesc);
            nimg = itemView.findViewById(R.id.nimg);

        }
    }
}
