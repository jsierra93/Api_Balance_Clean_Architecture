package co.com.jsierra.restproduct;

import co.com.jsierra.model.balance.RqBalance;
import co.com.jsierra.model.balance.RsBalance;
import co.com.jsierra.model.transaction.RqTransaction;
import co.com.jsierra.model.transaction.RsTransaction;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestConsumerTransaction implements TransactionGateway {

   // @Value("${transaction.url}")
    private final String url = "https://practicabanco.getsandbox.com:443/movements";

    private  final WebClient webClient;

    public RestConsumerTransaction() {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }
    @Override
    public Mono<RsTransaction> getTransactions(RqTransaction rqTransaction) {
        return webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(rqTransaction), RqBalance.class)
                .retrieve()
                .bodyToMono(RsTransaction.class);
    }
}
