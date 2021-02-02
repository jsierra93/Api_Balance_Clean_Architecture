package co.com.jsierra.model.balancemovements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RsStatus {
    private String code;
    private String title;
    private String detail;
    private String severity;
}
