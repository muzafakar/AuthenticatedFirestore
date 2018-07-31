package com.muzadev.authenticatedfirestore.model;

import android.support.annotation.Nullable;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zulfakar on 31/07/18.
 * For educational purposes
 */
@IgnoreExtraProperties
public class Customer implements Serializable {
    private String name;
    private String address;
    @ServerTimestamp
    private Date timeStamp;

    public Customer() {
    }

    public Customer(String name, String address, @Nullable Date timeStamp) {
        this.name = name;
        this.address = address;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
