package com.kaeden.watchlist.Controller;

import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Service.CoinDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class GeneralController {

    @Autowired
    CoinDataService coinDataSErvice;
    @GetMapping("addCoins")
    public List<CoinData> addCoins() throws InterruptedException {
//        coinDataSErvice.addCoin();
        return coinDataSErvice.addCoin();
    }
//    @GetMapping
//    public List<CoinData> addAllCoins() throws InterruptedException {
//
//        return coinDataSErvice.addCoinAndRefreshEveryHour();
//    }
}
