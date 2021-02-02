package co.com.jsierra.model.balancemovements;

import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.transaction.RqOffice;
import co.com.jsierra.model.transaction.RqPagination;
import co.com.jsierra.model.transaction.RqTransactionBody;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RqData {
    private RqAccount account;
    private RqTransactionBody transaction;
    private RqPagination pagination;
    private RqOffice office;
}
