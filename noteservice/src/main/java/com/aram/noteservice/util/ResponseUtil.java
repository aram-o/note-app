package com.aram.noteservice.util;

import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class for ResponseEntity creation.
 * @author aram
 */
public final class ResponseUtil {

    private ResponseUtil() {
    }
    
    /**
     * Wrap the optional into a {@link ResponseEntity} with an
     * {@link HttpStatus#OK} status, or if it's empty, it returns a
     * {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X> type of the response
     * @param maybeResponse response to return if present
     * @param status
     * @return response containing {@code maybeResponse} if present or
     * {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpStatus status) {
        return wrapOrNotFound(maybeResponse, null, status);
    }

    /**
     * Wrap the optional into a {@link ResponseEntity} with an
     * {@link HttpStatus#OK} status, or if it's empty, it returns a
     * {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X> type of the response
     * @param maybeResponse response to return if present
     * @return response containing {@code maybeResponse} if present or
     * {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse) {
        return wrapOrNotFound(maybeResponse, null, HttpStatus.OK);
    }

    /**
     * Wrap the optional into a {@link ResponseEntity} with an
     * {@link HttpStatus#OK} status with the headers, or if it's empty, it
     * returns a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND}.
     *
     * @param <X> type of the response
     * @param maybeResponse response to return if present
     * @param header headers to be added to the response
     * @param status
     * @return response containing {@code maybeResponse} if present or
     * {@link HttpStatus#NOT_FOUND}
     */
    public static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, HttpHeaders header, HttpStatus status) {
        return maybeResponse.map(response -> ResponseEntity.status(status).headers(header).body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
