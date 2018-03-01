package code.requests;

import code.exceptions.AuditingException;
import code.helpers.SearchHelper;
import code.model.SearchQuery;
import code.validators.GetAuditLogsValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
    This class is the entry point for getting audit logs
 */
@Path("/get")
public class GetAuditLogsRequest
{

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response getAuditLogs(SearchQuery query)
    {
        try {
            //Validate
            GetAuditLogsValidator.validate(query);

            //Get logs
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(SearchHelper.search(query))
                    .build();

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
