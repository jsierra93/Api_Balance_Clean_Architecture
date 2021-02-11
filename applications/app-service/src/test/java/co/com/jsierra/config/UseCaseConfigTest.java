package co.com.jsierra.config;

import co.com.jsierra.model.balance.gateways.BalanceGateway;
import co.com.jsierra.model.transaction.gateways.TransactionGateway;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseConfigTest {

    private UseCaseConfig useCaseConfig;

    @Mock
    private BalanceGateway balanceGateway;

    @Mock
    private TransactionGateway transactionGateway;

    @Before
    public void init() {
        useCaseConfig = new UseCaseConfig();
    }

    @Test
    public void balancemovementUseCase() {
        Assert.assertNotNull(useCaseConfig.balancemovementUseCase(balanceGateway, transactionGateway));
    }
}
