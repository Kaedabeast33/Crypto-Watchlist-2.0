package com.kaeden.watchlist.Service;

import com.kaeden.watchlist.DTOs.WatchlistDto;
import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Entities.Watchlist;

import javax.transaction.Transactional;
import java.util.Optional;

public interface WatchlistService  {
//    public Optional<WatchlistDto> getWatchlistsByUserId(Long userId);

    Optional<WatchlistDto> getWatchlistsByUserId(Long userId);

    public void addWatchlist(Long id);

    @Transactional
    Watchlist assignCoinToWatchlist(String coinId, Long watchlistId);

    @Transactional
    Optional<CoinData> deleteCoinToWatchlist(String coinId, Long watchlistId);
    @Transactional
    String deleteWatchlistByWatchlistId(Long watchlistId);



//    @Transactional
//    String getAllWatchlistByUserId(Long userId);

//    List<Watchlist> getAllWatchlistByUserId(int userId);

//    List<Watchlist> getAllWatchlistByUserId();
}
