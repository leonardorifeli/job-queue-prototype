package com.mercury;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.mercury.model.Send;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Path("/message")
public class Message
{

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg)
    {
        Send message = new Send();

        try {
            message.main(msg);
        } catch (IOException e) {

        } catch (TimeoutException e){

        }

        return Response.status(200).entity("Send to queue.").build();
    }

}