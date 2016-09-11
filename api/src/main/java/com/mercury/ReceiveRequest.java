package com.mercury;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.mercury.business.model.Receive;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.lang.InterruptedException;

@Path("/receive")
public class ReceiveRequest
{

    @GET
    @Path("")
    public Response printMessage()
    {
        Receive receive = new Receive();
/*
        try {
            //receive.main(job);
        } catch (InterruptedException e) {
            
        } catch (IOException e) {

        } catch (TimeoutException e) {

        }*/

        return Response.status(200).entity("Receiving job queue ").build();
    }

}