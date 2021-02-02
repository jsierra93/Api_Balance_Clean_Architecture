package co.com.jsierra.model.balance;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RqBalance {
    List<RqDataBalance> data;
}
