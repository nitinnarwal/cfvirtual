package com.example.cfvirtual.controler;

import com.example.cfvirtual.logic.CodeforcesVirtualRatingCalculator;
import com.example.cfvirtual.logic.Contestant;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controler {

	@GetMapping(value = "/")
	public String sayHi(){
		return "Hello from the other side!";
	}

	@GetMapping(value = "/virtual_rating")
	public String getRatings(@RequestParam("contestInfo") String contestsPointsInfo,@RequestParam("initial_rating") int initialRating) {
		List<Contestant> contestantList = new ArrayList<>();
		String[] contestInfoArray = contestsPointsInfo.split(";");
		for(String contestInfo: contestInfoArray){
			String[] contestAndPoints = contestInfo.split(",");
			if(contestAndPoints.length==2){
				int contestId = Integer.parseInt(contestAndPoints[0]);
				int points = Integer.parseInt(contestAndPoints[1]);
				CodeforcesVirtualRatingCalculator calculator = new CodeforcesVirtualRatingCalculator();
				Contestant contestant = calculator.generateRating(contestId, points, initialRating);
				contestantList.add(contestant);

				initialRating = contestant.rating + contestant.delta;
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(contestantList);
		return json;
	}
}
