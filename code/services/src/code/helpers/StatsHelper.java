package code.helpers;

import code.exceptions.AuditingException;
import code.model.AuditLog;
import code.model.SearchQuery;
import code.model.Stats;
import code.model.TransactionType;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    This class is used to assist with calculating statistics
 */
public class StatsHelper
{
    public static Stats getStats() throws AuditingException
    {
        //Stats Object
        Stats stats = new Stats();

        //Get Dates
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime dateTimeYesterday = dateTimeNow.minusDays(1);

        //Build Search Query
        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setDateFrom(dateTimeYesterday);
        searchQuery.setDateTo(dateTimeNow);

        //Get Audit Logs
        List<AuditLog> auditLogList = SearchHelper.search(searchQuery);

        //Set Total Logs
        stats.setTotalLogs(auditLogList.size());

        //Set other Logs
        Map<String, Integer> systemLogs = new HashMap<>();
        Map<String, Integer> userLogs = new HashMap<>();
        Map<String, Integer> failedLoginLogs = new HashMap<>();

        auditLogList.forEach(auditLog -> {

            //Sort out System Logs
            if (systemLogs.containsKey(auditLog.getSystem())) {
                systemLogs.put(auditLog.getSystem(), systemLogs.get(auditLog.getSystem()) + 1);
            } else {
                systemLogs.put(auditLog.getSystem(), 1);
            }

            //Sort out User Logs
            if (userLogs.containsKey(auditLog.getUsername())) {
                userLogs.put(auditLog.getUsername(), userLogs.get(auditLog.getUsername()) + 1);
            } else {
                userLogs.put(auditLog.getUsername(), 1);
            }

            //Sort out Failed Login Logs
            if (auditLog.getTransactionType() == TransactionType.LOGIN && auditLog.getMessage().toLowerCase().contains("failed")) {
                if (failedLoginLogs.containsKey(auditLog.getUsername())) {
                    failedLoginLogs.put(auditLog.getUsername(), failedLoginLogs.get(auditLog.getUsername()) + 1);
                } else {
                    failedLoginLogs.put(auditLog.getUsername(), 1);
                }
            }
        });

        stats.setSystemLogs(systemLogs);
        stats.setUserLogs(userLogs);
        stats.setFailedLogins(failedLoginLogs);

        return stats;
    }

}
