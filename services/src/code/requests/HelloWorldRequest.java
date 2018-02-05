package code.requests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/*
    This api is used to test that the service is up and running
 */
@Path("/hello")
public class HelloWorldRequest
{
    @GET
    public static String getMessage()
    {
        return "Hello";
    }
}
