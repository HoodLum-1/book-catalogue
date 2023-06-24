package com.payu.southafrica.uiservicethymeleaf.components;

import com.payu.southafrica.uiservicethymeleaf.components.exceptions.CustomException;
import com.payu.southafrica.uiservicethymeleaf.model.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookApiClient {
    private static final String FAILED_TO_RETRIEVE_BOOK_FROM_API = "Failed to retrieve book from API";
    public static final String ERROR = "error: {}";
    @Value("${api.base-url}")
    private String baseUrl;

    private final Client client;

    public BookApiClient() {
        this.client = ClientBuilder.newClient();
    }

    public List<BookDto> getAllBooks() {
        try {
            Invocation.Builder request = client.target(baseUrl + "/all-books").request(MediaType.APPLICATION_JSON);
            Response response = request.get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                log.info("Fetching books...");
                return response.readEntity(new GenericType<List<BookDto>>() {
                });
            } else {
                throw new CustomException(FAILED_TO_RETRIEVE_BOOK_FROM_API);
            }
        } catch (Exception ex) {
            log.error(ERROR, ex.getMessage());
            throw new CustomException(FAILED_TO_RETRIEVE_BOOK_FROM_API);
        }
    }

    public BookDto getBookById(Long id) {
        try {
            Invocation.Builder request = client.target(baseUrl + "/book/" + id).request(MediaType.APPLICATION_JSON);
            Response response = request.get();
            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                log.info("Fetching book with id: {}", id);
                return response.readEntity(BookDto.class);
            } else {
                throw new CustomException(FAILED_TO_RETRIEVE_BOOK_FROM_API);
            }
        } catch (Exception ex) {
            log.error(ERROR, ex.getMessage());
            throw new CustomException(FAILED_TO_RETRIEVE_BOOK_FROM_API);
        }
    }

    public void addBook(BookDto book) {
        try {
            Invocation.Builder request = client.target(baseUrl + "/add-book").request(MediaType.APPLICATION_JSON);
            Response response = request.post(Entity.json(book));
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new CustomException("Failed to add book to API");
            }
            log.info("Adding book");
        } catch (Exception ex) {
            log.error(ERROR, ex.getMessage());
            throw new CustomException("Failed to add book to API");
        }
    }

    public void updateBook(Long id, BookDto book) {
        try {
            Invocation.Builder request = client.target(baseUrl + "/update-book/" + id).request(MediaType.APPLICATION_JSON);
            Response response = request.put(Entity.json(book));
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new CustomException("Failed to update book in API");
            }
            log.info("Updated book with id: {}", id);
        } catch (Exception ex) {
            log.error(ERROR, ex.getMessage());
            throw new CustomException("Failed to update book in API");
        }
    }

    public void deleteBook(Long id) {
        try {
            Invocation.Builder request = client.target(baseUrl + "/delete-book/" + id).request(MediaType.APPLICATION_JSON);
            Response response = request.delete();
            if (response.getStatus() != Response.Status.OK.getStatusCode()) {
                throw new CustomException("Failed to delete book from API");
            }
            log.info("Deleted book with id: {}", id);
        } catch (Exception ex) {
            log.error(ERROR, ex.getMessage());
            throw new CustomException("Failed to delete book from API");
        }
    }
}
