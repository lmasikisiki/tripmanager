package com.khwela.khwelacore.commons;

import com.khwela.khwelacore.models.TripRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommonUtilities {

    public static TripRecord addUserToTripRecord(String userId, TripRecord tripRecord) {
        ArrayList<String> users = tripRecord.getUsers();
        users.add(userId);
        tripRecord.setUsers(users);
        return tripRecord;
    }

    public static Boolean travelOnSameDay(Date userTripDate, Date driverTripDate){
        SimpleDateFormat fmt= new SimpleDateFormat("YYYY/MM/dd");
        return fmt.format(userTripDate).equals(fmt.format(driverTripDate));
    }
}
