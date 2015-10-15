package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteReplayPraiseDao;
import com.zy.vote.entity.VoteReplayPraise;

@Service
public class VoteReplayPraiseService extends CommonService<VoteReplayPraise,String>{

	@Autowired
	private VoteReplayPraiseDao voteReplayPraiseDao;
	
	@Autowired
	public void setVoteReplayPraiseDao(VoteReplayPraiseDao voteReplayPraiseDao) {
		super.setCommonDao(voteReplayPraiseDao);
	}
	
	public int findMemberPraise(String replayId, String memberId){
		return voteReplayPraiseDao.findMemberPraise(replayId, memberId);
	}
}
