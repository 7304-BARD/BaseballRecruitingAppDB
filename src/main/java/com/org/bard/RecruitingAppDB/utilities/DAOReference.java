package com.org.bard.RecruitingAppDB.utilities;

import com.org.bard.RecruitingAppDB.dao.AccountDAO;
import com.org.bard.RecruitingAppDB.dao.PlayerDAO;
import com.org.bard.RecruitingAppDB.dao.WatchlistDAO;

public class DAOReference {
    public static final AccountDAO ACCOUNT_DAO = new AccountDAO();
    public static final PlayerDAO PLAYER_DAO = new PlayerDAO();
    public static final WatchlistDAO WATCHLIST_DAO = new WatchlistDAO();
}
