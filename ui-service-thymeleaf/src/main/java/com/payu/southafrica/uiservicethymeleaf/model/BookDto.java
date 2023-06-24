package com.payu.southafrica.uiservicethymeleaf.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@Builder
@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private String isbn;
    private BigDecimal price;
    private String publishDate;
    private String bookType;

    public String getPriceFormatted() {
        if (price != null) {
            Locale locale = new Locale("en", "ZA");
            Currency currency = Currency.getInstance("ZAR");
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            currencyFormat.setCurrency(currency);
            return currencyFormat.format(price);
        }
        return "";
    }
}