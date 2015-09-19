package com.zy.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteTopicPostReplay;

public interface VoteTopicPostReplayDao extends VoteTopicPostReplayCustomDao,CommonDao<VoteTopicPostReplay,String>{

	@Query(" from VoteTopicPostReplay r where r.voteTopic.id = ?1")
	List<VoteTopicPostReplay> getByTopicId(String topicId);
}
