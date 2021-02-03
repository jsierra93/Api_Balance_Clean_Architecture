package co.com.jsierra.model.balancemovements;

import co.com.jsierra.model.balance.RqAccount;
import co.com.jsierra.model.balance.RsAccount;
import co.com.jsierra.model.balance.RsBalances;
import co.com.jsierra.model.transaction.RqOffice;
import co.com.jsierra.model.transaction.RsCustomer;
import co.com.jsierra.model.transaction.RsRelatedTransferAccount;
import co.com.jsierra.model.transaction.RsTransactionBody;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RsData {

    @JsonProperty("_responseSize")
    private int responseSize;

    @JsonProperty("_flagMoreRecords")
    private Boolean flagMoreRecords;

    private RsBalances balances;
    private RqAccount account;
    private List<RsTransactionBody> transaction;
    private RqOffice office;
    private RsRelatedTransferAccount relatedTransferAccount;
    private RsCustomer customer;
}
