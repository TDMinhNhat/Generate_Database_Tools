package dev.skyherobrine.tools.entities.blog;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "User")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class User {

	@Id
	@Column(name = "user_id", nullable = false)
	@NonNull
	private String userId;

	@Column(name = "full_name", nullable = false, length = 100)
	@NonNull
	private String fullName;

	@Column(name = "dob", nullable = false)
	@NonNull
	private LocalDate dob;

	@Column(name = "sex", nullable = false)
	@NonNull
	private Boolean sex;

	@Column(name = "email", nullable = false, length = 100, unique = true)
	@NonNull
	private String email;

	@Column(name = "phone", nullable = false, length = 20, unique = true)
	@NonNull
	private String phone;

	@OneToOne
	@JoinColumn(name = "address_id", nullable = false)
	@NonNull
	private Address address;

	@Column(name = "account_name", nullable = false, length = 50, unique = true)
	@NonNull
	private String accountName;

	@Column(nullable = false, length = 100)
	@NonNull
	private String password;

	@Column(name = "date_created", nullable = false)
	@NonNull
	private LocalDateTime dateCreated;

	@Column(nullable = false)
	@NonNull
	private Boolean status;

	@Column(name = "date_modifier", nullable = false)
	@NonNull
	private LocalDateTime dateModifier;
	
}
