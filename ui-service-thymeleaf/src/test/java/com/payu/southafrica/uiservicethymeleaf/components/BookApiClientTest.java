package com.payu.southafrica.uiservicethymeleaf.components;

import com.payu.southafrica.uiservicethymeleaf.components.exceptions.CustomException;
import com.payu.southafrica.uiservicethymeleaf.model.BookDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookApiClientTest {

    private static final String BASE_URL = "http://localhost:5000/api/books";

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder requestBuilder;

    @Mock
    private Response response;

    @InjectMocks
    private BookApiClient bookApiClient;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(bookApiClient, "baseUrl", BASE_URL);
        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(requestBuilder);
    }

    @Test
    public void testGetAllBooks_SuccessfulResponse_ReturnsListOfBooks() {
        List<BookDto> expectedBooks = Arrays.asList(
                createBookDto(),
                createBookDto()
        );
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(response.readEntity(new GenericType<List<BookDto>>() {
        })).thenReturn(expectedBooks);
        when(requestBuilder.get()).thenReturn(response);

        List<BookDto> actualBooks = bookApiClient.getAllBooks();

        assertEquals(expectedBooks, actualBooks);
    }

    @Test(expected = CustomException.class)
    public void testGetAllBooks_NonOkResponse_ThrowsCustomException() {
        when(response.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        when(requestBuilder.get()).thenReturn(response);

        bookApiClient.getAllBooks();

        assertThatExceptionOfType(CustomException.class).isThrownBy(bookApiClient::getAllBooks);
    }

    @Test(expected = CustomException.class)
    public void testGetAllBooks_ExceptionThrown_ThrowsCustomException() {
        when(requestBuilder.get()).thenThrow(new RuntimeException("Something went wrong"));

        bookApiClient.getAllBooks();

        assertThatExceptionOfType(CustomException.class).isThrownBy(bookApiClient::getAllBooks);
    }

    @Test
    public void testGetBookById_SuccessfulResponse_ReturnsBookDto() {
        BookDto expectedBook = createBookDto();
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(response.readEntity(BookDto.class)).thenReturn(expectedBook);
        when(requestBuilder.get()).thenReturn(response);

        BookDto actualBook = bookApiClient.getBookById(createBookDto().getId());

        assertEquals(expectedBook, actualBook);
    }

    @Test(expected = CustomException.class)
    public void testGetBookById_NonOkResponse_ThrowsCustomException() {
        when(response.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        when(requestBuilder.get()).thenReturn(response);

        bookApiClient.getBookById(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.getBookById(any()));
    }

    @Test(expected = CustomException.class)
    public void testGetBookById_ExceptionThrown_ThrowsCustomException() {
        when(requestBuilder.get()).thenThrow(new RuntimeException("Something went wrong"));

        bookApiClient.getBookById(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.getBookById(any()));

    }

    @Test
    public void testAddBook_SuccessfulResponse_NoExceptionsThrown() {
        BookDto book = createBookDto();
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(requestBuilder.post(Entity.json(book))).thenReturn(response);

        assertDoesNotThrow(() -> bookApiClient.addBook(book));
    }

    @Test(expected = CustomException.class)
    public void testAddBook_NonOkResponse_ThrowsCustomException() {
        when(response.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        when(requestBuilder.post(Entity.json(any()))).thenReturn(response);

        bookApiClient.addBook(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.addBook(any()));
    }

    @Test(expected = CustomException.class)
    public void testAddBook_ExceptionThrown_ThrowsCustomException() {
        when(requestBuilder.post(Entity.json(any()))).thenThrow(new RuntimeException("Something went wrong"));

        bookApiClient.addBook(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.addBook(any()));
    }

    @Test
    public void testUpdateBook_SuccessfulResponse_NoExceptionsThrown() {
        BookDto book = createBookDto();
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(requestBuilder.put(Entity.json(book))).thenReturn(response);

        assertDoesNotThrow(() -> bookApiClient.updateBook(1L, book));
    }

    @Test(expected = CustomException.class)
    public void testUpdateBook_ExceptionThrown_ThrowsCustomException() {
        bookApiClient.updateBook(any(), any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.addBook(any()));
    }

    @Test
    public void testDeleteBook_SuccessfulResponse_NoExceptionsThrown() {
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        when(requestBuilder.delete()).thenReturn(response);

        assertDoesNotThrow(() -> bookApiClient.deleteBook(any()));
    }

    @Test(expected = CustomException.class)
    public void testDeleteBook_NonOkResponse_ThrowsCustomException() {
        when(response.getStatus()).thenReturn(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        when(requestBuilder.delete()).thenReturn(response);

        bookApiClient.deleteBook(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.addBook(any()));
    }

    @Test(expected = CustomException.class)
    public void testDeleteBook_ExceptionThrown_ThrowsCustomException() {
        when(requestBuilder.delete()).thenThrow(new RuntimeException("Something went wrong"));

        bookApiClient.deleteBook(any());

        assertThatExceptionOfType(CustomException.class).isThrownBy(() -> bookApiClient.addBook(any()));
    }

    private BookDto createBookDto() {
        return BookDto.builder()
                .id(1L)
                .name("Sample Book")
                .isbn("ISBN-001")
                .publishDate("2023-06-12")
                .price(BigDecimal.valueOf(19.99))
                .bookType("Soft Copy")
                .build();
    }
}

