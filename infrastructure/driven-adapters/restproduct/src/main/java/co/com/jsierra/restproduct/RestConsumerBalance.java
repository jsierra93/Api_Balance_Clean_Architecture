package co.com.jsierra.restproduct;


import co.com.jsierra.model.balance.RqBalance;
import co.com.jsierra.model.balance.RsBalance;
import co.com.jsierra.model.balance.gateways.BalanceGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestConsumerBalance implements BalanceGateway {

    @Value("${balance.url}")
    private String url;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<RsBalance> getBalance(RqBalance rqBalance) {
        return webClient.post()
                .uri(url)
                .body(Mono.just(rqBalance), RqBalance.class)
                .retrieve()
                .bodyToMono(RsBalance.class);
    }
}
