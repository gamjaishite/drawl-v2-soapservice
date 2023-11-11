package org.soapService.Common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.soapService.Domain.AccountVerificationRequest;
import org.soapService.Domain.CatalogRequest;
import org.soapService.Domain.ReportUser;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@XmlSeeAlso({AccountVerificationRequest.class, CatalogRequest.class, ReportUser.class, ArrayList.class})
public class ServiceResponse<T> implements Serializable {
    private String status;
    private String message;
    private List<T> data;
}
