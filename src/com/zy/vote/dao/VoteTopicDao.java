package com.zy.vote.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteTopic;

public interface VoteTopicDao extends VoteTopicCustomDao,CommonDao<VoteTopic,String>{

	@Query(" from VoteTopic l where l.startDate <= now() and l.endDate >= now() and l.deleteFlag !=1 ")
	List<VoteTopic> getCurrentTopic();
	
	@Query(" from VoteTopic l where l.deleteFlag !=1 order by l.startDate desc ")
	List<VoteTopic> getOrderedTopic();

	@Query(" from VoteTopic l where l.startDate >= now() and l.deleteFlag !=1 order by l.startDate asc ")
	List<VoteTopic> getNextTopic();
	
	@Query(" count(1) from VoteTopic l where l.deleteFlag !=1 and l.endDate >= ?1 ")
	int findTopicByStartDate(Date startDate);
	
	@Query(" count(1) from VoteTopic l where l.deleteFlag !=1 and l.startDate <= ?1 ")
	int findTopicByEndDate(Date startDate);
}
