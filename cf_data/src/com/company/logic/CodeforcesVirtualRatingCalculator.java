package com.company.logic;

import com.company.entities.StandingsRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CodeforcesVirtualRatingCalculator extends CodeforcesRatingCalculator2 {

    public List<Contestant> getProcessedContestants(Map<String, Integer> previousRatings,
                                                    List<StandingsRow> standingsRows){
        List<Contestant> contestants = new ArrayList<>(standingsRows.size());

        for (StandingsRow standingsRow : standingsRows) {
            int rank = standingsRow.getRank();
            if(standingsRow.getParty()!=null && standingsRow.getParty().getMembers()!=null
                    && standingsRow.getParty().getMembers().size()>0
                    && standingsRow.getParty().getMembers().get(0).getHandle()!=null) {
                String handle = standingsRow.getParty().getMembers().get(0).getHandle();
                if(previousRatings.containsKey(handle)) {
                    contestants.add(new Contestant(handle, rank, standingsRow.getPoints(), previousRatings.get(handle)));
                }
            }
        }
        preProcessContestants(contestants);

        return contestants;
    }

    private void preProcessContestants(List<Contestant> contestants){
        if (contestants.isEmpty()) {
            return;
        }

        reassignRanks(contestants);

        for (Contestant a : contestants) {
            a.seed = 1;
            for (Contestant b : contestants) {
                if (a != b) {
                    a.seed += getEloWinProbability(b, a);
                }
            }
        }

        for (Contestant contestant : contestants) {
            double midRank = Math.sqrt(contestant.rank * contestant.seed);
            contestant.needRating = getRatingToRank(contestants, midRank);
            contestant.delta = (contestant.needRating - contestant.rating) / 2;
        }
    }

    public void generateVirtualRating(List<Contestant> contestantList, Contestant currentContestant){
        for(Contestant contestant: contestantList){
            currentContestant.seed += getEloWinProbability(contestant, currentContestant);
        }

        contestantList.add(currentContestant);
        reassignRanks(contestantList);

        double midRank = Math.sqrt(currentContestant.rank * currentContestant.seed);
        currentContestant.needRating = getRatingToRank(contestantList, midRank);
        currentContestant.delta = (currentContestant.needRating - currentContestant.rating) / 2;

        sortByRatingDesc(contestantList);

        // Total sum should not be more than zero.
        {
            int sum = 0;
            for (Contestant c : contestantList) {
                sum += c.delta;
            }
            int inc = -sum / contestantList.size() - 1;
            for (Contestant contestant : contestantList) {
                contestant.delta += inc;
            }
        }

        // Sum of top-4*sqrt should be adjusted to zero.
        {
            int sum = 0;
            int zeroSumCount = Math.min((int) (4 * Math.round(Math.sqrt(contestantList.size()))), contestantList.size());
            for (int i = 0; i < zeroSumCount; i++) {
                sum += contestantList.get(i).delta;
            }
            int inc = Math.min(Math.max(-sum / zeroSumCount, -10), 0);
            for (Contestant contestant : contestantList) {
                contestant.delta += inc;
            }
        }

    }
}
