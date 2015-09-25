package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.entity.PageModel;
import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteTopicPostDao;
import com.zy.vote.entity.VoteTopicPost;

@Service
public class VoteTopicPostService extends CommonService<VoteTopicPost,String>{
	
	@Autowired
	private VoteTopicPostDao voteTopicPostDao;
	@Autowired
	private VoteTopicOptionService  voteTopicOptionService;
	
	@Autowired
	public void setVoteTopicPostDao(VoteTopicPostDao voteTopicPostDao) {
		super.setCommonDao(voteTopicPostDao);
	}
	
	public PageModel<VoteTopicPost> queryPage(VoteTopicPost queryDto,PageModel<VoteTopicPost> pageModel){
		return voteTopicPostDao.queryForPage(queryDto, pageModel);
	}

}
