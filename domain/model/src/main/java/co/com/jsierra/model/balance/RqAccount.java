package co.com.jsierra.model.balance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RqAccount {
    private String type;
    private String number;
}
