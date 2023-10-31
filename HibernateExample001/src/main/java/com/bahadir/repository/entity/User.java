package com.bahadir.repository.entity;


import com.bahadir.enums.EAdressType;
import com.bahadir.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tblusers")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Information information;
    @Column(nullable = false,length = 32)
    private String password;
    @Column(nullable = false,unique = true)
    private String username;
    @Enumerated()
    @Builder.Default
    private EGender gender = EGender.BELIRSIZ;

    @ElementCollection
    private List<String> interest;

    @ElementCollection
    @Embedded
    private Map<EAdressType,Adress> adresses;
}
