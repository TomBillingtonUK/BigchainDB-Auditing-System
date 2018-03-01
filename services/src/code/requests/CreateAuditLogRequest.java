package code.requests;

import code.exceptions.AuditingException;
import code.helpers.BigchainDBHelper;
import code.model.AuditLog;
import code.validators.CreateAuditLogValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/*
    This class is the entry point for creating an Audit Log
 */
@Path("/create")
public class CreateAuditLogRequest
{
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response createAuditLog(AuditLog auditLog)
    {
        try {
            //Validate the data provided
            CreateAuditLogValidator.Validate(auditLog);

            // Create the Audit Log
            String transactionId = BigchainDBHelper.createAuditLog(auditLog);

            //Send Success Response
            return Response
                    .status(200)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .entity(ResponseBuilder(transactionId))
                    .build();

        } catch (AuditingException exception) {
            switch (exception.getExceptionType()) {
                case VALIDATION_ERROR:
                    throw new WebApplicationException(exception.getErrorMessage(), 400);
                case SERVER_ERROR:
                default:
                    throw new WebApplicationException(exception.getErrorMessage(), 500);
            }
        }
    }

    private static String ResponseBuilder(String transactionId)
    {
        return new StringBuilder()
                .append("{ \"transactionId\" : \"")
                .append(transactionId)
                .append("\"}")
                .toString();
    }

}
