package code.model;

import java.util.List;

/*
    Class used for the response of the Get Stats Request
 */
public class Stats
{
    private int totalLogs;

    private List<SystemStat> systemLogs;

    private List<UserStat> userLogs;

    private List<UserStat> failedLogins;

    private List<UserStat> userExports;

    public int getTotalLogs()
    {
        return totalLogs;
    }

    public void setTotalLogs(int totalLogs)
    {
        this.totalLogs = totalLogs;
    }

    public List<SystemStat> getSystemLogs()
    {
        return systemLogs;
    }

    public void setSystemLogs(List<SystemStat> systemLogs)
    {
        this.systemLogs = systemLogs;
    }

    public List<UserStat> getUserLogs()
    {
        return userLogs;
    }

    public void setUserLogs(List<UserStat> userLogs)
    {
        this.userLogs = userLogs;
    }

    public List<UserStat> getFailedLogins()
    {
        return failedLogins;
    }

    public void setFailedLogins(List<UserStat> failedLogins)
    {
        this.failedLogins = failedLogins;
    }

    public List<UserStat> getUserExports()
    {
        return userExports;
    }

    public void setUserExports(List<UserStat> userExports)
    {
        this.userExports = userExports;
    }

}
