package pl.pich.finances.bill.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Period {
    @JsonProperty("daily")
    DAILY("daily"),

    @JsonProperty("weekly")
    WEEKLY("weekly"),

    @JsonProperty("monthly")
    MONTHLY("monthly");

    private String code;

    Period(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public static Period fromCode(String code) {
        for (Period interval : Period.values()){
            if (interval.getCode().equals(code)){
                return interval;
            }
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!");
    }

}
