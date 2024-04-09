package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Address")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Address {

    @Id
    @Column(name = "user_id")
    @NonNull
    private String userId;

    @Column(nullable = false, length = 100)
    @NonNull
    private String street;

    @Column(nullable = false, length = 50)
    @NonNull
    private String ward;

    @Column(nullable = false, length = 50)
    @NonNull
    private String district;

    @Column(nullable = false, length = 50)
    @NonNull
    private String city;

    @Column(nullable = false, length = 50)
    @NonNull
    private String country;

    @Column(nullable = false)
    @NonNull
    private Integer zipCode;

    @OneToOne
    private User user;

}
