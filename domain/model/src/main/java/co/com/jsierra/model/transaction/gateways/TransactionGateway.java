package co.com.jsierra.model.transaction.gateways;

import co.com.jsierra.model.transaction.RqTransaction;
import co.com.jsierra.model.transaction.RsTransaction;
import reactor.core.publisher.Mono;

public interface TransactionGateway {
    Mono<RsTransaction> getTransactions(RqTransaction rqTransaction);
}
