package com.org.bard.RecruitingAppDB.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.org.bard.RecruitingAppDB.entities.Account;

import static com.org.bard.RecruitingAppDB.utilities.DAOReference.ACCOUNT_DAO;
import static com.org.bard.RecruitingAppDB.utilities.DAOReference.PLAYER_DAO;
import static com.org.bard.RecruitingAppDB.utilities.IDCounter.getNewUid;

@Path("/dbm")
public class DBMEndpoint {

    @POST
    @Path("/init")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTables() {
        String result = ACCOUNT_DAO.createTable();
        if (result.split(" ", 2)[0] == "Successfully"){
            result = PLAYER_DAO.createTable();
            if (result.split(" ", 2)[0] == "Successfully") {
                return Response.status(200).entity(result).build();
            } else {
                return Response.status(500).entity(result).build();
            }
        } else {
            return Response.status(500).entity(result).build();
        }
    }
}