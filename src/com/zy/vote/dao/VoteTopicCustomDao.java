package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.dto.VoteTopicDto;
import com.zy.vote.entity.VoteTopic;

public interface VoteTopicCustomDao {

	PageModel<VoteTopic> queryForPage(VoteTopicDto queryDto);
	
	public void updateDeleteFlag(String[] ids,Integer isDelete);
}
