package co.com.jsierra.model.balance.gateways;

import co.com.jsierra.model.balance.RqBalance;
import co.com.jsierra.model.balance.RsBalance;
import reactor.core.publisher.Mono;

public interface BalanceGateway {
    Mono<RsBalance> getBalance(RqBalance rqBalance);
}
