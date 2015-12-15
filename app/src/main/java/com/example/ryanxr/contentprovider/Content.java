package com.example.ryanxr.contentprovider;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import java.util.List;


/**
 * Created by RyanxR on 12/15/2015.
 */
public class Content extends Service {
    public Content() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Data db = new Data(this);
        db.deleteAll();
        db.addOffice(new Company(612345678, "Delft", "Rotterdamseweg 137, Delft"));
        db.addOffice(new Company(687654321, "Den Haag", "Rotterdamseweg 138, Delft"));
        db.addOffice(new Company(611111111, "Rotterdam", "Rotterdamseweg 139, Delft"));
        List<Company> list = db.getAllOffices();
    }
 /*   @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    } */
}