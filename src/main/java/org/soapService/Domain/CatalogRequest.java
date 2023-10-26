package org.soapService.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogRequest {
    private int id;
    private String uuid;
    private String title;
    private String description;
    private String poster;
    private String trailer;
    private String category;
    private String createdAt;
    private String updatedAt;
}
