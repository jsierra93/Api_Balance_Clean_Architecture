package co.com.jsierra.model.balancemovements;

import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.transaction.RqOffice;
import co.com.jsierra.model.transaction.RqPagination;
import co.com.jsierra.model.transaction.RqTransactionBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RqData {
    private RqAccount account;
    private RqTransactionBody transaction;
    private RqPagination pagination;
    private RqOffice office;
}
