package test;

import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.helpers.BigchainDBHelper;
import code.model.AuditLog;

public class TestUtilities
{
    public static AuditLog createAuditLogAndWaitForProcessing(AuditLog auditLog) throws AuditingException
    {
        auditLog.setId(BigchainDBHelper.createAuditLog(auditLog));

        boolean isReady = false;

        while (!isReady) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {

            }

            try {
                isReady = BigchainDBHelper.hasTransactionBeenProcessed(auditLog.getId());
            } catch (AuditingException exception) {
                if (exception.getExceptionType() != ExceptionType.TRANSACTION_NOT_READY) {
                    throw exception;
                }
            }
        }
        return auditLog;
    }
}
