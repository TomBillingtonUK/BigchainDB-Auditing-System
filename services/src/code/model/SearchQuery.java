package code.model;

import code.helpers.DateHelper;

import java.time.LocalDateTime;

/* This class is used for providing a search query to retrieve audit log entries */
public class SearchQuery
{
    private String id;

    private LocalDateTime dateTo;

    private LocalDateTime dateFrom;

    private String system;

    private String username;

    private TransactionType transactionType;


    //Required for Jax RS
    public SearchQuery()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public LocalDateTime getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(String dateTo)
    {
        this.dateFrom = DateHelper.stringToDate(dateTo);
    }

    public void setDateFrom(LocalDateTime dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(String dateTo)
    {
        this.dateTo = DateHelper.stringToDate(dateTo);
    }

    public void setDateTo(LocalDateTime dateTo)
    {
        this.dateTo = dateTo;
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

    //Used for Json conversion
    public String getTimestamp_Start()
    {
        return DateHelper.dateToString(dateFrom);
    }

    public void setTimestamp_Start(String timestampStart)
    {
        this.dateFrom = DateHelper.stringToDate(timestampStart);
    }

    public String getTimestamp_End()
    {
        return DateHelper.dateToString(dateTo);
    }

    public void setTimestamp_End(String timestampEnd)
    {
        dateTo = DateHelper.stringToDate(timestampEnd);
    }
}
