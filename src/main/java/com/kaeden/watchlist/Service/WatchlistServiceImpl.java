package com.kaeden.watchlist.Service;


import com.kaeden.watchlist.DTOs.WatchlistDto;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Entities.User;
import com.kaeden.watchlist.Entities.Watchlist;
import com.kaeden.watchlist.Repository.CoinDataRepository;
import com.kaeden.watchlist.Repository.UserRepository;
import com.kaeden.watchlist.Repository.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class WatchlistServiceImpl implements WatchlistService {
    @Autowired
    @Qualifier("watchlistRepository")
    WatchlistRepository watchlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CoinDataRepository coinRepository;

    @Override
    public Optional<WatchlistDto> getWatchlistsByUserId(Long userId) {

        return Optional.empty();
    }

    @Override
    public void addWatchlist(Long user_id) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findById(user_id);
        Watchlist watchlist = new Watchlist();
        userOptional.ifPresent(user -> watchlist.setUser(user));
        watchlistRepository.saveAndFlush(watchlist);

    }

    @Override
    public Watchlist assignCoinToWatchlist(String coinId, Long watchlistId) {
        Watchlist watchlist = watchlistRepository.findById(watchlistId).get();
        Optional<CoinData> coinOptional = coinRepository.findById(coinId);

         coinOptional.ifPresent(coinSet -> watchlist.addToCoinSet(coinSet));

        return watchlistRepository.saveAndFlush(watchlist);

    }
    public Optional<CoinData> deleteCoinToWatchlist(String coindId, Long watchlistId){
        Watchlist watchlist = watchlistRepository.findById(watchlistId).get();
        Optional<CoinData> coinDataOptional = coinRepository.findById(coindId);
        coinDataOptional.ifPresent(coinData -> watchlist.deleteFromCoinSet(coinData));
        return coinDataOptional;
    }

    @Override
    @Transactional
    public String deleteWatchlistByWatchlistId(Long watchlistId) {
        Optional<Watchlist> watchlistOptional = watchlistRepository.findById(watchlistId);
        watchlistOptional.ifPresent( watchlist-> watchlistRepository.customDeleteWatchlistCoinsById(watchlistId));
       watchlistOptional.ifPresent(watchlist -> watchlistRepository.customDeleteWatchlistById(watchlistId) );
        
         return "watchlist "+ watchlistOptional.get().getId()+ " deleted";



    }

//    @Override
//    public String getAllWatchlistByUserId(Long userId) {
//
////
//    }

//    @Override
//    public List<Watchlist> getAllWatchlistByUserId(int userId) {
//
//        return watchlistRepository.getWatchlistByUserId(userId);userId
//    }
}
