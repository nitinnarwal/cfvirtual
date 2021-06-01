package com.example.cfvirtual.download.data;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUtility {
    public static String STANDINGS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/standings_";
    public static String RATINGS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/ratings_";
    public static String STANDINGS_FAILED = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/failed_standings.txt";
    public static String RATINGS_FAILED = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/failed_ratings.txt";
    public static String CONTESTANTS_FILE_PATH = "/Users/nitin.narwal/Desktop/cf_data/downloaded_data/contestants_";

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
