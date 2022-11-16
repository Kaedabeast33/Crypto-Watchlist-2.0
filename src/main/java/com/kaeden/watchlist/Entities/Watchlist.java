package com.kaeden.watchlist.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kaeden.watchlist.DTOs.WatchlistDto;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "watchlist")
@OnDelete(action=OnDeleteAction.CASCADE)
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(

            name="watchlist_coins",
            joinColumns = @JoinColumn(name="watchlist_id"),
            inverseJoinColumns = @JoinColumn(name="coin_id")
    )
    private Set<CoinData> coinSet= new HashSet<>();
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

//METHODS

    public Watchlist() {
    }

    public Watchlist(Long id) {
        this.id = id;
//        this.user = user;
    }

    public Watchlist(WatchlistDto watchlistDto) {


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
    this.user = user;
    }

    public Set<CoinData> getCoinSet() {
        return coinSet;
    }

    public void setCoinSet(Set<CoinData> coinSet) {
        this.coinSet = coinSet;
    }

    public User getUser() {
        return user;
    }

    public void addToCoinSet(CoinData coinSet){
        this.coinSet.add(coinSet);
    }


    public void deleteFromCoinSet(CoinData coinData) {
        this.coinSet.remove(coinData);
    }
}
