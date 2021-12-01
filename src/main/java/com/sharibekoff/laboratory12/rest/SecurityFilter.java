package com.sharibekoff.laboratory12.rest;

import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String SECURED_USER_PREFIX = "user";
    private static final String SECURED_GROUP_PREFIX = "group";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_GROUP_PREFIX) || containerRequestContext.getUriInfo().getPath().contains(SECURED_USER_PREFIX)) {
            List<String> authHeader = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER);
            if (authHeader != null && authHeader.size() > 0) {
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                // Replace with advanced decoder
                String decodedString = new String(Base64.decodeBase64(authToken));
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                System.out.println(username + ":" + password);

                // user roles
                if ("USER".equals(username) && "USER".equals(password)) {
                    return;
                } else if ("ADMIN".equals(username) && "ADMIN".equals(password)) {
                    return;
                } else if ("MURATBEK".equals(username) && "MURATBEK".equals(password)) {
                    return;
                }
            }
            Response unauthorizedResponse = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("User cannot access the resource.")
                    .build();
            containerRequestContext.abortWith(unauthorizedResponse);
        }
    }
}
