package com.example.noronshopcommons.data.modal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderShipping {
    private String address;
    private String fullName;
    private String phone;
}
