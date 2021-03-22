package co.com.jsierra.api;

import co.com.jsierra.model.balancemovements.RqBalancemovements;
import co.com.jsierra.model.balancemovements.RsBalancemovements;
import co.com.jsierra.usecase.balancemovement.BalancemovementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    @Autowired
    private final BalancemovementUseCase balancemovementUseCase;

    public Mono<ServerResponse> getBalanceMovements(ServerRequest serverRequest) {

        Mono<RqBalancemovements> rqBalancemovements = serverRequest.bodyToMono(RqBalancemovements.class);

        return ServerResponse.ok()
                .header("app-version","1.1")
                .body(rqBalancemovements.flatMap(
                        body -> balancemovementUseCase.getBalanceMovements(body)
                ), RsBalancemovements.class);
    }
}
