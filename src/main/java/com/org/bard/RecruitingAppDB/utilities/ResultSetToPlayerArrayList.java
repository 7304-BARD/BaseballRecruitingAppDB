package com.org.bard.RecruitingAppDB.utilities;

import com.org.bard.RecruitingAppDB.entities.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public final class ResultSetToPlayerArrayList {
    public static ArrayList<Player> convert(ResultSet rs, ArrayList<Player> players) throws SQLException {
        while (rs.next()) {
            Player player = new Player();
            player.pgid = rs.getLong("pgid");
            player.name = rs.getString("name");
            player.year = rs.getShort("year");
            player.pos1 = rs.getString("pos1");
            player.pos2 = rs.getString("pos2");
            player.age = rs.getShort("age");
            player.height = rs.getFloat("height");
            player.weight = rs.getFloat("weight");
            player.bt = rs.getString("bt");
            player.hs = rs.getString("hs");
            player.town = rs.getString("town");
            player.team_summer = rs.getString("team_summer");
            player.team_fall = rs.getString("team_fall");
            players.add(player);
        }
        return players;
    }
}
