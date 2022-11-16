package com.kaeden.watchlist.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="coins")
public class CoinData {
    @Id
    private String id;
    @Column
    private String symbol;
    @Column
    private String name;
    @Column
    private String image;
    @Column
    private String current_price;
    @Column
    private String market_cap;
    @Column
    private String market_cap_rank;
    @Column
    private String total_volume;
    @Column
    private String high_24h;
    @Column
    private String low_24h;
    @Column
    private String price_change_24h;
    @Column
    private String price_change_percentage_24h;
    @JsonIgnore
    @ManyToMany(mappedBy = "coinSet",cascade = CascadeType.ALL)
    private Set<Watchlist> watchlistSet = new HashSet<>();
    //GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getMarket_cap_rank() {
        return market_cap_rank;
    }

    public void setMarket_cap_rank(String market_cap_rank) {
        this.market_cap_rank = market_cap_rank;
    }

    public String getTotal_volume() {
        return total_volume;
    }

    public void setTotal_volume(String total_volume) {
        this.total_volume = total_volume;
    }

    public String getHigh_24h() {
        return high_24h;
    }

    public void setHigh_24h(String high_24h) {
        this.high_24h = high_24h;
    }

    public String getLow_24h() {
        return low_24h;
    }

    public void setLow_24h(String low_24h) {
        this.low_24h = low_24h;
    }

    public String getPrice_change_24h() {
        return price_change_24h;
    }

    public void setPrice_change_24h(String price_change_24h) {
        this.price_change_24h = price_change_24h;
    }

    public String getPrice_change_percentage_24h() {
        return price_change_percentage_24h;
    }

    public void setPrice_change_percentage_24h(String price_change_percentage_24h) {
        this.price_change_percentage_24h = price_change_percentage_24h;
    }
}
