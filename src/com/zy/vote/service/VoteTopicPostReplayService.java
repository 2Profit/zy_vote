package com.zy.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.entity.PageModel;
import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteTopicPostReplayDao;
import com.zy.vote.entity.VoteTopicPostReplay;

@Service
public class VoteTopicPostReplayService extends CommonService<VoteTopicPostReplay,String>{

	@Autowired
	private VoteTopicPostReplayDao voteTopicPostReplayDao;
	
	@Autowired
	public void setVoteTopicPostReplayDao(VoteTopicPostReplayDao voteTopicPostReplayDao) {
		super.setCommonDao(voteTopicPostReplayDao);
	}
	
	public PageModel<VoteTopicPostReplay> queryForPage(VoteTopicPostReplay queryDto,
			PageModel<VoteTopicPostReplay> pageModel){
		return voteTopicPostReplayDao.queryForPage(queryDto, pageModel);
	}
	
	public List<VoteTopicPostReplay> getByTopicId(String topicId){
		return voteTopicPostReplayDao.getByTopicId(topicId);
	}
}
