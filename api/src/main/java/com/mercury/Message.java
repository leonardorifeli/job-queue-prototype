package com.mercury;

import com.mercury.business.model.Send;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.json.simple.JSONObject;

@Path("/message")
@Produces("application/json")
public class Message {

    private JSONObject result;
    private String queueName = "mercury";

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg)
    {
        Send message = new Send();

        try {
            message.sendMessage(msg, this.queueName);
            return Response.status(200).entity(this.getResult(true, msg)).build();
        } catch (IOException e) {
            return Response.status(400).entity(this.getResult(false, e.getMessage())).build();
        } catch (TimeoutException e) {
            return Response.status(400).entity(this.getResult(false, e.getMessage())).build();
        }

        return Response.status(400).entity(this.getResult(false, "Error.")).build();
    }

    private JSONObject getResult(boolean status, String msg) {
        this.result = new JSONObject();

        this.result.put("isSuccess", status);
        this.result.put("message", msg);
        this.result.put("queueName", this.queueName);

        return this.result;
    }

}