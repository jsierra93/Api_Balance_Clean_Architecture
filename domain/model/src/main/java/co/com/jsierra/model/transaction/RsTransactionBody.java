package co.com.jsierra.model.transaction;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RsTransactionBody {
    private String id;
    private String postedDate;
    private String description;
    private BigDecimal amount;
    private String type;
    private String reference1;
    private String reference2;
    private String reference3;
    private String checkNumber;
}
