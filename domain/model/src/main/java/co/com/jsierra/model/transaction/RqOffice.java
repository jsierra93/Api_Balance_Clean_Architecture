package co.com.jsierra.model.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RqOffice {
    private String code;
    private String name;
}
