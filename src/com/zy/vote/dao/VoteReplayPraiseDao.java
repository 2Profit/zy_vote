package com.zy.vote.dao;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteReplayPraise;

public interface VoteReplayPraiseDao extends CommonDao<VoteReplayPraise, String>{

	@Query(" count(1) from VoteReplayPraise p where p.voteTopicPostReplay.id =?1 and p.member.id = ?2 ")
	public int findMemberPraise(String replayId, String memberId);
}
