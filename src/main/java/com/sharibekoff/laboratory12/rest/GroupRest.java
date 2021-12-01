package com.sharibekoff.laboratory12.rest;

import com.sharibekoff.laboratory12.entity.GroupInSocialNetwork;
import com.sharibekoff.laboratory12.service.GroupService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("group")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "MURATBEK", "USER"})
public class GroupRest {

    @Inject
    GroupService groupService;

    @Path("new")
    @RolesAllowed("ADMIN")
    @POST
    public Response createGroup(GroupInSocialNetwork groupInSocialNetwork) {
        // api/v1/group/new
        groupService.createGroup(groupInSocialNetwork);
        return Response.ok().build();
    }

    @Path("update")
    @RolesAllowed("ADMIN")
    @PUT
    public Response updateGroup(GroupInSocialNetwork groupInSocialNetwork) {
        // api/v1/group/update
        groupService.updateGroup(groupInSocialNetwork);
        return Response.ok().build();
    }

    @Path("subscribers")
    @PermitAll
    @GET
    public Response findBySubscribersAmount(@QueryParam("amount") Integer amount) {
        return Response.ok(groupService.findBySubscribersAmount(amount)).build();
    }

    @Path("{id}")
    @PermitAll
    @GET
    public Response findById(@PathParam("id") Long id) {
        // api/v1/group/{id}
        return Response.ok(groupService.findById(id)).build();
    }

    @Path("list")
    @PermitAll
    @GET
    public Response findAll() {
        // api/v1/group/list
        return Response.ok(groupService.findAll()).build();
    }

    @Path("delete")
    @DenyAll
    @DELETE
    public Response deleteGroup(GroupInSocialNetwork groupInSocialNetwork) {
        // api/v1/group/delete
        return Response.ok(groupService.deleteGroup(groupInSocialNetwork)).build();
    }
}
