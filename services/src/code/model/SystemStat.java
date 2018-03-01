package code.model;

/*
    Class used for creating system statistics
 */
public class SystemStat
{
    private String system;

    private int amount;

    public SystemStat(String system, int amount)
    {
        this.system = system;
        this.amount = amount;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
