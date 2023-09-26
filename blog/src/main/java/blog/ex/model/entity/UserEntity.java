package blog.ex.model.entity;

import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NonNull
	@Column(name = "user_name")
	private String userName;
	
	@NonNull
	@Column(name = "password")
	private String password;
	
	@NonNull
	@Column(name = "email")
	private String email;
	//フィールドにemailを追加する
	
	@NonNull
	@Column(name = "register_date")
	private LocalDateTime registerDate;

    public UserEntity() {
    }

    public UserEntity(String userName, String email, String password, LocalDateTime registerDate) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.registerDate = registerDate;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}
    


}
