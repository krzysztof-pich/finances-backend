package pl.pich.finances.bill.dto;

import pl.pich.finances.bill.model.Period;

import java.math.BigDecimal;
import java.util.Date;

public class BillDto {

    private Integer id;
    private String name;
    private BigDecimal amount;
    private Integer timeOfPayment;
    private Period period;
    private Integer intervalModulo;
    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(Integer timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getIntervalModulo() {
        return intervalModulo;
    }

    public void setIntervalModulo(Integer intervalModulo) {
        this.intervalModulo = intervalModulo;
    }
}
