package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.service.CommonService;
import com.zy.vote.dao.VotePostPraiseDao;
import com.zy.vote.entity.VotePostPraise;

@Service
public class VotePostPraiseService extends CommonService<VotePostPraise,String>{

	@Autowired
	private VotePostPraiseDao votePostPraiseDao;
	
	@Autowired
	public void setVotePostPraiseDao(VotePostPraiseDao votePostPraiseDao) {
		super.setCommonDao(votePostPraiseDao);
	}
}
