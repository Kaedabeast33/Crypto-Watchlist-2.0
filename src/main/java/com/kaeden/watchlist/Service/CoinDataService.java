package com.kaeden.watchlist.Service;

import com.kaeden.watchlist.Entities.CoinData;

import javax.transaction.Transactional;
import java.util.List;

public interface CoinDataService  {

    //METHODS
//    @Override
//    @Transactional
//    public List<String> addCoin(CoinDto coinDto) {
//        List<String> response = new ArrayList<>();
//        Coin coin = new Coin(coinDto);
//        coinRepository.saveAndFlush(coin);
//        response.add(coin.getCoinName()) ;
//        response.add(coin.getPrice().toString());
//        return response;
//    }
    @Transactional
    List<CoinData> addCoinAndRefreshEveryHour() throws InterruptedException;
    @Transactional
    List<CoinData> addCoin()throws InterruptedException;
}
