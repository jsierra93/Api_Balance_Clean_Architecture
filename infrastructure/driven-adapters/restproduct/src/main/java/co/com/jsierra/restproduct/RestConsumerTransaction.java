package co.com.jsierra.restproduct;

import co.com.jsierra.model.transaction.RqTransaction;
import co.com.jsierra.model.transaction.RsTransaction;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Setter
public class RestConsumerTransaction implements TransactionGateway {

    @Value("${transaction.url}")
    private String url;

    @Autowired
    private WebClient webClient;

    @Override
    public Mono<RsTransaction> getTransactions(RqTransaction rqTransaction) {
        return webClient.post()
                .uri(url)
                .body(Mono.just(rqTransaction), RqTransaction.class)
                .retrieve()
                .bodyToMono(RsTransaction.class);

    }
}
