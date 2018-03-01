package code.helpers;

import code.exceptions.AuditingException;
import code.model.*;

import java.time.LocalDateTime;
import java.util.*;

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
        Map<String, Integer> userExports = new HashMap<>();

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

            //Sort out User Exports
            if (auditLog.getTransactionType() == TransactionType.EXPORT) {
                if (userExports.containsKey(auditLog.getUsername())) {
                    userExports.put(auditLog.getUsername(), userExports.get(auditLog.getUsername()) + 1);
                } else {
                    userExports.put(auditLog.getUsername(), 1);
                }
            }
        });

        stats.setSystemLogs(convertToSystemStatArrayList(systemLogs));
        stats.setUserLogs(convertToUserStatArrayList(userLogs));
        stats.setFailedLogins(convertToUserStatArrayList(failedLoginLogs));
        stats.setUserExports(convertToUserStatArrayList(userExports));

        return stats;
    }

    private static ArrayList<UserStat> convertToUserStatArrayList(Map<String, Integer> stats)
    {
        ArrayList<UserStat> userStats = new ArrayList<>();
        Iterator it = stats.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            userStats.add(new UserStat(pair.getKey(), pair.getValue()));
        }

        return userStats;
    }

    private static ArrayList<SystemStat> convertToSystemStatArrayList(Map<String, Integer> stats)
    {
        ArrayList<SystemStat> systemStats = new ArrayList<>();
        Iterator it = stats.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();
            systemStats.add(new SystemStat(pair.getKey(), pair.getValue()));
        }

        return systemStats;
    }

}
