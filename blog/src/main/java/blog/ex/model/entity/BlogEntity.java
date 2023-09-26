package blog.ex.model.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity

@Table(name = "blogs")
public class BlogEntity {
	@Id
	@Column(name = "blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	
	@NonNull
	@Column(name = "blog_title")
	private String blogTitle;
	
	@NonNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "register_date")
	private LocalDate registerDate;
	
	@NonNull
	@Column(name = "blog_Image")
	private String blogImage;
	
	@NonNull
	@Column(name = "blog_detail")
	private String blogDetail;
	
	@NonNull
	@Column(name = "category_id")
	private Long categoryId;
	

	@NonNull
	@Column(name = "user_id")
	private Long userId;
	
	
	
	public BlogEntity(@NonNull String blogTitle, @NonNull LocalDate registerDate, @NonNull String blogImage, @NonNull String blogDetail, @NonNull Long categoryId, Long userId) {
		this.blogTitle = blogTitle;
		this.registerDate = registerDate;
		this.blogImage = blogImage;
		this.blogDetail = blogDetail;
		this.categoryId = categoryId;
		this.userId = userId;
	}

	public BlogEntity() {
		
		// TODO Auto-generated constructor stub
	}

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}
	
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getBlogDetail() {
		return blogDetail;
	}

	public void setBlogDetail(String blogDetail) {
		this.blogDetail = blogDetail;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	
}
