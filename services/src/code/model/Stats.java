package code.model;

import java.util.Map;

/*
    Class used for the response of the Get Stats Request
 */
public class Stats
{
    private int totalLogs;

    private Map<String, Integer> systemLogs;

    private Map<String, Integer> userLogs;

    private Map<String, Integer> failedLogins;

    private Map<String, Integer> userExports;

    public int getTotalLogs()
    {
        return totalLogs;
    }

    public void setTotalLogs(int totalLogs)
    {
        this.totalLogs = totalLogs;
    }

    public Map<String, Integer> getSystemLogs()
    {
        return systemLogs;
    }

    public void setSystemLogs(Map<String, Integer> systemLogs)
    {
        this.systemLogs = systemLogs;
    }

    public Map<String, Integer> getUserLogs()
    {
        return userLogs;
    }

    public void setUserLogs(Map<String, Integer> userLogs)
    {
        this.userLogs = userLogs;
    }

    public Map<String, Integer> getFailedLogins()
    {
        return failedLogins;
    }

    public void setFailedLogins(Map<String, Integer> failedLogins)
    {
        this.failedLogins = failedLogins;
    }

    public Map<String, Integer> getUserExports()
    {
        return userExports;
    }

    public void setUserExports(Map<String, Integer> userExports)
    {
        this.userExports = userExports;
    }

}
