package com.kaeden.watchlist;

import com.google.gson.Gson;
import com.kaeden.watchlist.DAO.CoinDataSaveAndFlush;
import com.kaeden.watchlist.DAO.CoinDataSaveAndFlushDao;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Repository.CoinDataRepository;
import com.kaeden.watchlist.Service.CoinDataService;
import com.kaeden.watchlist.Service.CoinDataServiceImpl;
import com.kaeden.watchlist.Service.RunningThreadInterface;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.kaeden.watchlist.Entities.CoinData;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
@SpringBootApplication
public class WatchlistApplication {

	public static void main(String[] args) throws Exception {

		RunningThread runningThread = new RunningThread();
		Thread thread = new Thread(runningThread);
		thread.start();

		SpringApplication.run(WatchlistApplication.class, args);

	}
}



//


//		}











