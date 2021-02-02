package co.com.jsierra.model.balancemovements;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RqBalancemovements {
    private List<RqData> data;
}
