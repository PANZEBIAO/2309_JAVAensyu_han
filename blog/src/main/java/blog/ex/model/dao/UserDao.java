package blog.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.ex.model.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Long> {
	UserEntity findByUserId(Long userId);
	
	UserEntity findByUserName(String userName);
	
	UserEntity findByUserNameAndPassword(String userName, String password);
}
