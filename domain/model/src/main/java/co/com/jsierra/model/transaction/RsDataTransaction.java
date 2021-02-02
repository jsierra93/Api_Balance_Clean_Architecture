package co.com.jsierra.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RsDataTransaction {
    @JsonProperty("_responseSize")
    private int responseSize;

    @JsonProperty("_flagMoreRecords")
    private Boolean flagMoreRecords;

    private List<RsTransactionBody> transaction;
    private RqOffice office;
    private RsRelatedTransferAccount relatedTransferAccount;
    private RsCustomer customer;


}
