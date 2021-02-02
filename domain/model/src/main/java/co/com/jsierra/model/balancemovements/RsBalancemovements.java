package co.com.jsierra.model.balancemovements;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RsBalancemovements {
    private List<RsData> data;
    private RsStatus status;
}
