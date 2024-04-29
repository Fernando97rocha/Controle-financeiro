package com.app.controlefinanceiro.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {

    private Long id;
    private int amountType; //
    private BigDecimal amountvalue;
    private String description;

    public Amount() {
    }

    public Amount(Long id, int amountType, BigDecimal amountvalue, String description) {
        this.id = id;
        this.amountType = amountType;
        this.amountvalue = amountvalue;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmountType() {
        return amountType;
    }

    public void setAmountType(int amountType) {
        this.amountType = amountType;
    }

    public BigDecimal getAmountvalue() {
        return amountvalue;
    }

    public void setAmountvalue(BigDecimal amountvalue) {
        this.amountvalue = amountvalue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return amountType == amount.amountType && Objects.equals(id, amount.id) && Objects.equals(amountvalue, amount.amountvalue) && Objects.equals(description, amount.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountType, amountvalue, description);
    }
}
