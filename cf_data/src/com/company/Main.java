package com.company;

import com.company.download.data.DownloadUtility;
import com.company.download.data.PreprocessUtility;
import com.company.download.data.ReadUtility;
import com.company.entities.MembersItem;
import com.company.entities.Party;
import com.company.entities.RatingChange;
import com.company.entities.StandingsRow;
import com.company.logic.CodeforcesVirtualRatingCalculator;
import com.company.logic.Contestant;
import com.company.logic.Util;
import com.google.gson.Gson;

import javax.print.DocFlavor;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Util.printDateAndTime();
        if (args == null || args.length == 0) {
            System.out.println("args usage:\n" +
                    "for fetching Latest Contests: 1\n" +
                    "for specific range of contests: 2 <start_contest_id> <end_contest_id>\n");

            return;
        }

        initDirectories();

        if (args.length == 1 && args[0].equals("1")) {
            DownloadUtility.fetchLatestData();
        } else if (args.length == 3 && args[0].equals("2")) {
            int start = Integer.parseInt(args[1]);
            int end = Integer.parseInt(args[2]);
            DownloadUtility.downloadData(start, end);
            PreprocessUtility.generateContestList(start, end);
        }

    }

    private static void initDirectories() {
        String cfDataDirectory = System.getProperty("user.home") + "/cf_data/";
        System.out.println(cfDataDirectory);
        File cf_data_file = new File(cfDataDirectory);
        cf_data_file.mkdir();

        DownloadUtility.STANDINGS_FILE_PATH = cfDataDirectory + "standings_";
        DownloadUtility.STANDINGS_FAILED = cfDataDirectory + "failed_standings.txt";
        DownloadUtility.RATINGS_FILE_PATH = cfDataDirectory + "ratings_";
        DownloadUtility.RATINGS_FAILED = cfDataDirectory + "failed_ratings.txt";
        DownloadUtility.CONTESTANTS_FILE_PATH = cfDataDirectory + "contestants_";
    }
}
