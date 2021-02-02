package co.com.jsierra.restproduct;

import co.com.jsierra.model.balance.RqBalance;
import co.com.jsierra.model.balance.RsBalance;
import co.com.jsierra.model.balance.gateways.BalanceGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestConsumerBalance implements BalanceGateway {

  //  @Value("${balance.url}")
    private final String url = "https://practicabanco.getsandbox.com:443/balances";

    private final WebClient webClient;

    public RestConsumerBalance() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    @Override
    public Mono<RsBalance> getBalance(RqBalance rqBalance) {
        return webClient.post()
                .body(Mono.just(rqBalance), RqBalance.class)
                .retrieve()
                .bodyToMono(RsBalance.class);
    }
}
