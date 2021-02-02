package co.com.jsierra.model.balancemovements;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RsStatus {
    private String code;
    private String title;
    private String detail;
    private String severity;
}
