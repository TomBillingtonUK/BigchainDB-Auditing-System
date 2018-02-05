package test.requests;

import code.model.AuditLog;
import code.model.TransactionType;
import code.requests.CreateAuditLogRequest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;

public class CreateAuditLogRequestTest
{
    @Test
    public void createAuditLog_ValidObject()
    {
        //Setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A");

        //Execute
        String transactionId = CreateAuditLogRequest.createAuditLog(auditLog);

        //Verify
        Assert.assertNotNull(transactionId);
        Assert.assertTrue(!transactionId.isEmpty());
    }

    @Test
    public void createAuditLog_InvalidObject()
    {
        try {
            //Setup
            AuditLog auditLog = new AuditLog();

            //Execute
            CreateAuditLogRequest.createAuditLog(auditLog);
        } catch (WebApplicationException exception) {
            Assert.assertEquals(400, exception.getResponse().getStatus());
        }


    }

}
