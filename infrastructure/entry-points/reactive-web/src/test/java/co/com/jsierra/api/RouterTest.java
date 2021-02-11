package co.com.jsierra.api;


import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.balancemovements.RqBalancemovements;
import co.com.jsierra.model.balancemovements.RqData;
import co.com.jsierra.model.balancemovements.RsBalancemovements;
import co.com.jsierra.model.transaction.RqOffice;
import co.com.jsierra.model.transaction.RqPagination;
import co.com.jsierra.model.transaction.RqTransactionBody;
import co.com.jsierra.usecase.balancemovement.BalancemovementUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    BalancemovementUseCase balancemovementUseCase;

    static final String URI_API = "/balance/movements";

    //Headers
    static final String CHANNEL = "Channel";
    static final String DATE = "Date";
    static final String DATE_TIME = "DateTime";
    static final String IP = "Ip";
    static final String CLIENTID = "ClientId";
    static final String CLIENTTYPE = "ClientType";


    static final String VALUE_CHANNEL = "APP";
    static final String VALUE_DATE = "2019/12/12";
    static final String VALUE_DATE_TIME = "19:00:00";
    static final String VALUE_IP = "1.1.1.1";
    static final String VALUE_CLIENTID = "1234556788";
    static final String VALUE_CLIENTTYPE = "CC";

    Mono<RsBalancemovements> rsBalancemovements = Mono.just(RsBalancemovements.builder()
            .data(null)
            .build());

    RqBalancemovements rqBalancemovements;

    @Before
    public void init() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(50000))
                .build();

        rqBalancemovements = RqBalancemovements.builder()
                .data(List.of(RqData.builder()
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

        Mockito.when(balancemovementUseCase.getBalanceMovements(rqBalancemovements)).thenReturn(rsBalancemovements);
    }

    @Test
    public void routerFunctionIsOk() {
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(URI_API)
                        .build())
                .header("Content-Type","application/json")
                .header(CHANNEL, VALUE_CHANNEL)
                .header(DATE, VALUE_DATE)
                .header(DATE_TIME, VALUE_DATE_TIME)
                .header(IP, VALUE_IP)
                .header(CLIENTID, VALUE_CLIENTID)
                .header(CLIENTTYPE, VALUE_CLIENTTYPE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void routerFunctionNotHeaders() {
        webTestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path(URI_API)
                        .build())
                .header("Content-Type","application/json")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}