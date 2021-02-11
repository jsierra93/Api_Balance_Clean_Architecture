package co.com.jsierra.restproduct;

import org.junit.Test;

import javax.net.ssl.SSLException;

import static org.junit.Assert.assertNotNull;


public class WebClientConfigTest {
    @Test
  public void createWebClient() throws SSLException {
        WebClientConfig webClientConfig = new WebClientConfig();
        webClientConfig.setUrlBase("http://localhost");
        assertNotNull(webClientConfig.createWebClient());
    }
}