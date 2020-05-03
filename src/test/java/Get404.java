import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get404 extends BaseClass{

    //Create an HTTP client to send requests to the server
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException, NullPointerException {
        client.close();
//        response.close();
    }

    @Test
    public void nonExistingURLReturns404() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/nonexistingurl");
        response = client.execute(get);
        int actstatus = response.getStatusLine().getStatusCode();
        assertEquals(actstatus, 404);
    }
}
