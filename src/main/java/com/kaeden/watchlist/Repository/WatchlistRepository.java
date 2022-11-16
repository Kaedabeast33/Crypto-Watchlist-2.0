package com.kaeden.watchlist.Repository;

import com.kaeden.watchlist.Entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("watchlistRepository")
public interface WatchlistRepository extends JpaRepository<Watchlist,Long> {
    @Query(value = "SELECT * FROM watchlist WHERE user_id = :userId", nativeQuery = true)
    List<Watchlist> getWatchlistByUserId(@Param("userId") Long userId);
    @Modifying
    @Query(value = "DELETE from watchlist_coins w_c where w_c.watchlist_id = :watchlistId",nativeQuery = true)
    void customDeleteWatchlistCoinsById(@Param("watchlistId")Long watchlistId);

    @Modifying
    @Query(value =  "DELETE from watchlist w WHERE w.id = :watchlistId ",nativeQuery = true)
    void customDeleteWatchlistById(@Param("watchlistId")Long watchlistId);
}
