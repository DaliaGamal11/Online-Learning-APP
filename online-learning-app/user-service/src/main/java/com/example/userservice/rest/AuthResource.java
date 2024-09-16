package com.example.userservice.rest;

import com.example.userservice.ejb.UserSessionBean;
import com.example.userservice.entity.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    private UserSessionBean userSessionBean;

    @POST
    @Path("/login")
    public Response login(@FormParam("email") String email, @FormParam("password") String password) {
        boolean success = userSessionBean.login(email, password);
        if (success) {
            return Response.ok(userSessionBean.getLoggedInUser()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
        }
    }

    @POST
    @Path("/logout")
    public Response logout() {
        userSessionBean.logout();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/current")
    public Response getCurrentUser() {
        if (userSessionBean.isLoggedIn()) {
            return Response.ok(userSessionBean.getLoggedInUser()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("No user is currently logged in").build();
        }
    }
}
