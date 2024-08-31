package com.example.noronshopcommons.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountBankRequest {
    private Integer id;
    private String nameBank;
    private Long ownerId;
    private String ownerAccount;
    private String numberCard;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
    private String userName;
    private String identityNumber;
    private String bankBranch;
    private String accountNumber;
}
