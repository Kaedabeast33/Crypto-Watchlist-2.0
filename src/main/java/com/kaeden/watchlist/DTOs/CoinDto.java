package com.kaeden.watchlist.DTOs;

import com.kaeden.watchlist.Entities.CoinData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class CoinDto implements Serializable {
    private Long id;
    private String coinName;
    private  Long price;

    //Getters and Setters
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCoinName() {
//        return coinName;
//    }
//    public void setCoinName(String coinName) {
//        this.coinName = coinName;
//    }

//    public Long getPrice() {
//        return price;
//    }
//    public void setPrice(Long price) {
//        this.price = price;
//    }

    //Constructor
//    public CoinDto(CoinData coin){
//        if (coin.getId()!=null){
//            this.id=coin.getId();
//        }if (coin.getCoinName()!=null){
//            this.coinName= coin.getCoinName();
//        }if(coin.getPrice()!=null){
//            this.price = coin.getPrice();
//        }
//    }
    public CoinDto(){

    }
}
