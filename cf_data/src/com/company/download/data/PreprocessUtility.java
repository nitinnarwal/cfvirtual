package com.company.download.data;

import com.company.entities.RatingChange;
import com.company.entities.StandingsRow;
import com.company.logic.CodeforcesVirtualRatingCalculator;
import com.company.logic.Contestant;
import com.company.logic.Util;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PreprocessUtility {

    public static void generateContestList(int start, int end) {
        CodeforcesVirtualRatingCalculator calculator = new CodeforcesVirtualRatingCalculator();
        Gson gson = new Gson();

        for(int contestId = start; contestId<=end; contestId++){
            System.out.println("Started contest " + contestId);
            List<RatingChange> ratingChangeList = ReadUtility.getRatingChangeList(contestId);
            List<StandingsRow> standingsRowList = ReadUtility.getStandingsRows(contestId);
            Map<String, Integer> previousRatings = Util.getPrevRatingsMap(ratingChangeList);

            List<Contestant> contestantList = calculator.getProcessedContestants(previousRatings, standingsRowList);
            String json = gson.toJson(contestantList);

            String filePath = DownloadUtility.CONTESTANTS_FILE_PATH + contestId + ".txt";
            try {
                FileWriter fileWriter = new FileWriter(filePath);
                fileWriter.write(json);
                fileWriter.flush();
                fileWriter.close();

                System.out.println("Preprocessed " + contestId);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed in writing to file " + contestId);
            }
        }
    }
}
