package code;

import code.requests.CreateAuditLogRequest;
import code.requests.GetAuditLogsRequest;
import code.requests.GetStatsRequest;
import code.requests.HelloWorldRequest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class AuditingServices extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add(HelloWorldRequest.class);
        hashSet.add(CreateAuditLogRequest.class);
        hashSet.add(GetAuditLogsRequest.class);
        hashSet.add(GetStatsRequest.class);
        hashSet.add(CorsFilter.class);

        return hashSet;
    }
}
