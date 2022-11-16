package com.kaeden.watchlist.Controller;

import com.kaeden.watchlist.DAO.WatchlistDAO;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Entities.Watchlist;
import com.kaeden.watchlist.Repository.WatchlistRepository;
import com.kaeden.watchlist.Service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/watchlists")
public class WatchlistController {
    @Autowired
    WatchlistService watchlistService;

    @Autowired
    WatchlistDAO watchlistDAO;

    @Autowired
    WatchlistRepository watchlistRepository;

//    @GetMapping("/{userId}")
//    public Optional<WatchlistDto> getWatchlistsByUserId(@PathVariable Long userId){
//        Optional<WatchlistDto> watchlistsByUserId = WatchlistService.getWatchlistsByUserId(userId);
//        return watchlistsByUserId;
//    }
    @PutMapping("/{watchlistId}/coin/{coinId}")
    public Watchlist assignCoinToWatchlist(@PathVariable Long watchlistId, @PathVariable String coinId){

        return watchlistService.assignCoinToWatchlist(coinId,watchlistId);
    }
    @DeleteMapping("/{watchlistId}")
    public String deleteWatchlist(@PathVariable Long watchlistId){
        return watchlistService.deleteWatchlistByWatchlistId(watchlistId);
    }
    @GetMapping("/users/{userId}")
    public List<Watchlist> getAllWatchlistByUserId(@PathVariable Long userId){
        return watchlistRepository.getWatchlistByUserId(userId);
    }
    @DeleteMapping("/{watchlistId}/coin/{coinId}")
    public Optional<CoinData> deleteCoinFromWatchlist(@PathVariable Long watchlistId, @PathVariable String coinId){
        return watchlistService.deleteCoinToWatchlist(coinId,watchlistId);
    }
    @GetMapping("/{watchlistId}")
    public Optional<Watchlist> getWatchlistById(@PathVariable Long watchlistId){
        return watchlistRepository.findById(watchlistId);
    }
}
