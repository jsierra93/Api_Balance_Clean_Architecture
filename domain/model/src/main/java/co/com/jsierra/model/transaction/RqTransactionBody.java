package co.com.jsierra.model.transaction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RqTransactionBody {
    private String startDate;
    private String endDate;
    private int minAmount;
    private int maxAmount;
    private String type;
    private String checkNumber;
    private String group;
    private String description;
}
