package com.smallcinema.api.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Validated
public class Error {
    @NotNull
    private String code;
    @NotNull
    private String message;

    public Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Error() {
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Error error = (Error) o;
        return code.equals(error.code) && message.equals(error.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
