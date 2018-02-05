package test.validators;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.AuditLog;
import code.model.TransactionType;
import code.validators.CreateAuditLogValidator;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class CreateAuditLogValidatorTest
{

    @Test
    public void validationTest_Valid() throws AuditingException
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A");

        //execute
        CreateAuditLogValidator.Validate(auditLog);
    }

    @Test
    public void validationTest_Invalid_MultipleIssues()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", null, null, null);

        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertTrue(exception.getErrorMessage().contains(Constants.NO_MESSAGE_VALIDATION_MESSAGE));
            Assert.assertTrue(exception.getErrorMessage().contains(Constants.NO_TYPE_VALIDATION_MESSAGE));
            Assert.assertTrue(exception.getErrorMessage().contains(Constants.NO_USERNAME_VALIDATION_MESSAGE));
        }

    }

    @Test
    public void validationTest_Invalid_NullDate()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(null, "System A", "User A", TransactionType.CREATE, "Message A");

        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(),
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, Constants.NO_DATE_VALIDATION_MESSAGE));
        }
    }

    @Test
    public void validationTest_Invalid_NullSystem()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), null, "User A", TransactionType.CREATE, "Message A");
        //execute
        validateSystemField(auditLog);
    }

    @Test
    public void validationTest_Invalid_EmptyStringSystem()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "", "User A", TransactionType.CREATE, "Message A");

        //execute
        validateSystemField(auditLog);

    }

    @Test
    public void validationTest_Invalid_NullUsername()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", null, TransactionType.CREATE, "Message A");

        //execute
        validateUsernameField(auditLog);
    }

    @Test
    public void validationTest_Invalid_EmptyStringUsername()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "", TransactionType.CREATE, "Message A");
        //execute
        validateUsernameField(auditLog);
    }

    @Test
    public void validationTest_Invalid_NullType()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", null, "Message A");

        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(),
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, Constants.NO_TYPE_VALIDATION_MESSAGE));
        }
    }

    @Test
    public void validationTest_Invalid_NullMessage()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, null);

        //execute
        validateMessageField(auditLog);
    }

    @Test
    public void validationTest_Invalid_EmptyStringMessage()
    {
        //setup
        AuditLog auditLog =
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "");

        //execute
        validateMessageField(auditLog);
    }

    private void validateSystemField(AuditLog auditLog)
    {
        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(),
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, Constants.NO_SYSTEM_VALIDATION_MESSAGE));
        }
    }

    private void validateUsernameField(AuditLog auditLog)
    {
        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(),
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, Constants.NO_USERNAME_VALIDATION_MESSAGE));
        }
    }

    private void validateMessageField(AuditLog auditLog)
    {
        try {
            CreateAuditLogValidator.Validate(auditLog);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(),
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, Constants.NO_MESSAGE_VALIDATION_MESSAGE));
        }
    }
}
