package com.example.userservice.rest;

import com.example.userservice.ejb.UserBean;
import com.example.userservice.entity.User;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserBean userBean;

    @POST
    public Response addUser(User user) {
        userBean.addUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int userId) {
        userBean.deleteUser(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int userId, User updatedUser) {
        updatedUser.setUserId(userId);
        userBean.updateUser(updatedUser);
        return Response.ok(updatedUser).build();
    }

    @GET
    public Response getAllUsers() {
        List<User> users = userBean.getAllUsers();
        return Response.ok(users).build();
    }

    @GET
    @Path("/admins")
    public Response getAllAdmins() {
        List<User> admins = userBean.getAllAdmins();
        return Response.ok(admins).build();
    }

    @GET
    @Path("/instructors")
    public Response getAllInstructors() {
        List<User> instructors = userBean.getAllInstructors();
        return Response.ok(instructors).build();
    }

    @GET
    @Path("/students")
    public Response getAllStudents() {
        List<User> students = userBean.getAllStudents();
        return Response.ok(students).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int userId) {
        User user = userBean.getUserById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @GET
    @Path("/email")
    public Response getUserByEmail(@QueryParam("email") String email) {
        User user = userBean.getUserByEmail(email);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }
}