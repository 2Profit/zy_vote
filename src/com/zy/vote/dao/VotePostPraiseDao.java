package com.zy.vote.dao;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VotePostPraise;

public interface VotePostPraiseDao extends CommonDao<VotePostPraise, String>,VotePostPraiseCustomDao{

	@Query("select count(1) from VotePostPraise p where p.member.id = ?1 and p.voteTopicPost.id = ?2")
	public int findMemberPraise(String memberId, String postId);
}
