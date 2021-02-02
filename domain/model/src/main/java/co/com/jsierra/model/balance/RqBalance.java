package co.com.jsierra.model.balance;
import lombok.*;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RqBalance {
    List<RqDataBalance> data;
}
