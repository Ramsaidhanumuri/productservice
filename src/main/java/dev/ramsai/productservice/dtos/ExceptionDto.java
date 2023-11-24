package dev.ramsai.productservice.dtos;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {

	private final OffsetDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;

    public ExceptionDto(OffsetDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
