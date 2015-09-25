package com.zy.vote.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteTopicOption;

public interface VoteTopicOptionDao  extends CommonDao<VoteTopicOption,String>{

	@Query(" from VoteTopicOption o where o.voteTopic.id = ?1")
	List<VoteTopicOption> getOptionByVoteTopic(String voteTopicId);
}
