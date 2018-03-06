package com.org.bard.RecruitingAppDB.endpoint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.org.bard.RecruitingAppDB.entities.Player;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.org.bard.RecruitingAppDB.utilities.DAOReference.WATCHLIST_DAO;

@Path("/watchlist")
public class WatchlistEndpoint {

	Gson gson = new Gson();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPlayerToUserWatchlist(@QueryParam("uid") long uid, final String pgids) {
		Type listType = new TypeToken<ArrayList<Long>>(){}.getType();
		List<Long> pgidsList = new Gson().fromJson(pgids, listType);
		String result = WATCHLIST_DAO.addToWatchlist(uid, pgidsList.toArray(new Long[pgidsList.size()]));
		if (result.split(" ", 2)[0] != "Successfully") {
			return Response.status(500).entity(result).build();
		}

		if (pgidsList.size() == 0) {
			return Response.status(204).entity("No players needed to be added to watchlist").build();
		}
		else if (pgidsList.size() == 1) {
			return Response.status(201).entity("Successfully added player to watchlist").build();
		} else {
			return Response.status(201).entity("Successfully added all players to watchlist").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Player[] getUserWatchlist(@QueryParam("uid") long uid) {
		ArrayList<Player> watchlist = WATCHLIST_DAO.getWatchlist(uid);
		if (watchlist == null) {
			return null;
		}

		return watchlist.toArray(new Player[watchlist.size()]);
	}



}