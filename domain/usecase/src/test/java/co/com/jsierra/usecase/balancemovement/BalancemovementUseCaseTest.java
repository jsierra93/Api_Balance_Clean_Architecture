package co.com.jsierra.usecase.balancemovement;

import co.com.jsierra.model.balance.*;
import co.com.jsierra.model.balance.gateways.BalanceGateway;
import co.com.jsierra.model.balancemovements.RqBalancemovements;
import co.com.jsierra.model.balancemovements.RqData;
import co.com.jsierra.model.balancemovements.RsBalancemovements;
import co.com.jsierra.model.transaction.*;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import com.sun.tools.javac.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BalancemovementUseCaseTest {

    @InjectMocks
    private BalancemovementUseCase balancemovementUseCase;

    @Mock
    private BalanceGateway balanceGateway;
    @Mock
    private TransactionGateway transactionGateway;


    private RqBalance rqBalance;

    private RsBalance rsBalance;

    private RsTransaction rsTransaction;

    private RqTransaction rqTransaction;

    private RqBalancemovements rqBalancemovements;


    @Before
    public void init() {

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

        rqBalance = RqBalance.builder()
                .data(List.of(RqDataBalance.builder()
                        .account(RqAccount.builder()
                                .number("1234567890")
                                .type("CUENTA_DE_AHORRO")
                                .build())
                        .build()))
                .build();


        rsBalance = RsBalance.builder()
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

        rsTransaction = RsTransaction
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


        when(balanceGateway.getBalance(rqBalance)).thenReturn(Mono.just(rsBalance));
        when(transactionGateway.getTransactions(rqTransaction)).thenReturn(Mono.just(rsTransaction));

    }

    @Test
    public void getBalanceMovements() {
        Mono<RsBalancemovements> rsBalancemovements = balancemovementUseCase.getBalanceMovements(rqBalancemovements);

        StepVerifier.create(rsBalancemovements)
                .assertNext(Assert::assertNotNull)
                .expectNextCount(0)
                .verifyComplete();
/*
        StepVerifier.create(rsBalancemovements)
                .assertNext(Assert::assertNotNull)
                .expectNextCount(0)
                .expectComplete()
                .verify();
*/
    }


}