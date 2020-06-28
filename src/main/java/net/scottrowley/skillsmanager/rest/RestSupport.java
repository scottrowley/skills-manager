package net.scottrowley.skillsmanager.rest;

import net.scottrowley.skillsmanager.response.ErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestSupport {
  public <T> ResponseEntity<T> singleEntity(final T entity) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(entity);
  }

  public <T> ResponseEntity<List<T>> pagedEntities(final Page<T> page) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .contentType(MediaType.APPLICATION_JSON)
        .body(page.getContent());
  }

  public <T> ResponseEntity<T> created(final T entity) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .contentType(MediaType.APPLICATION_JSON)
        .body(entity);
  }

  public ResponseEntity<ErrorResponse> notFound(final Map<String, String> data) {
    ErrorResponse errorResponse = new ErrorResponse("NOT_FOUND", "Not found", data);

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body(errorResponse);
  }

  public static Map<String, String> dataMapFromId(final Integer id) {
    Map<String, String> data = new HashMap<>();
    data.put("id", id.toString());
    return data;
  }
}
