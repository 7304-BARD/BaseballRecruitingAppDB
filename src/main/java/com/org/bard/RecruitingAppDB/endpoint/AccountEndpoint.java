package com.org.bard.RecruitingAppDB.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.org.bard.RecruitingAppDB.entities.Account;

import static com.org.bard.RecruitingAppDB.utilities.DAOReference.ACCOUNT_DAO;
import static com.org.bard.RecruitingAppDB.utilities.IDCounter.getNewUid;

@Path("/account")
public class AccountEndpoint {

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(@FormParam("email") String email,
                                  @FormParam("username") String username,
                                  @FormParam("password") String password,
                                  @FormParam("organization") String organization) {
	    Account account = new Account(email, username, password, organization);
	    account.setUid(getNewUid());
		String result = ACCOUNT_DAO.addAccount(account);
		if (result.split(" ", 2)[0] == "Successfully"){
			return Response.status(201).entity(result).build();
		} else {
			return Response.status(500).entity(result).build();
		}
	}

}