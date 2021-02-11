package co.com.jsierra.restproduct;

import co.com.jsierra.model.balance.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.util.List;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class RestConsumerBalanceTest {

    public static MockWebServer mockWebServer;

    RqBalance rqBalance;
    Mono<RsBalance> rsBalance;

    @Before
    public void setUp() throws IOException {
        System.setProperty("io.netty.tryReflectionSetAccessible", "true");
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        rqBalance = RqBalance.builder()
                .data(List.of(RqDataBalance.builder()
                        .account(RqAccount.builder()
                                .number("1234567890")
                                .type("CUENTA_DE_AHORRO")
                                .build())
                        .build()))
                .build();


    }


    @Test
    public void getBalance() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(mapper.writeValueAsString(createResponseBalanceMock())));

        RestConsumerBalance restConsumerBalance = new RestConsumerBalance();
        restConsumerBalance.setUrl(mockWebServer.url("/balance").toString());
        String baseUrl = String.format("http://localhost:%s",
                mockWebServer.getPort());
        restConsumerBalance.setWebClient(WebClient.builder().baseUrl(baseUrl).build());
        rsBalance = restConsumerBalance.getBalance(rqBalance);

        StepVerifier.create(rsBalance)
                .assertNext(Assert::assertNotNull)
                .verifyComplete();
    }


    public RsBalance createResponseBalanceMock() {
        return RsBalance.builder()
                .data(List.of(
                        RsDataBalance.builder()
                                .account(RsAccount.builder()
                                        .balances(
                                                RsBalances.builder()
                                                        .available(BigDecimal.valueOf(1))
                                                        .availableOverdraftBalance(BigDecimal.valueOf(1))
                                                        .overdraftValue(BigDecimal.valueOf(1))
                                                        .availableOverdraftQuota(BigDecimal.valueOf(1))
                                                        .cash(BigDecimal.valueOf(1))
                                                        .unavailableClearing(BigDecimal.valueOf(1))
                                                        .receivable(BigDecimal.valueOf(1))
                                                        .blocked(BigDecimal.valueOf(1))
                                                        .unavailableClearing(BigDecimal.valueOf(1))
                                                        .cashStartDay(BigDecimal.valueOf(1))
                                                        .pockets(BigDecimal.valueOf(1))
                                                        .remittanceQuota(BigDecimal.valueOf(1))
                                                        .agreedRemittanceQuota(BigDecimal.valueOf(1))
                                                        .remittanceQuota(BigDecimal.valueOf(1))
                                                        .normalInterest(BigDecimal.valueOf(1))
                                                        .suspensionInterest(BigDecimal.valueOf(1))
                                                        .build()
                                        )
                                        .build())
                                .build()))
                .build();
    }


    @After
    public void mockShutDown() throws IOException {
        mockWebServer.shutdown();
    }
}