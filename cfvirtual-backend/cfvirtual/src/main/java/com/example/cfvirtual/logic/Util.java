package com.example.cfvirtual.logic;

import com.example.cfvirtual.entities.RatingChange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static Map<String, Integer> getPrevRatingsMap(List<RatingChange> ratingChangeList){
        Map<String, Integer> map = new HashMap<>();
        for(RatingChange ratingChange:ratingChangeList){
            map.put(ratingChange.getHandle(), ratingChange.getOldRating());
        }

        return map;
    }
}
