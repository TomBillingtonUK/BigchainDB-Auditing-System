package test.requests;

import code.requests.HelloWorldRequest;
import org.junit.Assert;
import org.junit.Test;

public class HelloWorldRequestTest
{

    @Test
    public void helloWorldTest_Valid()
    {
        Assert.assertEquals(HelloWorldRequest.getMessage(), "Hello");
    }
}
