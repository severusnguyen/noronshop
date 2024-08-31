package com.example.noronshopcommons.data.dto.request;

import com.example.noronshopcommons.data.modal.Author;
import com.example.noronshopcommons.data.modal.Categories;
import com.example.noronshopcommons.data.modal.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.JSON;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TikiProductsRequest {
    private Integer id;
    private String name;
    private String urlKey;
    private String shortDescription;
    private Double price;
    private Double discount;
    private Double discountRate;
    private String thumbnailUrl;
    private String description;
    private List<Specifications> specifications;
    private String bookName;
    private List<Author> authors;
    private Integer productId;
    private List<Categories> categories;
    private LocalDateTime createdAt;
    private String status;
}
