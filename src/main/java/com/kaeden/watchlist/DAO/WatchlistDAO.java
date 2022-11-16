package com.kaeden.watchlist.DAO;

import com.kaeden.watchlist.Entities.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


public interface WatchlistDAO {



    List<Watchlist> getAllWatchlistByUserId(Long userId);
}
