package blog.ex.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.ex.model.dao.BlogDao;
import blog.ex.model.entity.BlogEntity;
import blog.ex.model.entity.UserEntity;


@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private UserService userService;
	
	public UserEntity getUserById(Long userId) {
		return userService.getUserById(userId);
	}
	
	public List<BlogEntity> findAllBlogPost(Long userId){
		if(userId == null) {
			return blogDao.findAll();
		}else {
			return blogDao.findByUserId(userId);
		}
	}
	
	public boolean createBlogPost(String blogTitle, LocalDate registerDate, String fileName, String blogDetail, Long categoryId, Long userId) {
		
		BlogEntity blog = new BlogEntity(blogTitle, registerDate, fileName, blogDetail, categoryId, userId);
		blogDao.save(blog);
		return true;
		
	}
	
	public BlogEntity getBlogPost(Long blogId) {
		if(blogId == null) {
			return null;
		}else {
			return blogDao.findByBlogId(blogId);
		}
	}
	
	public boolean editBlogPost(String blogTitle, String blogDetail, Long categoryId, Long userId, Long blogId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if(userId == null) {
			return false;
		}else {
			blogList.setBlogId(blogId);
			blogList.setBlogTitle(blogTitle);
			blogList.setCategoryId(categoryId);
			blogList.setBlogDetail(blogDetail);
			blogList.setUserId(userId);
			blogDao.save(blogList);
			return true;
		}
	}
	
	public boolean editBlogImage(Long blogId, String fileName, Long userId) {
		BlogEntity blogList = blogDao.findByBlogId(blogId);
		if(fileName == null || blogList.getBlogImage().equals(fileName)) {
			return false;
		}else {
			blogList.setBlogId(blogId);
			blogList.setBlogImage(fileName);
			blogList.setUserId(userId);
			blogDao.save(blogList);
			return true;
		}
	}
	
	//削除機能
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
	
	//検索機能
	
	public List<BlogEntity> searchBlogs(Long userId, String searchTerm) {
		if(userId == null) {
			return Collections.emptyList();
		}
		return blogDao.findByUserIdAndBlogTitleContaining(userId, searchTerm);
	}
}
