package com.company.logic;

import com.company.entities.RatingChange;

import java.text.SimpleDateFormat;
import java.util.*;

public class Util {
    public static Map<String, Integer> getPrevRatingsMap(List<RatingChange> ratingChangeList) {
        Map<String, Integer> map = new HashMap<>();
        for (RatingChange ratingChange : ratingChangeList) {
            map.put(ratingChange.getHandle(), ratingChange.getOldRating());
        }

        return map;
    }

    public static void printDateAndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        System.out.println("[" + sdf.format(calendar.getTime()) + " IST]");
    }
}
