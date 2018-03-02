package test.requests;

import code.exceptions.AuditingException;
import code.model.AuditLog;
import code.model.Stats;
import code.model.TransactionType;
import code.requests.GetStatsRequest;
import org.junit.Assert;
import org.junit.Test;
import test.TestUtilities;

import java.time.LocalDateTime;

public class GetStatsRequestTest
{
    @Test
    public void getStatsRequestTests_valid() throws AuditingException
    {
        //Setup
        AuditLog auditLog = new AuditLog(LocalDateTime.now(),
                "statsHelperTest-System",
                "statsHelperTest-Username",
                TransactionType.LOGIN,
                "statsHelperTest-Message-Failed");
        TestUtilities.createAuditLogAndWaitForProcessing(auditLog);

        //Execute
        Stats stats = GetStatsRequest.getStats();

        //Test
        Assert.assertTrue(stats.getSystemLogs().size() > 0);
        Assert.assertTrue(stats.getFailedLogins().size() > 0);
        Assert.assertTrue(stats.getUserLogs().size() > 0);
    }
}
