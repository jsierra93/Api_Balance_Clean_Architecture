package co.com.jsierra.model.balance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RsBalances {
    private BigDecimal available;
    private BigDecimal availableOverdraftBalance;
    private BigDecimal overdraftValue;
    private BigDecimal availableOverdraftQuota;
    private BigDecimal cash;
    private BigDecimal unavailableClearing;
    private BigDecimal receivable;
    private BigDecimal blocked;
    private BigDecimal unavailableStartDayClearingStartDay;
    private BigDecimal cashStartDay;
    private BigDecimal pockets;
    private BigDecimal remittanceQuota;
    private BigDecimal agreedRemittanceQuota;
    private BigDecimal remittanceQuotaUsage;
    private BigDecimal normalInterest;
    private BigDecimal suspensionInterest;
}
