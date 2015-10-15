package com.zy.vote.dao;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteReplayReport;

public interface VoteReplayReportDao extends CommonDao<VoteReplayReport, String>{

	@Query(" count(1) from VoteReplayReport p where p.voteTopicPostReplay.id =?1 and p.member.id = ?2 ")
	public int findMemberReport(String replayId, String memberId);
}
