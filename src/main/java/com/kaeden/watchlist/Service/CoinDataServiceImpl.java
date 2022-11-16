package com.kaeden.watchlist.Service;

import com.google.gson.Gson;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Repository.CoinDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class CoinDataServiceImpl implements CoinDataService{
    @Autowired
    CoinDataRepository coinDataRepository;



    @Override
    public List<CoinData> addCoin()  {


            Gson gson = new Gson();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h"))
                    .header("X-RapidAPI-Key", "1b6c1c913bmsh44f2f5f0f917aecp16b941jsnd895ce315dba")
                    .header("X-RapidAPI-Host", "coingecko.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = null;
            try {
                getResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(getResponse.body());
            CoinData[] coinData = gson.fromJson(getResponse.body(), CoinData[].class);

            for(CoinData coin:coinData) {
                coinDataRepository.saveAndFlush(coin);
            }

            System.out.println("coinDataSaved");

        return coinDataRepository.findAll();
    }
    @Override
    public List<CoinData> addCoinAndRefreshEveryHour() throws InterruptedException {
        int count = 0;
        while (count < 10) {
            count++;
            Gson gson = new Gson();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&price_change_percentage=24h"))
                    .header("X-RapidAPI-Key", "1b6c1c913bmsh44f2f5f0f917aecp16b941jsnd895ce315dba")
                    .header("X-RapidAPI-Host", "coingecko.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = null;
            try {
                getResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(getResponse.body());
            CoinData[] coinData = gson.fromJson(getResponse.body(), CoinData[].class);

            try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("CryptoCoins.txt"));

				for (CoinData coin : coinData) {
					bufferedWriter.write("\njkljk" + coin.getName() + coin.getCurrent_price());
				}
				bufferedWriter.close();
			} catch (IOException e) {throw new RuntimeException(e);}

            for(CoinData coin:coinData) {
                coinDataRepository.saveAndFlush(coin);
            }

            System.out.println("coinDataSaved");
            Thread.sleep(400000);

        }
        return coinDataRepository.findAll();
    }
}
