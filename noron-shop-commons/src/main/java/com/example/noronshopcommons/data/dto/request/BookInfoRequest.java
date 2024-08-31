package com.example.noronshopcommons.data.dto.request;

import lombok.Data;

@Data
public class BookInfoRequest {
    private Integer id;
    private String code;
    private String author;
    private String supplier;
    private String size;
    private int weight;
    private String publisher;
    private int yearOfPublication;
    private String language;
    private int numberOfPages;
    private String form;
    private int productId;
}
