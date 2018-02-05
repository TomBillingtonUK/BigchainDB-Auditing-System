package code.requests;

import code.exceptions.AuditingException;
import code.helpers.SearchHelper;
import code.model.AuditLog;
import code.model.SearchQuery;
import code.validators.GetAuditLogsValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
    This class is the entry point for getting audit logs
 */
@Path("/get")
public class GetAuditLogsRequest
{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static List<AuditLog> getAuditLogs(SearchQuery query)
    {
        try {
            //Validate
            GetAuditLogsValidator.validate(query);

            //Get logs
            return SearchHelper.search(query);
        } catch (AuditingException exception) {
            switch (exception.getExceptionType()) {
                case VALIDATION_ERROR:
                    throw new WebApplicationException(exception.getErrorMessage(), 400);
                case NO_DATA:
                    throw new WebApplicationException(exception.getErrorMessage(), 404);
                case SERVER_ERROR:
                default:
                    throw new WebApplicationException(exception.getErrorMessage(), 500);
            }
        }
    }
}
