package com.org.bard.RecruitingAppDB.endpoint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.org.bard.RecruitingAppDB.entities.Player;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.org.bard.RecruitingAppDB.utilities.DAOReference.PLAYER_DAO;

@Path("/players")
public class PlayerEndpoint {

	Gson gson = new Gson();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPlayerToDatabase(final String players) {
		Type listType = new TypeToken<ArrayList<Player>>(){}.getType();
		List<Player> playersList = new Gson().fromJson(players, listType);
		PLAYER_DAO.addPlayer(playersList.toArray(new Player[playersList.size()]));

		if (playersList.size() == 0) {
			return Response.status(204).entity("No players needed to be added").build();
		}
		else if (playersList.size() == 1) {
			return Response.status(201).entity("Successfully added player").build();
		} else {
			return Response.status(201).entity("Successfully added all players").build();
		}
	}

}