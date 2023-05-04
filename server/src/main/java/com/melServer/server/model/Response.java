package com.melServer.server.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Melissa Hargis
 * 5/4/23
 */

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)/* separate what the front and backend see response wise */
public class Response {
    protected LocalDateTime timeStamp;
    protected  int statusCode;
    protected HttpStatus status;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected Map<?,?> data; /* front end */
}
