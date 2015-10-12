package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VotePostPraise;

public interface VotePostPraiseCustomDao {

	PageModel<VotePostPraise> queryForPage(VotePostPraise queryDto,PageModel<VotePostPraise> pageModel);
}
