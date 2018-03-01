package code.requests;

import code.exceptions.AuditingException;
import code.helpers.StatsHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
    This class is the entry point for retrieving stats
 */
@Path("/stats")
public class GetStatsRequest
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getStats()
    {
        try {
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(StatsHelper.getStats())
                    .build();

        } catch (AuditingException exception) {
            switch (exception.getExceptionType()) {
                case NO_DATA:
                    throw new WebApplicationException(exception.getErrorMessage(), 404);
                case SERVER_ERROR:
                default:
                    throw new WebApplicationException(exception.getErrorMessage(), 500);
            }
        }
    }

}
