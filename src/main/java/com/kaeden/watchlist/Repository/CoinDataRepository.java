package com.kaeden.watchlist.Repository;

import com.kaeden.watchlist.Entities.CoinData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinDataRepository extends JpaRepository<CoinData,String> {

}
