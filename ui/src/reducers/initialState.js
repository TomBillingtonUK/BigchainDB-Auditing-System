export default {
    auditLogs: [{
        date: "2018-01-01T12:00:00",
        id: "12345",
        message: "Message A",
        system: "System A",
        transactionType: "CREATE",
        username: "User A"
    },
    {
        date: "2018-01-02T12:00:00",
        id: "12346",
        message: "Message B",
        system: "System B",
        transactionType: "DELETE",
        username: "User B"
    },],
    statistics: {},
    filter: {
        system: "",
        username: "",
        transactionType: "CREATE",
        timestamp_start: "",
        timestamp_end: ""
    },
    settings: {},
};