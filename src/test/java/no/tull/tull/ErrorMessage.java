package no.tull.tull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    private String message;
    private String type;

    @JsonCreator
    public ErrorMessage(@JsonProperty("message") final String message, @JsonProperty("type") final String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
