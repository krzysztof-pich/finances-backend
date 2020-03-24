package pl.pich.finances.app;

import java.util.ArrayList;
import java.util.List;

public class ErrorDetails {
    private String errorMessage;
    private List<String> details;

    public ErrorDetails(String message) {
        super();
        this.errorMessage = message;
        this.details = new ArrayList<>();
    }

    public ErrorDetails(String message, List<String> details) {
        super();
        this.errorMessage = message;
        this.details = details;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getDetails() {
        return details;
    }
}
