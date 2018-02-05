package code.validators;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.SearchQuery;

/* This class is used to validate the search query */
public class GetAuditLogsValidator
{

    public static void validate(SearchQuery searchQuery) throws AuditingException
    {
        //Check that the query is not null
        if (isNullOrEmpty(searchQuery.getId()) &&
                isNull(searchQuery.getDateFrom()) &&
                isNull(searchQuery.getDateTo()) &&
                isNullOrEmpty(searchQuery.getSystem()) &&
                isNullOrEmpty(searchQuery.getUsername()) &&
                isNull(searchQuery.getSystem())) {
            throw new AuditingException(ExceptionType.VALIDATION_ERROR, Constants.SEARCH_QUERY_EMPTY_ERROR_MESSAGE);
        }

        //Validate Dates
        if (isNull(searchQuery.getDateFrom()) && !isNull(searchQuery.getDateTo())) {
            throw new AuditingException(ExceptionType.VALIDATION_ERROR, Constants.NO_FROM_DATE_ERROR_MESSAGE);
        }

        if (!isNull(searchQuery.getDateTo()) && searchQuery.getDateFrom().isAfter(searchQuery.getDateTo())) {
            throw new AuditingException(ExceptionType.VALIDATION_ERROR, Constants.TO_DATE_BEFORE_FROM_DATE_ERROR_MESSAGE);
        }
    }

    private static boolean isNull(Object object)
    {
        return object == null;
    }

    private static boolean isNullOrEmpty(String value)
    {
        return value == null || value.isEmpty();
    }

}
