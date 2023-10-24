package org.soapServiceV2.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountVerificationRequest {
    private int id;
    private String uuid;
    private int userId;
    private String status;
    private String createdAt;
    private String updatedAt;
}
