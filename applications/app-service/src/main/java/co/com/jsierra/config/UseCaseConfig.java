package co.com.jsierra.config;

import co.com.jsierra.model.balance.gateways.BalanceGateway;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import co.com.jsierra.usecase.balancemovement.BalancemovementUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
public BalancemovementUseCase balancemovementUseCase(BalanceGateway balanceGateway, TransactionGateway transactionGateway){
    return new BalancemovementUseCase(balanceGateway, transactionGateway);
}
}
