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
@Produces("application/json")
public class ReceiveRequest {

    private JSONObject result = new JSONObject();
    private String queueName = "mercury";

    @GET
    @Path("")
    public Response printMessage() {
        Receive receive = new Receive();

        try {

        } catch (InterruptedException e) {
            
        } catch (IOException e) {

        } catch (TimeoutException e) {

        }

        return Response.status(200).entity("Receiving job queue ").build();
    }

    private JSONObject getResult(boolean status, String msg) {
        this.result.put("isSuccess", status);
        this.result.put("message", msg);
        this.result.put("queueName", this.queueName);

        return this.result;
    }

}