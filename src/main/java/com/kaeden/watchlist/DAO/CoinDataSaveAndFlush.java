package com.kaeden.watchlist.DAO;

import com.kaeden.watchlist.Entities.CoinData;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class CoinDataSaveAndFlush implements CoinDataSaveAndFlushDao {
    private EntityManager entityManager;

    @Autowired
    public CoinDataSaveAndFlush(EntityManager theEntityManager){entityManager = theEntityManager;}
    @Override
    public boolean save(CoinData coinData) {
        Session currentSessoin = entityManager.unwrap(Session.class);
        currentSessoin.saveOrUpdate(coinData);


        return false;
    }
}
