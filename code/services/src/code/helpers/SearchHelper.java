package code.helpers;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.AuditLog;
import code.model.SearchQuery;
import code.model.TransactionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    This class assists with processing search requests
 */
public class SearchHelper
{

    public static List<AuditLog> search(SearchQuery searchQuery) throws AuditingException
    {
        List<AuditLog> auditLogs = null;

        if (searchQuery.getDateFrom() != null) {
            auditLogs = searchByDate(searchQuery);
        } else if (searchQuery.getUsername() != null && !searchQuery.getUsername().isEmpty()) {
            auditLogs = searchByUsername(searchQuery);
        } else if (searchQuery.getSystem() != null && !searchQuery.getSystem().isEmpty()) {
            auditLogs = searchBySystem(searchQuery);
        } else if (searchQuery.getTransactionType() != null) {
            auditLogs = searchByType(searchQuery);
        }

        if (auditLogs == null || auditLogs.isEmpty()) {
            throw new AuditingException(ExceptionType.NO_DATA, Constants.NO_TRANSACTIONS_FOUND_ERROR_MESSAGE);
        }

        return auditLogs;
    }


    //Search Methods

    private static List<AuditLog> searchByDate(SearchQuery searchQuery) throws AuditingException
    {
        List<AuditLog> results = new ArrayList<>();
        LocalDate start = searchQuery.getDateFrom().toLocalDate();
        LocalDate end;

        //Check if Date To is null
        if (searchQuery.getDateTo() == null) {
            searchQuery.setDateTo(LocalDateTime.now());
        }

        end = searchQuery.getDateTo().toLocalDate();

        //Search
        do {
            String queryString = "\"" + start.format(DateTimeFormatter.ISO_DATE) + "\"";

            try {
                results.addAll(BigchainDBHelper.getAuditLogs(queryString));
            } catch (AuditingException exception) {
                //We want to avoid the no data exception in case a day has no logs
                if (exception.getExceptionType() != ExceptionType.NO_DATA) {
                    throw exception;
                }
            }

            start = start.plusDays(1);
        }
        while (!start.isAfter(end));

        //Deal wil no logs
        if (results.size() == 0) {
            throw new AuditingException(ExceptionType.NO_DATA, Constants.NO_TRANSACTIONS_FOUND_ERROR_MESSAGE);
        }

        //Filter
        return results.stream()
                .filter(auditLog -> filterDate(auditLog.getDate().toLocalDate(), searchQuery))
                .filter(auditLog -> filterUsername(auditLog.getUsername(), searchQuery))
                .filter(auditLog -> filterBySystem(auditLog.getSystem(), searchQuery))
                .filter(auditLog -> filterByType(auditLog.getTransactionType(), searchQuery))
                .collect(Collectors.toList());
    }

    private static List<AuditLog> searchByUsername(SearchQuery searchQuery) throws AuditingException
    {
        List<AuditLog> results;

        //Get Results
        results = BigchainDBHelper.getAuditLogs("\"" + searchQuery.getUsername() + "\"");

        //Filter
        return results.stream()
                .filter(auditLog -> filterUsername(auditLog.getUsername(), searchQuery))
                .filter(auditLog -> filterBySystem(auditLog.getSystem(), searchQuery))
                .filter(auditLog -> filterByType(auditLog.getTransactionType(), searchQuery))
                .collect(Collectors.toList());
    }

    private static List<AuditLog> searchBySystem(SearchQuery searchQuery) throws AuditingException
    {
        List<AuditLog> results;

        //Query
        results = BigchainDBHelper.getAuditLogs("\"" + searchQuery.getSystem() + "\"");

        return results.stream()
                .filter(auditLog -> filterBySystem(auditLog.getSystem(), searchQuery))
                .filter(auditLog -> filterByType(auditLog.getTransactionType(), searchQuery))
                .collect(Collectors.toList());
    }

    private static List<AuditLog> searchByType(SearchQuery searchQuery) throws AuditingException
    {
        List<AuditLog> results;

        //Query
        results = BigchainDBHelper.getAuditLogs("\"" + searchQuery.getTransactionType().getValue() + "\"");

        return results.stream()
                .filter(auditLog -> filterByType(auditLog.getTransactionType(), searchQuery))
                .collect(Collectors.toList());
    }


    //Filtering Methods

    private static boolean filterDate(LocalDate date, SearchQuery searchQuery)
    {
        LocalDate dateFrom = searchQuery.getDateFrom().toLocalDate();
        LocalDate dateTo = searchQuery.getDateTo().toLocalDate();

        return (date.isAfter(dateFrom) && date.isBefore(dateTo)) ||
                date.isEqual(dateFrom) ||
                date.isEqual(dateTo);
    }

    private static boolean filterUsername(String username, SearchQuery searchQuery)
    {
        if (searchQuery.getUsername() == null || searchQuery.getUsername().isEmpty()) {
            return true;
        }

        return username.equalsIgnoreCase(searchQuery.getUsername());
    }

    private static boolean filterBySystem(String system, SearchQuery searchQuery)
    {
        if (searchQuery.getSystem() == null || searchQuery.getSystem().isEmpty()) {
            return true;
        }

        return system.equalsIgnoreCase(searchQuery.getSystem());
    }

    private static boolean filterByType(TransactionType transactionType, SearchQuery searchQuery)
    {
        if (searchQuery.getTransactionType() == null) {
            return true;
        }

        return transactionType.equals(searchQuery.getTransactionType());
    }
}
