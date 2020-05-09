package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
	public int changePassword(@Param("password") String password,@Param("username") String username);
	
	public User findByPid(int pid);
	
	@Modifying
	@Query("DELETE FROM User u WHERE u.username=:username")
	public int deleteUserByUsername(@Param("username") String username);
	
	@Modifying(clearAutomatically=true)
	@Query("UPDATE User u SET u.password = :password, u.sid = :sid, u.adresse = :adresse WHERE u.username = :username")
	public int updateUser(@Param("password") String password, @Param("sid") Salle sid, 
			@Param("adresse") String adresse, @Param("username") String username);

	
	@Modifying(clearAutomatically=true)
	@Query("DELETE FROM User u WHERE u.username = :username")
	public int cancelUserAccount(@Param("username") String username);
}
