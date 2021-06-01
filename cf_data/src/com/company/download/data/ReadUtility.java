package com.company.download.data;

import com.company.entities.RatingChange;
import com.company.entities.RatingChangeStatus;
import com.company.entities.StandingsRow;
import com.company.entities.StandingsRowsStatus;
import com.company.logic.Contestant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReadUtility {

    public static List<StandingsRow> getStandingsRows(int contestId){
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            String filePath = DownloadUtility.STANDINGS_FILE_PATH + contestId + ".txt";
            File myObj = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(myObj));
            String st;
            while((st = br.readLine()) != null) {
                stringBuilder.append(st);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            Gson gson = new Gson();
            StandingsRowsStatus standingsRowsStatus = gson.fromJson(stringBuilder.toString(), StandingsRowsStatus.class);

            List<StandingsRow> standingsRows = new ArrayList<>();
            if (standingsRowsStatus != null && standingsRowsStatus.getStandingsRows() != null
                    && standingsRowsStatus.getStandingsRows().getRows() != null) {
                standingsRows = standingsRowsStatus.getStandingsRows().getRows();
            }
            return standingsRows;
        }
    }

    public static List<RatingChange> getRatingChangeList(int contestId){
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            String filePath = DownloadUtility.RATINGS_FILE_PATH + contestId + ".txt";
            File myObj = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(myObj));
            String st;
            while((st = br.readLine()) != null) {
                stringBuilder.append(st);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            Gson gson = new Gson();
            RatingChangeStatus ratingChangeStatus = gson.fromJson(stringBuilder.toString(), RatingChangeStatus.class);

            List<RatingChange> ratingChangeList = new ArrayList<>();
            if (ratingChangeStatus != null && ratingChangeStatus.getRatingChangeList() != null) {
                ratingChangeList = ratingChangeStatus.getRatingChangeList();
            }
            return ratingChangeList;
        }
    }

    public static List<Contestant> getContestantList(int contestId){
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            String filePath = DownloadUtility.CONTESTANTS_FILE_PATH + contestId + ".txt";
            File myObj = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(myObj));
            String st;
            while((st = br.readLine()) != null) {
                stringBuilder.append(st);
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            Type listType = new TypeToken<List<Contestant>>() {
            }.getType();
            Gson gson = new Gson();
            List<Contestant> contestantList = gson.fromJson(stringBuilder.toString(), listType);

            return contestantList;
        }
    }
}
