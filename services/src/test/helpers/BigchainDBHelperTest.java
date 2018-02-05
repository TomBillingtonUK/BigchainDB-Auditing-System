package test.helpers;


import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.helpers.BigchainDBHelper;
import code.helpers.DateHelper;
import code.model.AuditLog;
import code.model.TransactionType;
import org.junit.Assert;
import org.junit.Test;
import test.TestUtilities;

import java.time.LocalDateTime;
import java.util.List;

public class BigchainDBHelperTest
{

    @Test
    public void createAuditLog_Valid() throws AuditingException
    {
        //Execute
        String id = BigchainDBHelper.createAuditLog(
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A"));

        //Validate
        Assert.assertNotNull(id);
    }

    @Test
    public void getAuditLog_Valid() throws AuditingException
    {
        //Setup
        AuditLog originalAuditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A");
        String id = TestUtilities.createAuditLogAndWaitForProcessing(originalAuditLog).getId();

        //Execute
        AuditLog newAuditLog = BigchainDBHelper.getAuditLogById(id);

        //Validate
        Assert.assertEquals(newAuditLog.getId(), id);
        Assert.assertEquals(newAuditLog.getDate(), originalAuditLog.getDate());
        Assert.assertEquals(newAuditLog.getSystem(), originalAuditLog.getSystem());
        Assert.assertEquals(newAuditLog.getUsername(), originalAuditLog.getUsername());
        Assert.assertEquals(newAuditLog.getTransactionType(), originalAuditLog.getTransactionType());
        Assert.assertEquals(newAuditLog.getMessage(), originalAuditLog.getMessage());
    }

    @Test
    public void getAuditLog_Invalid_Id()
    {
        String id = "testing 123";
        try {
            BigchainDBHelper.getAuditLogById(id);
        } catch (AuditingException exception) {
            Assert.assertEquals(ExceptionType.NO_DATA, exception.getExceptionType());
            Assert.assertNotNull(exception.getOriginalException());
            Assert.assertEquals(String.format(Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE,
                    id,
                    exception.getOriginalException().getMessage()),
                    exception.getErrorMessage());
        }
    }

    @Test
    public void getStatus_Invalid_Id()
    {
        String id = "testing 123";
        try {
            BigchainDBHelper.hasTransactionBeenProcessed(id);
        } catch (AuditingException exception) {
            Assert.assertEquals(ExceptionType.NO_DATA, exception.getExceptionType());
            Assert.assertNotNull(exception.getOriginalException());
            Assert.assertEquals(String.format(Constants.TRANSACTION_NOT_FOUND_ERROR_MESSAGE,
                    id,
                    exception.getOriginalException().getMessage()),
                    exception.getErrorMessage());
        }
    }

    @Test
    public void getTransactions_Valid() throws AuditingException
    {
        //Setup
        AuditLog auditLog = TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A"));
        String dateTimeString = "\"" + DateHelper.dateToString(auditLog.getDate()) + "\"";

        //Execute
        List<AuditLog> auditLogList = BigchainDBHelper.getAuditLogs(dateTimeString);

        //Verify
        Assert.assertTrue(auditLogList.size() == 1);
        Assert.assertEquals(auditLogList.get(0).getDate(), auditLog.getDate());
    }

    @Test
    public void getTransaction_Invalid_SearchTerm()
    {
        //Setup
        String invalidDate = "\"1900-01-01T00:00:00\"";

        try {
            //Execute
            BigchainDBHelper.getAuditLogs(invalidDate);
        } catch (AuditingException exception) {
            //Verify
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.NO_DATA);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(), Constants.NO_TRANSACTIONS_FOUND_ERROR_MESSAGE);
        }
    }


}
