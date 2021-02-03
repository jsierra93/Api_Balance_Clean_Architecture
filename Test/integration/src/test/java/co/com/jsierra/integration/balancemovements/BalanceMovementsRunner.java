package co.com.jsierra.integration.balancemovements;

import org.junit.runner.RunWith;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.junit4.Karate;

@RunWith(Karate.class)
@KarateOptions(features = "src/test/java/co/com/bancolombia/integration/details/balance_movements.feature")
public class BalanceMovementsRunner {

}
