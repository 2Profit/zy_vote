package com.zy.vote.dao;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VotePostReport;

public interface VotePostReportDao extends CommonDao<VotePostReport, String>{
	
	@Query(" select count(1) from VotePostReport r where r.member.id = ?1 and r.voteTopicPost.id = ?2")
	public int findMemberPostReport(String memberId, String postId);

}
