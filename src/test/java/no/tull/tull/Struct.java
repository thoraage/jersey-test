package no.tull.tull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Struct {
    private final String hopp;
    private final int i;

    @JsonCreator
    public Struct(@NotNull @JsonProperty(value = "hopp", required = true) final String hopp, @JsonProperty("i") final int i) {
        this.hopp = hopp;
        this.i = i;
    }

    public String getHopp() {
        return hopp;
    }

    public int getI() {
        return i;
    }
}
