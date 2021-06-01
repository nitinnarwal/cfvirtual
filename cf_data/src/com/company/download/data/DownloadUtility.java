package com.company.download.data;

import com.company.entities.Contest;
import com.company.entities.ContestStatus;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadUtility {
    public static String STANDINGS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/standings_";
    public static String RATINGS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/ratings_";
    public static String STANDINGS_FAILED = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/failed_standings.txt";
    public static String RATINGS_FAILED = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/failed_ratings.txt";
    public static String CONTESTANTS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/contestants_";
    private static int FINISHED_CONTEST_LIMIT = 10;

    public static void fetchLatestData() {
        String CONTESTS_URL = "https://codeforces.com/api/contest.list";

        try {
            URL urlObj = new URL(CONTESTS_URL);
            HttpURLConnection urlCon = (HttpURLConnection) urlObj.openConnection();
            int responseCode = urlCon.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlCon.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder("");
                String currentLine;
                while ((currentLine = reader.readLine()) != null)
                    response.append(currentLine);
                reader.close();

                String contestListJson = response.toString();

                processLatestData(contestListJson);

            } else{
                System.out.println("contest.list failed response code " + responseCode);

            }
        } catch (MalformedURLException e){
            System.out.println("The specified URL is malformed: " + e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void processLatestData(String contestListJson){
        List<Contest> contestList = getContestList(contestListJson);
        List<Contest> validContestList = getValidContests(contestList);
        downloadAndGenerateContestsData(validContestList);
    }

    private static List<Contest> getContestList(String contestListJson){
        Gson gson = new Gson();
        ContestStatus contestStatus = gson.fromJson(contestListJson, ContestStatus.class);
        return contestStatus.getContestList();
    }

    private static List<Contest> getValidContests(List<Contest> contestList){
        List<Contest> validContestList = new ArrayList<>();
        for(Contest contest:contestList){
            if(contest.getPhase().equals("FINISHED")){
                validContestList.add(contest);
            }
            if(validContestList.size()>=FINISHED_CONTEST_LIMIT){
                break;
            }
        }
        return validContestList;
    }

    private static void downloadAndGenerateContestsData(List<Contest> contestList){
        if(contestList!=null){
            for(Contest contest:contestList){
                downloadData(contest.getId(), contest.getId());
                PreprocessUtility.generateContestList(contest.getId(), contest.getId());
            }
        }
    }

    public static void downloadData(int start, int end){
        String STANDINGS_URL = "http://codeforces.com/api/contest.standings?contestId=";
        String RATINGS_URL = "https://codeforces.com/api/contest.ratingChanges?contestId=";

        try {
            for(int i=start;i<=end;i++){
                URL urlObj;
                HttpURLConnection urlCon;
                int responseCode;
                String url;
                String filePath;

                url = STANDINGS_URL + i;
                filePath = STANDINGS_FILE_PATH + i + ".txt";

                urlObj = new URL(url);
                urlCon = (HttpURLConnection) urlObj.openConnection();

                responseCode = urlCon.getResponseCode();

                if(responseCode == urlCon.HTTP_OK) {

                    InputStream inputStream = urlCon.getInputStream();
                    BufferedInputStream reader = new BufferedInputStream(inputStream);

                    BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, bytesRead);
                    }

                    writer.close();
                    reader.close();

                    System.out.println(i + " Data saved standings");
                } else{
                    System.out.println(i + " Failed in standings");

                    filePath = STANDINGS_FAILED;
                    FileWriter fileWriter = new FileWriter(filePath, true);
                    fileWriter.write(String.valueOf(i) + "\n");
                    fileWriter.flush();
                    fileWriter.close();
                }

                url = RATINGS_URL + i;
                filePath = RATINGS_FILE_PATH + i + ".txt";

                urlObj = new URL(url);
                urlCon = (HttpURLConnection) urlObj.openConnection();

                responseCode = urlCon.getResponseCode();

                if(responseCode == urlCon.HTTP_OK) {

                    InputStream inputStream = urlCon.getInputStream();
                    BufferedInputStream reader = new BufferedInputStream(inputStream);

                    BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(filePath));

                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, bytesRead);
                    }

                    writer.close();
                    reader.close();

                    System.out.println(i + " Data saved ratings");
                } else{
                    System.out.println(i + " Failed in ratings");

                    filePath = RATINGS_FAILED;
                    FileWriter fileWriter = new FileWriter(filePath, true);
                    fileWriter.write(String.valueOf(i) + "\n");
                    fileWriter.flush();
                    fileWriter.close();
                }



                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    System.out.println(i + " Thread Exception");
                    e.printStackTrace();
                    break;
                }

            }
        } catch (MalformedURLException e) {
            System.out.println("The specified URL is malformed: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("An I/O error occurs: " + e.getMessage());
        }
    }
}
