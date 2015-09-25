package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopicPost;

public interface VoteTopicPostCustomDao {

	PageModel<VoteTopicPost> queryForPage(VoteTopicPost queryDto,PageModel<VoteTopicPost> pageModel);
}
