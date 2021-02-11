package co.com.jsierra.restproduct;


import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.transaction.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RestConsumerTransactionTest {

    public static MockWebServer mockWebServer;

    RqTransaction rqTransaction;
    RsTransaction  rsTransactionMock;
    Mono<RsTransaction> rsTransaction;

    @Before
    public void setUp() throws IOException {
        System.setProperty("io.netty.tryReflectionSetAccessible", "true");
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        rqTransaction = RqTransaction.builder()
                .data(List.of(RqDataTransaction.builder()
                        .account(RqAccount.builder()
                                .number("1234567890")
                                .type("CUENTA_DE_AHORRO")
                                .build())
                        .office(RqOffice.builder()
                                .code("12345")
                                .name("12345")
                                .build())
                        .pagination(RqPagination.builder()
                                .key(1)
                                .size(30)
                                .build())
                        .transaction(RqTransactionBody.builder()
                                .startDate("2020-01-01")
                                .endDate("2020-01-01")
                                .minAmount(1)
                                .maxAmount(4)
                                .type("DEBITO")
                                .checkNumber("")
                                .group("")
                                .description("")
                                .build())
                        .build()))
                .build();

    rsTransactionMock = RsTransaction
                .builder()
                .data(List.of(RsDataTransaction.builder()
                        .responseSize(1)
                        .flagMoreRecords(true)
                        .office(RqOffice.builder()
                                .code("12345")
                                .name("12345")
                                .build())
                        .office(RqOffice.builder()
                                .code("12345")
                                .name("12345")
                                .build())
                        .transaction(List.of(RsTransactionBody
                                .builder()
                                .id("1")
                                .postedDate("2020-04-01")
                                .description("Transferencia a otros bancos")
                                .amount(BigDecimal.valueOf(1))
                                .type("DEBITO")
                                .reference1("")
                                .reference2("")
                                .reference3("")
                                .checkNumber("")
                                .build()))
                        .build()))
                .build();
    }

    @Test
    public void getTransactions() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .setBody(mapper.writeValueAsString(rsTransactionMock)));

        RestConsumerTransaction restConsumerTransaction = new RestConsumerTransaction();

        restConsumerTransaction.setUrl(mockWebServer.url("/transaction").toString());
        String baseUrl = String.format("http://localhost:%s",
                mockWebServer.getPort());
        restConsumerTransaction.setWebClient(WebClient.builder().baseUrl(baseUrl).build());
        rsTransaction = restConsumerTransaction.getTransactions(rqTransaction);

        StepVerifier.create(rsTransaction)
                .assertNext(Assert::assertNotNull)
                .verifyComplete();
    }

    @After
    public void mockShutDown() throws IOException {
        mockWebServer.shutdown();
    }
}