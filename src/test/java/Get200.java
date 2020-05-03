import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class Get200 extends BaseClass{

    //Create an HTTP client to send requests to the server
    public CloseableHttpClient client;
    public CloseableHttpResponse response;

    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException, NullPointerException {
        client.close();
//        response.close();
    }

    @Test
    public void baseURLReturns200() throws IOException {
        //send a GET request to the destination URL
        HttpGet get = new HttpGet(BASE_ENDPOINT);

        //tell the client to execute the GET request        
        response = client.execute(get);

        //Get status code from the response
        int actstatus = response.getStatusLine().getStatusCode();

        //Assert actual equals expected
        assertEquals(actstatus, 200);
    }

    @Test
    public void rateLimitReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/rate_limit");
        HttpResponse response = client.execute(get);
        int actstatus = response.getStatusLine().getStatusCode();
        assertEquals(actstatus, 200);
    }

    @Test
    public void searchRepoReturns200() throws IOException {
        HttpGet get = new HttpGet(BASE_ENDPOINT + "/search/repositories?q=java");
        HttpResponse response = client.execute(get);
        int actstatus = response.getStatusLine().getStatusCode();
        assertEquals(actstatus, 200);
    }
}
