package com.zy.vote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteTopicOptionDao;
import com.zy.vote.entity.VoteTopicOption;

@Service
public class VoteTopicOptionService extends CommonService<VoteTopicOption,String>{

	@Autowired
	private VoteTopicOptionDao voteTopicOptionDao;
	
	@Autowired
	public void setVoteTopicOptionDao(VoteTopicOptionDao voteTopicOptionDao) {
		super.setCommonDao(voteTopicOptionDao);
	}
	
	public List<VoteTopicOption> getOptionByVoteTopic(String voteTopicId){
		return voteTopicOptionDao.getOptionByVoteTopic(voteTopicId);
	}
}
