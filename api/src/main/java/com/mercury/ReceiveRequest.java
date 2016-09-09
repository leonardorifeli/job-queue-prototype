package com.mercury;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import com.mercury.model.Receive;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.lang.InterruptedException;

@Path("/receive")
public class ReceiveRequest
{

    @GET
    @Path("/{job}")
    public Response printMessage(@PathParam("job") String job)
    {
        Receive receive = new Receive();

        try {
            receive.main(job);
        } catch (InterruptedException e) {
            
        } catch (IOException e) {

        } catch (TimeoutException e) {

        }

        return Response.status(200).entity("Receiving job queue "+job).build();
    }

}