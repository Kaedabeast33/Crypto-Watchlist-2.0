package com.kaeden.watchlist.Service;

import javax.transaction.Transactional;

public interface RunningThreadInterface {
    @Transactional
    public Runnable updateDB();
}
