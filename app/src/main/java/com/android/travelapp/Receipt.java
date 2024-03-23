package com.android.travelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Receipt extends AppCompatActivity {
    ImageView imgTour;
    TextView nameTour, totalPeople, priceTour, totalPrice, name, email, phone;
    Button btnConfirm;
    AlertDialog dialog;
    SharedPreferences preferences;

    String CHANNEL_ID = "Travel App v2.6 (BETA)";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private static final String KEY_IMG_TOUR = "img_tour";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";
    private static final String KEY_PRICE_TOUR = "price_tour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        imgTour = findViewById(R.id.img_tour);
        nameTour = findViewById(R.id.name_tour);
        totalPeople = findViewById(R.id.total_people);
        priceTour = findViewById(R.id.price_tour);
        totalPrice = findViewById(R.id.total_price);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnConfirm = findViewById(R.id.btn_confirm);

        preferences = getSharedPreferences("userInfo", 0);

        String nameView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);
        String phoneView = preferences.getString(KEY_PHONE, null);

        String imgTourView = preferences.getString(KEY_IMG_TOUR, null);
        String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
        String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
        String priceView = preferences.getString(KEY_PRICE_TOUR, null);
        String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);

        if (nameView != null || emailView != null || phoneView != null || nameTourView != null || totalItemsView != null || priceView != null || totalPriceView != null || imgTourView != null){
            Glide.with(this).asBitmap().load(imgTourView).into(imgTour);
            name.setText(nameView);
            email.setText(emailView);
            phone.setText(phoneView);
            nameTour.setText(nameTourView);
            priceTour.setText("Rp"+priceView);
            totalPeople.setText(totalItemsView + " Orang");
            totalPrice.setText("Rp"+totalPriceView);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(Receipt.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Message")
                        .setMessage("\nAre you sure booked this spot?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Receipt.this, "Success Booked Ticket", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Receipt.this, Receipt.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent pendingIntent = PendingIntent.getActivity(Receipt.this, 0, intent, PendingIntent.FLAG_MUTABLE);

                                NotificationCompat.Builder builder = new NotificationCompat.Builder(Receipt.this, CHANNEL_ID)
                                        .setSmallIcon(R.drawable.ic_ticket)
                                        .setContentTitle("Detail Ticket")
                                        .setStyle(new NotificationCompat.BigTextStyle()
                                                .bigText("\nYour Ticket Successfully Booked!\n" +
                                                        "=====================================" + "\n" +
                                                        "Nama Pemesan\t: "+nameView+ "\n" +
                                                        "Nama Tempat\t: "+nameTourView+ "\n" +
                                                        "Total Orang\t: "+totalItemsView+ "\n" +
                                                        "Total Harga\t: Rp"+totalPriceView+ "\n" +
                                                        "====================================="))
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        // Set the intent that will fire when the user taps the notification
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Receipt.this);
                                notificationManager.notify(25, builder.build());
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetDetailTour();
                                Toast.makeText(Receipt.this, "Fail Booked Ticket", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Receipt.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private void resetDetailTour(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME_TOUR, null);
        editor.putString(KEY_COUNT_ITEMS, null);
        editor.putString(KEY_TOTAL_PRICE, null);
        editor.apply();
    }
}