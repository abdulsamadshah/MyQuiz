package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.IconCompat;


import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        getFirebaseMessage(message.getNotification().getTitle(), message.getNotification().getBody(), message.getNotification().getImageUrl().toString());
    }

    public void getFirebaseMessage(String title, String msg, String nimg) {

        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            builder = new NotificationCompat.Builder(this, "usernotification")
                    .setSmallIcon(IconCompat.createWithContentUri(nimg))
                    .setContentTitle(title)
                    .setContentText(msg)
                    .setAutoCancel(true);
        }

        notification notification = new notification(title, msg,nimg);
        database.getReference().child("mynotification").push().setValue(notification);

//        StorageReference reference=storage.getReference().child("notificationimg").child(new Date().getTime()+"");
//        reference.putFile()

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(25, builder.build());

    }
}
