package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.entity.PageModel;
import com.zy.common.service.CommonService;
import com.zy.vote.dao.VotePostReportDao;
import com.zy.vote.dto.ReportUnionDto;
import com.zy.vote.entity.VotePostReport;

@Service
public class VotePostReportService extends CommonService<VotePostReport,String>{

	@Autowired
	private VotePostReportDao votePostReportDao;
	
	@Autowired
	public void setVotePostReportDao(VotePostReportDao votePostReportDao) {
		super.setCommonDao(votePostReportDao);
	}
	
	public int findMemberPostReport(String memberId, String postId){
		return votePostReportDao.findMemberPostReport(memberId, postId);
	}
	
	public PageModel<VotePostReport> queryForPage(VotePostReport queryDto,
			PageModel<VotePostReport> pageModel){
		
		return votePostReportDao.queryForPage(queryDto, pageModel);
	}
	
	public PageModel<ReportUnionDto> queryPage(ReportUnionDto queryDto, 
			PageModel<ReportUnionDto> pageModal){
		
		return votePostReportDao.queryPage(queryDto, pageModal);
	}
}
