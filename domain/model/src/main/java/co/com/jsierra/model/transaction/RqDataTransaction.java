package co.com.jsierra.model.transaction;

import co.com.jsierra.model.balance.RqAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RqDataTransaction {
    private RqAccount account;
    private RqTransactionBody transaction;
    private RqPagination pagination;
    private RqOffice office;
}
