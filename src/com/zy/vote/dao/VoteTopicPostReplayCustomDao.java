package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopicPostReplay;

public interface VoteTopicPostReplayCustomDao {

	PageModel<VoteTopicPostReplay> queryForPage(VoteTopicPostReplay queryDto,PageModel<VoteTopicPostReplay> pageModel);
	
	public void deleteById(String[] ids);
}
