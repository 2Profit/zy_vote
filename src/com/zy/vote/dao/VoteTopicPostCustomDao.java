package com.zy.vote.dao;

import java.util.List;

import com.zy.common.entity.PageModel;
import com.zy.vote.dto.PostUnionReplayDto;
import com.zy.vote.entity.VoteTopicPost;

public interface VoteTopicPostCustomDao {

	PageModel<VoteTopicPost> queryForPage(VoteTopicPost queryDto,PageModel<VoteTopicPost> pageModel);
	
	public void updateDeleteFlag(String[] ids,Integer isDelete);
	
	List<VoteTopicPost> queryMostPraisePost(String topicId);
	
	PageModel<PostUnionReplayDto> queryPage(PostUnionReplayDto queryDto, PageModel<PostUnionReplayDto> pageModal);
	
}
