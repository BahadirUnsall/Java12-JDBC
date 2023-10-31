package com.bahadir.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Information {
    private String name;
    private String middleName;
    private String surname;
}
