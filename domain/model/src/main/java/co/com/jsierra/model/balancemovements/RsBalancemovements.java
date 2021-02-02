package co.com.jsierra.model.balancemovements;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RsBalancemovements {
    private List<RsData> data;
    private RsStatus status;
}
