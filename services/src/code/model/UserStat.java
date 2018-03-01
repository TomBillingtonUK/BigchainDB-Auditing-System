package code.model;

/*
    Used for providing User Statistics
 */
public class UserStat
{
    private String username;

    private int amount;

    public UserStat(String username, int amount)
    {
        this.username = username;
        this.amount = amount;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
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
