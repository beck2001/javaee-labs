package com.sharibekoff.laboratory12.rest;

import com.sharibekoff.laboratory12.entity.User;
import com.sharibekoff.laboratory12.service.UserService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "USER", "MURATBEK"})
public class UserRest {

    @Inject
    UserService userService;

    @Path("new")
    @RolesAllowed("ADMIN")
    @POST
    public Response createUser(User user) {
        // api/v1/user/new
        userService.createUser(user);
        return Response.ok().build();
    }

    @Path("update")
    @RolesAllowed("ADMIN")
    @PUT
    public Response updateUser(User user) {
        // api/v1/user/update
        userService.updateUser(user);
        return Response.ok().build();
    }

    @Path("{id}")
    @PermitAll
    @GET
    public Response findById(@PathParam("id") Long id) {
        // api/v1/user/{id}
        return Response.ok(userService.findById(id)).build();
    }

    @Path("age")
    @PermitAll
    @GET
    public Response findByAge(@QueryParam("age") Integer age) {
        return Response.ok(userService.findByAge(age)).build();
    }

    @Path("list")
    @PermitAll
    @GET
    public Response findAll() {
        // api/v1/user/list
        return Response.ok(userService.findAll()).build();
    }

    @Path("delete")
    @DenyAll
    @DELETE
    public Response deleteUser(User user) {
        // api/v1/user/delete
        return Response.ok(userService.deleteUser(user)).build();
    }

    // rest endpoint to update user's status
    @Path("status")
    @RolesAllowed("ADMIN")
    @POST
    public Response changeStatus(@QueryParam("id") Long id, @QueryParam("status") String status) {
        User user = userService.findById(id);
        user.setStatus(status);
        userService.updateUser(user);
        return Response.ok(user).build();
    }
}
