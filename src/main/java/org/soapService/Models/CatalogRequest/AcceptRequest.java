package org.soapService.Models.CatalogRequest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This is just an example
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcceptRequest {
    private String title;
    private String description;
}
