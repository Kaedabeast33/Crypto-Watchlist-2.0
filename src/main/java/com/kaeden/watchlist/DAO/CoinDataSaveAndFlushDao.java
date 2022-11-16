package com.kaeden.watchlist.DAO;

import com.kaeden.watchlist.Entities.CoinData;

public interface CoinDataSaveAndFlushDao {
    public boolean save(CoinData coinData);
}
