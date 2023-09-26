package blog.ex.model.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.BlogEntity;
import jakarta.transaction.Transactional;


public interface BlogDao extends JpaRepository<BlogEntity,Long> {
	//userIdでブログを検索
	List<BlogEntity> findByUserId(Long userId);
	
	BlogEntity findByBlogTitleAndRegisterDate(String blogTitle,LocalDate registerDate);

	BlogEntity findByBlogId(Long blogId);
	
	@Transactional
	List<BlogEntity> deleteByBlogId(Long blogId);
	
	List<BlogEntity> findByUserIdAndBlogTitleContaining(Long userId, String searchTerm);
}
