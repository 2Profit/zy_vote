package com.zy.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteTopicPost;

public interface VoteTopicPostDao extends VoteTopicPostCustomDao,CommonDao<VoteTopicPost,String>{
	
	@Query(" from VoteTopicPost p where p.deleteFlag !=1 and p.voteTopic.id = ?1 and p.publisher.id = ?2 order by p.createDate desc ")
	List<VoteTopicPost> findMemberPost(String topicId, String memberId);
	
	@Query("select count(1) from VoteTopicPost p where p.deleteFlag !=1 and p.voteTopic.id = ?1 ")
	int countMaxFloorNumb(String topicId);
}
