package code.exceptions;


/*
    Custom Exception so we can provide custom error messages and easier http status code error definitions
 */
public class AuditingException extends Exception
{

    private String errorMessage;

    private Exception originalException;

    private ExceptionType exceptionType;

    public AuditingException(Exception originalException, ExceptionType type, String errorMessage)
    {
        this(type, errorMessage);
        this.originalException = originalException;
    }

    public AuditingException(ExceptionType type, String errorMessage)
    {
        this.exceptionType = type;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public Exception getOriginalException()
    {
        return originalException;
    }

    public void setOriginalException(Exception originalException)
    {
        this.originalException = originalException;
    }

    public ExceptionType getExceptionType()
    {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType)
    {
        this.exceptionType = exceptionType;
    }
}
