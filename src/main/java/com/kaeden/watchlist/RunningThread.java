package com.kaeden.watchlist;

import com.google.gson.Gson;
import com.kaeden.watchlist.DAO.CoinDataSaveAndFlush;
import com.kaeden.watchlist.DAO.CoinDataSaveAndFlushDao;
import com.kaeden.watchlist.Entities.CoinData;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Entities.CoinData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@ComponentScan
public class RunningThread implements Runnable {
    @Autowired
    CoinDataSaveAndFlushDao coinDataSaveAndFlushDao;

    @Override
    public void run() {
        Gson gson = new Gson();


        //METHODS{

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=1h%2C24h%2C7d"))
                .header("X-RapidAPI-Key", "1b6c1c913bmsh44f2f5f0f917aecp16b941jsnd895ce315dba")
                .header("X-RapidAPI-Host", "coingecko.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        CoinData[] coinData = gson.fromJson(response.body(), CoinData[].class);
        System.out.println(coinData[99].getName());
        System.out.println(coinData[99].getCurrent_price());


        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Users/kaedenbradshaw/Desktop/My_Crypto_Data/CryptoCoins.txt"));

            for (CoinData coin : coinData) {
                bufferedWriter.write("\n" + coin.getName() +" - "+ coin.getCurrent_price());
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


//        for (CoinData coin : coinData) {
//            System.out.println(coinDataSaveAndFlushDao.save(coin));
////            coinDataSaveAndFlushDao.save(coin);
//        }

//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


    }

}






//









