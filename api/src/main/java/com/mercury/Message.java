package com.mercury;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import com.mercury.business.model.Send;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.json.simple.JSONObject;

@Path("/message")
@Produces("application/json")
public class Message
{
    private JSONObject result = new JSONObject();
    private String queueName = "mercury";

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg)
    {
        Send message = new Send();

        try {
            message.sendMessage(msg, this.queueName);
            this.result = this.getResult(true, msg);
        } catch (IOException e) {
            this.result = this.getResult(false, e.getMessage());
            return Response.status(400).entity(this.result).build();
        } catch (TimeoutException e) {
            this.result = this.getResult(false, e.getMessage());
            return Response.status(400).entity(this.result).build();
        }

        return Response.status(200).entity(this.result).build();
    }

    private JSONObject getResult(boolean status, String msg) {
        this.result.put("isSuccess", status);
        this.result.put("message", msg);
        this.result.put("queueName", this.queueName);

        return this.result;
    }

}