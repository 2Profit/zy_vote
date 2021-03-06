package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.entity.PageModel;
import com.zy.common.service.CommonService;
import com.zy.vote.dao.VotePostPraiseDao;
import com.zy.vote.entity.VotePostPraise;
import com.zy.vote.entity.VotePostReport;

@Service
public class VotePostPraiseService extends CommonService<VotePostPraise,String>{

	@Autowired
	private VotePostPraiseDao votePostPraiseDao;
	
	@Autowired
	public void setVotePostPraiseDao(VotePostPraiseDao votePostPraiseDao) {
		super.setCommonDao(votePostPraiseDao);
	}
	
	public int findMemberPraise(String memberId, String postId){
		return votePostPraiseDao.findMemberPraise(memberId, postId);
	}
	
	public PageModel<VotePostPraise> queryForPage(VotePostPraise queryDto,
			PageModel<VotePostPraise> pageModel){
		
		return votePostPraiseDao.queryForPage(queryDto, pageModel);
	}
}
