package com.kaeden.watchlist.DTOs;

import com.kaeden.watchlist.Entities.CoinData;
import com.kaeden.watchlist.Entities.Watchlist;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
public class WatchlistDto implements Serializable {
    private Long id;
    private Set<CoinData> coinData;

    //CONSTRUCTOR
    public WatchlistDto(Watchlist watchlist){
        if(watchlist.getId()!=null){
            this.id= watchlist.getId();

        }if(watchlist.getCoinSet()!=null){
            this.coinData = watchlist.getCoinSet();
        }
    }
    public WatchlistDto(){

    }

//GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
