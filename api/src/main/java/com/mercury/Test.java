package com.mercury;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class Test
{

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg)
    {
        String result = "Hello, " + msg;
        return Response.status(200).entity(result).build();
    }

}