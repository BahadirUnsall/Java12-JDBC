package com.bahadir.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Embeddable
public class Information {
    private String first_name;
    private String last_name;
    private String email;
}
