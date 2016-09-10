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

    private String errorMessage;

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg)
    {
        Send message = new Send();

        try {
            message.sendMessage(msg, "mercury");
        } catch (IOException e) {
            this.errorMessage = "Error of IO";
            return Response.status(400).entity(this.errorMessage).build();
        } catch (TimeoutException e){
            this.errorMessage = "Error of Timeout";
            return Response.status(400).entity(this.errorMessage).build();
        }

        return Response.status(200).entity("Sended to queue "+ queue +".").build();
    }

}