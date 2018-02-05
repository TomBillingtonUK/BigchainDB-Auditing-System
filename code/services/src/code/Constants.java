package code;

public class Constants
{

    //Constants for the Bigchain DB Server Instance
    //TODO - Move to an external properties file
    public static final String BIGCHAIN_DB_BASE_URL = "http://127.0.0.1:9984";

    // Constants for the Bigchain DB Asset Data Names
    public static final String DATE_FIELD_NAME = "date";
    public static final String SYSTEM_FIELD_NAME = "system";
    public static final String USERNAME_FIELD_NAME = "username";
    public static final String TYPE_FIELD_NAME = "type";
    public static final String MESSAGE_FIELD_NAME = "message";

    //Constants for validation messages
    public static final String NO_DATE_VALIDATION_MESSAGE = " No Date Provided.";
    public static final String NO_TYPE_VALIDATION_MESSAGE = " No Audit Type Provided.";
    public static final String NO_USERNAME_VALIDATION_MESSAGE = " No Username Provided.";
    public static final String NO_SYSTEM_VALIDATION_MESSAGE = " No System Provided.";
    public static final String NO_MESSAGE_VALIDATION_MESSAGE = " No Log Message Provided";

    // Constants for error messages
    public static final String GET_STATUS_ERROR_MESSAGE = "Error when checking transaction status for id %s , with the error: %s";
    public static final String TRANSACTION_NOT_READY_ERROR_MESSAGE = "Transaction Id %s is still being processed";
    public static final String TRANSACTION_NOT_FOUND_ERROR_MESSAGE = "Transaction Id %s can't be found";
    public static final String GET_TRANSACTION_ERROR_MESSAGE = "Error when attempting to get transaction with a id %s, with the error: %s";
    public static final String CREATE_TRANSACTION_ERROR_MESSAGE = "Error when trying to create transaction, with the error %s";
    public static final String VALIDATION_ERROR_MESSAGE = "Data Validation Errors: %s";
    public static final String NO_TRANSACTIONS_FOUND_ERROR_MESSAGE = "No transactions were found";
    public static final String GET_TRANSACTIONS_ERROR_MESSAGE = "Error when trying to find transaction with term %s . with the error %s";
    public static final String SEARCH_QUERY_EMPTY_ERROR_MESSAGE = "No search query has been provided";
    public static final String NO_FROM_DATE_ERROR_MESSAGE = "A valid from date must be provided if a to date is provided";
    public static final String TO_DATE_BEFORE_FROM_DATE_ERROR_MESSAGE = "The To date must be after the From Date";
}
