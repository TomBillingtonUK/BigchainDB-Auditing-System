package code.model;

import code.helpers.DateHelper;

import java.time.LocalDateTime;


/*
    This class contains the object for an audit log.
 */
public class AuditLog
{
    //id is only set when a audit log has been retrieved
    private String id;

    private LocalDateTime date;

    private String system;

    private String username;

    private TransactionType transactionType;

    private String message;


    //Required for Jax RS
    public AuditLog()
    {
    }

    public AuditLog(LocalDateTime date, String system, String username, TransactionType transactionType, String message)
    {
        setDate(date);
        setSystem(system);
        setUsername(username);
        setTransactionType(transactionType);
        setMessage(message);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        try {
            this.date = DateHelper.stringToDate(date);
        } catch (Exception exception) {
            this.date = null;
        }
    }

    public void setDate(LocalDateTime date)
    {
        this.date = date;
    }

    public String getTimestamp()
    {
        return DateHelper.dateToString(this.date);
    }

    public void setTimestamp(String timestamp)
    {
        setDate(timestamp);
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public TransactionType getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType)
    {
        this.transactionType = transactionType;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
