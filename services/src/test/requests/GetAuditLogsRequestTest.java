package test.requests;

import code.exceptions.AuditingException;
import code.model.AuditLog;
import code.model.SearchQuery;
import code.model.TransactionType;
import code.requests.GetAuditLogsRequest;
import org.junit.Assert;
import org.junit.Test;
import test.TestUtilities;

import javax.ws.rs.WebApplicationException;
import java.time.LocalDateTime;
import java.util.List;

public class GetAuditLogsRequestTest
{
    @Test
    public void getAuditLogsRequestTest_valid() throws AuditingException
    {
        //Setup
        AuditLog auditLog = new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A");
        TestUtilities.createAuditLogAndWaitForProcessing(auditLog);

        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUsername("User A");
        searchQuery.setSystem("System A");

        //Execute
        List<AuditLog> logs = (List<AuditLog>)GetAuditLogsRequest.getAuditLogs(searchQuery).getEntity();

        //Test
        logs.forEach(auditLog1 -> {
            Assert.assertEquals(auditLog1.getSystem(), auditLog.getSystem());
            Assert.assertEquals(auditLog1.getUsername(), auditLog.getUsername());
        });
    }

    @Test
    public void getAuditLogsRequestTest_invalidObject()
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();

        try {
            GetAuditLogsRequest.getAuditLogs(searchQuery);
        } catch (WebApplicationException exception) {
            Assert.assertEquals(exception.getResponse().getStatus(), 400);
        }
    }

    @Test
    public void getAuditLogsRequestTest_invalidNoData()
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUsername("I am an invalid username");

        try {
            GetAuditLogsRequest.getAuditLogs(searchQuery);
        } catch (WebApplicationException exception) {
            Assert.assertEquals(exception.getResponse().getStatus(), 404);
        }
    }
}
