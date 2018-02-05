package test.helpers;

import code.Constants;
import code.exceptions.AuditingException;
import code.exceptions.ExceptionType;
import code.helpers.SearchHelper;
import code.model.AuditLog;
import code.model.SearchQuery;
import code.model.TransactionType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import test.TestUtilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchHelperTest
{
    @BeforeClass
    public static void setup() throws AuditingException
    {
        //Base Search Transaction
        TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now(), "System A", "User A", TransactionType.CREATE, "Message A"));
        //Transaction to catch out Date Searches
        TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now().plusDays(1),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE),
                        TransactionType.CREATE,
                        LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)));
        //Transaction to catch out System Searches
        TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now(), "Test", "System A", TransactionType.CREATE, "System A"));
        //Transaction to catch out Username Searches
        TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now(), "User A", "Test", TransactionType.CREATE, "User A"));
        //Transaction to catch out Type searches
        TestUtilities.createAuditLogAndWaitForProcessing(
                new AuditLog(LocalDateTime.now(),
                        TransactionType.CREATE.getValue(),
                        TransactionType.CREATE.getValue(),
                        TransactionType.DELETE,
                        TransactionType.CREATE.getValue()));
    }

    @Test
    public void searchHelperTest_Valid_FromDateOnly() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now()));
    }

    @Test
    public void searchHelperTest_Valid_FromAndToDate() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setDateTo(LocalDateTime.now());

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now()));
    }

    @Test
    public void searchHelperTest_Valid_DateAndSystem() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setSystem("System A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getSystem(), "System A");
        });
    }

    @Test
    public void searchHelperTest_Valid_DateAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_DateAndUsername() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setUsername("User A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getUsername(), "User A");
        });
    }

    @Test
    public void searchHelperTest_Valid_DateSystemAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setSystem("System A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_DateSystemAndUsername() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setSystem("System A");
        searchQuery.setUsername("User A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getUsername(), "User A");
        });
    }

    @Test
    public void searchHelperTest_Valid_DateUsernameAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setUsername("User A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getUsername(), "User A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_AllFilters() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(LocalDateTime.now());
        searchQuery.setSystem("System A");
        searchQuery.setUsername("User A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getDate().toLocalDate(), LocalDate.now());
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getUsername(), "User A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_Username() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUsername("User A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getUsername(), "User A");
        });
    }

    @Test
    public void searchHelperTest_Valid_UsernameAndSystem() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setSystem("System A");
        searchQuery.setUsername("User A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getUsername(), "User A");
        });
    }

    @Test
    public void searchHelperTest_Valid_UsernameAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUsername("User A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getUsername(), "User A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_UsernameSystemAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setSystem("System A");
        searchQuery.setUsername("User A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getUsername(), "User A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_System() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setSystem("System A");

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getSystem(), "System A");
        });
    }

    @Test
    public void searchHelperTest_Valid_SystemAndType() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setSystem("System A");
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getSystem(), "System A");
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Valid_Type() throws AuditingException
    {
        //Setup
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setTransactionType(TransactionType.CREATE);

        //execute
        List<AuditLog> logs = SearchHelper.search(searchQuery);

        //verify
        logs.stream().forEach(auditLog -> {
            Assert.assertEquals(auditLog.getTransactionType(), TransactionType.CREATE);
        });
    }

    @Test
    public void searchHelperTest_Invalid_NoResults()
    {
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setUsername("This is a username no on will ever have");

        try {
            SearchHelper.search(searchQuery);
        } catch (AuditingException exception) {
            Assert.assertEquals(exception.getExceptionType(), ExceptionType.NO_DATA);
            Assert.assertEquals(exception.getErrorMessage(), Constants.NO_TRANSACTIONS_FOUND_ERROR_MESSAGE);
            Assert.assertNull(exception.getOriginalException());
        }

    }
}
