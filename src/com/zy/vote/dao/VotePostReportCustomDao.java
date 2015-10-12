package com.zy.vote.dao;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VotePostReport;

public interface VotePostReportCustomDao {

	PageModel<VotePostReport> queryForPage(VotePostReport queryDto,PageModel<VotePostReport> pageModel);
}
