package test.validators;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.model.SearchQuery;
import code.validators.GetAuditLogsValidator;
import org.junit.Assert;
import org.junit.Test;

public class GetAuditLogsValidatorTest
{
    @Test
    public void validationTest_Valid() throws AuditingException
    {
        //setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setId("test");

        //execute
        GetAuditLogsValidator.validate(searchQuery);
    }

    @Test
    public void validationTest_Invalid_AllFieldsNull()
    {
        //setup
        SearchQuery searchQuery = new SearchQuery();

        try {
            GetAuditLogsValidator.validate(searchQuery);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(), Constants.SEARCH_QUERY_EMPTY_ERROR_MESSAGE);
        }
    }

    @Test
    public void validationTest_Invalid_ToDateWithNoFromDate()
    {
        //setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateTo("2018-01-01T00:00:00");

        try {
            GetAuditLogsValidator.validate(searchQuery);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(), Constants.NO_FROM_DATE_ERROR_MESSAGE);
        }
    }

    @Test
    public void validationTest_Invalid_ToDateBeforeFromDate()
    {
        //setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom("2018-01-01T00:00:00");
        searchQuery.setDateTo("2017-01-01T00:00:00");

        try {
            GetAuditLogsValidator.validate(searchQuery);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.VALIDATION_ERROR);
            Assert.assertNull(exception.getOriginalException());
            Assert.assertEquals(exception.getErrorMessage(), Constants.TO_DATE_BEFORE_FROM_DATE_ERROR_MESSAGE);
        }
    }
}
