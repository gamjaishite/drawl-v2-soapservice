package org.soapService.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportUser {
    private int id;
    private String uuid;
    private String content;
    private int reporterId;
    private int reportedId;
    private String createdAt;
    private String updatedAt;
}
