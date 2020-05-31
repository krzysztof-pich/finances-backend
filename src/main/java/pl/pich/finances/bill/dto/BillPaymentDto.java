package pl.pich.finances.bill.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BillPaymentDto {
    private Integer id;
    private BigDecimal amount;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
