package org.soapService.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private int id;
    private String reqDesc;
    private String ip;
    private String endpointDest;
    private String createdAt;
}
