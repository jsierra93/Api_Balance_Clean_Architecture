package co.com.jsierra.model.transaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RqTransaction {
    private List<RqDataTransaction> data;
}
