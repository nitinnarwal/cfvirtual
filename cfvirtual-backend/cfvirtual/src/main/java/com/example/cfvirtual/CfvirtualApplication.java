package com.example.cfvirtual;

import com.example.cfvirtual.download.data.DownloadUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CfvirtualApplication {

	public static void main(String[] args) {
		initDirectories();
		SpringApplication.run(CfvirtualApplication.class, args);
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
