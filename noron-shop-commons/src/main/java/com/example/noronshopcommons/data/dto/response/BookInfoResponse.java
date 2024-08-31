package com.example.noronshopcommons.data.dto.response;

import lombok.Data;

@Data
public class BookInfoResponse {
    private Integer id;
    private String code;
    private String author;
    private String supplier;
    private String size;
    private Integer weight;
    private String publisher;
    private Integer yearOfPublication;
    private String language;
    private Integer numberOfPages;
    private String form;
    private Integer productId;
    private ProductResponse productResponse;
}
