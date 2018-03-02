package code.requests;

import code.exceptions.AuditingException;
import code.helpers.StatsHelper;
import code.model.Stats;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/*
    This class is the entry point for retrieving stats
 */
@Path("/stats")
public class GetStatsRequest
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static Stats getStats()
    {
        try {
            return StatsHelper.getStats();

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
