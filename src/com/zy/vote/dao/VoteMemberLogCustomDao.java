package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteMemberLog;

public interface VoteMemberLogCustomDao {

	PageModel<VoteMemberLog> queryForPage(VoteMemberLog queryDto,PageModel<VoteMemberLog> pageModel);
}
