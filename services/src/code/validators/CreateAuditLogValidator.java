package code.validators;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.AuditLog;

import java.util.ArrayList;
import java.util.List;

/*
    This class is used to validate an Audit Log during the Create Audit Log Request
 */
public class CreateAuditLogValidator
{
    /*
        Entry point for validation
     */
    public static void Validate(AuditLog auditLog) throws AuditingException
    {
        List<String> validationErrors = new ArrayList<>();

        if (auditLog.getDate() == null) {
            validationErrors.add(Constants.NO_DATE_VALIDATION_MESSAGE);
        }

        if (auditLog.getSystem() == null || auditLog.getSystem().isEmpty()) {
            validationErrors.add(Constants.NO_SYSTEM_VALIDATION_MESSAGE);
        }

        if (auditLog.getUsername() == null || auditLog.getUsername().isEmpty()) {
            validationErrors.add(Constants.NO_USERNAME_VALIDATION_MESSAGE);
        }

        if (auditLog.getTransactionType() == null) {
            validationErrors.add(Constants.NO_TYPE_VALIDATION_MESSAGE);
        }

        if (auditLog.getMessage() == null || auditLog.getMessage().isEmpty()) {
            validationErrors.add(Constants.NO_MESSAGE_VALIDATION_MESSAGE);
        }

        //Deal with any errors
        if (!validationErrors.isEmpty()) {
            StringBuilder validationMessagesStringBuilder = new StringBuilder();

            validationErrors.stream().forEach((message) -> validationMessagesStringBuilder.append(message));

            throw new AuditingException(ExceptionType.VALIDATION_ERROR,
                    String.format(Constants.VALIDATION_ERROR_MESSAGE, validationMessagesStringBuilder.toString()));
        }
    }
}
