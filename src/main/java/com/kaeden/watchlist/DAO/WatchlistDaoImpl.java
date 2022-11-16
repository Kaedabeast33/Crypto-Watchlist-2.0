package com.kaeden.watchlist.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class WatchlistDaoImpl implements WatchlistDAO {
    @Autowired
    private EntityManager entityManager;


    @Override
    public List getAllWatchlistByUserId(Long userId){

       return entityManager.createQuery(
                "SELECT c FROM watchlist c WHERE c.id = 1"
        ).getResultList();


    }


}
