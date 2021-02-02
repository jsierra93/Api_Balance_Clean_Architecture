package co.com.jsierra.usecase.balancemovement;

import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.balance.RqBalance;
import co.com.jsierra.model.balance.RqDataBalance;
import co.com.jsierra.model.balance.RsBalance;
import co.com.jsierra.model.balance.gateways.BalanceGateway;
import co.com.jsierra.model.balancemovements.RqBalancemovements;
import co.com.jsierra.model.balancemovements.RqData;
import co.com.jsierra.model.balancemovements.RsBalancemovements;
import co.com.jsierra.model.balancemovements.RsData;
import co.com.jsierra.model.transaction.RqDataTransaction;
import co.com.jsierra.model.transaction.RqTransaction;
import co.com.jsierra.model.transaction.RsTransaction;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BalancemovementUseCase {

    private final BalanceGateway balanceGateway;
    private final TransactionGateway transactionGateway;


    public Mono<RsBalancemovements> getBalanceMovements(RqBalancemovements rqBalancemovements){
        //Construimos los request Balance
        RqData rqData = rqBalancemovements.getData().get(0);

        List<RqDataBalance> rqDataBalance = new ArrayList<>();

        rqDataBalance.add(RqDataBalance.builder()
                .account(rqData.getAccount())
                .build());

        RqBalance rqBalance = RqBalance.builder().data(rqDataBalance)
                .build();

        //Construimos los request Transaction

        List<RqDataTransaction> rqDataTransactions = new ArrayList<>();
        rqDataTransactions.add(RqDataTransaction.builder()
                .account(rqData.getAccount())
                .office(rqData.getOffice())
                .pagination(rqData.getPagination())
                .transaction(rqData.getTransaction())
                .build()
        );
        RqTransaction rqTransaction = RqTransaction.builder()
                .data(rqDataTransactions)
                .build();

        Mono<RsBalance> rsBalance = balanceGateway.getBalance(rqBalance);
        Mono<RsTransaction> rsTransaction = transactionGateway.getTransactions(rqTransaction);

        return Mono.zip(rsBalance, rsTransaction)
                .flatMap( response -> {
                    List<RsData> data = new ArrayList<>();
                    data.add(RsData.builder()
                            .responseSize(response.getT2().getData().get(0).getResponseSize())
                            .flagMoreRecords(response.getT2().getData().get(0).getFlagMoreRecords())
                            .account(response.getT1().getData().get(0).getAccount())
                            .balances(response.getT1().getData().get(0).getAccount().getBalances())
                            .transaction(response.getT2().getData().get(0).getTransaction())
                            .office(response.getT2().getData().get(0).getOffice())
                            .relatedTransferAccount(response.getT2().getData().get(0).getRelatedTransferAccount())
                            .customer(response.getT2().getData().get(0).getCustomer())
                            .build());

                    RsBalancemovements rsBalancemovements = RsBalancemovements.builder()
                            .data(data)
                            .build();

                    return Mono.just(rsBalancemovements);
                });
    }
}