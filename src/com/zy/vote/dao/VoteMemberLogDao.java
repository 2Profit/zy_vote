package com.zy.vote.dao;

import org.springframework.data.jpa.repository.Query;

import com.zy.common.dao.CommonDao;
import com.zy.vote.entity.VoteMemberLog;

public interface VoteMemberLogDao extends VoteMemberLogCustomDao,CommonDao<VoteMemberLog, String> {

	@Query(" select count(1) from VoteMemberLog l where l.member.id = ?1 and l.voteTopic.id = ?2")
	public int findMemberTopicLog(String memberId, String topicId);
}
