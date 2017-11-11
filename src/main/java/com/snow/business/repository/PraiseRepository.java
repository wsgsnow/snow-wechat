package com.snow.business.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.snow.business.entity.Praise;
import com.snow.business.entity.view.CommentView;

public interface PraiseRepository extends JpaRepository<Praise, Long> {


	Long countByCollectId(Long collectId);
	
	Praise findByUserIdAndCollectId(Long userId,Long collectId); 
	
	public String findPraiseUserSql="select u.id as userId,u.userName as userName,u.profilePicture as profilePicture,p.createTime as createTime "
			+ "from Praise p,User u WHERE p.userId=u.id";
	
	@Query(findPraiseUserSql+ " and p.id=?1")
	CommentView findPraiseUser(Long id);
	
}