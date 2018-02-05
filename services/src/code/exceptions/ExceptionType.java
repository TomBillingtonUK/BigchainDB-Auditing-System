package code.exceptions;

/*
    This enum contains the types of exceptions a Auditing Exception can be.
    This allows us to more easily provide error status codes back to end users of the api
 */
public enum ExceptionType
{
    VALIDATION_ERROR,
    SERVER_ERROR,
    TRANSACTION_NOT_READY,
    NO_DATA
}