package code.model;

import java.util.HashMap;
import java.util.Map;

/*
    This enum contains the types of audit logs which can be stored in the system
 */
public enum TransactionType
{

    LOGIN("Login"),
    CREATE("Create"),
    READ("Read"),
    DELETE("Delete"),
    UPDATE("Update");

    private static final Map<String, TransactionType> lookup = new HashMap<>();

    static {
        for (TransactionType type : TransactionType.values()) {
            lookup.put(type.getValue(), type);
        }
    }

    private String value;

    TransactionType(String value)
    {
        this.value = value;
    }

    public static TransactionType get(String value)
    {
        return lookup.get(value);
    }

    public String getValue()
    {
        return value;
    }
}
