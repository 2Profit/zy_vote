package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteReplayReportDao;
import com.zy.vote.entity.VoteReplayReport;

@Service
public class VoteReplayReportService extends CommonService<VoteReplayReport,String>{

	@Autowired
	private VoteReplayReportDao voteReplayReportDao;
	
	@Autowired
	public void setVoteReplayReportDao(VoteReplayReportDao voteReplayReportDao) {
		super.setCommonDao(voteReplayReportDao);
	}
	
	public int findMemberReport(String replayId, String memberId){
		return voteReplayReportDao.findMemberReport(replayId, memberId);
	}
}
