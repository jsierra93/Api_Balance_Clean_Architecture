package co.com.jsierra.model.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RsRelatedTransferAccount {
    private String type;
    private String number;
}
