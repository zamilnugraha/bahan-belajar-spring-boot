package com.banner.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.banner.entity.User;
import com.banner.entity.UserPK;

public interface UserDao extends JpaRepository<User, UserPK> {
	
	public User findOneByName(String name);
	public User findOneById(String id);
	public User findOneByEmail(String email);
	
	//new user
	@Query(value="SELECT * FROM EKR_98_USER WHERE EMAIL = ? AND PASSWORD = ?",nativeQuery=true)
	public User findUserByEmail(String email, String password);
	
	@Query(value="SELECT * FROM EKR_98_USER WHERE EMAIL = ?",nativeQuery=true)
	public User loginWithLdap(String email);
	
	@Query(value="SELECT * FROM EKR_98_USER WHERE ID = ?",nativeQuery=true)
	public User findUserByID(String id);
	
	@Query
	(value="SELECT a.*, b.ID, b.EMAIL, b.PASSWORD "+
			"FROM EKR_99_TOKEN a, EKR_98_USER "+
			"ON a.ID = b.ID "+
			"WHERE a.USER_ID=b.ID", nativeQuery=true)
	public User findTokenById(String id);
	
	@Modifying(clearAutomatically = true)
 	@Query(value="UPDATE EKR_98_USER SET ROW_STATUS = ?, ROW_TIME_UPDATE = ? WHERE id = ?",nativeQuery=true)
 	public Integer deleteUser(String rowStatus, Date rowTimeUpdate, String id);
		
	@Modifying(clearAutomatically = true)
 	@Query(value="UPDATE EKR_99_TOKEN SET ROW_STATUS = 0 WHERE TOKEN = ?",nativeQuery=true)
 	public Integer deleteToken(String token);
	
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_98_USER SET APPROVAL_NO = ? WHERE id = ?",nativeQuery=true)
	public Integer updateStatus(String approvalStatus, String id);
	
	//change password
	@Modifying(clearAutomatically = true)
	@Query(value="UPDATE EKR_98_USER SET PASSWORD = ?, ROW_TIME_UPDATE = ? WHERE id = ?",nativeQuery=true)
	public Integer changePassword(String oldPassword, Date rowTimeUpdate, String id);
	
	@Query(value="SELECT * FROM EKR_98_USER WHERE ROW_STATUS = '1' ORDER BY ROW_TIME_UPDATE DESC",nativeQuery=true)
	public List<User> findAllRedis();
	public List<User> findAllByOrderByNameAsc();
	public List<User> findAllByTypeOrderByNameAsc(String string);
	public List<User> findAllByRowStatusAndTypeOrderByNameAsc(String rowStatus, String type);
	public User findOneByEmailIgnoreCase(String email);
}
