package com.zy.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteTopic;

public interface VoteTopicDao extends VoteTopicCustomDao,CommonDao<VoteTopic,String>{

	@Query(" from VoteTopic l where l.schedule = ?1")
	List<VoteTopic> getTopicBySchedule(String schedule);
}
