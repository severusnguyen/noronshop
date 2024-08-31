package com.example.noronshopcommons.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingAddressResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String districts;
    private String wards;
    private LocalDateTime createdAt;
    private Long userId;
    private UserResponse userResponse;
    private String address;
    private Integer id;
}
