package com.zy.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteMemberLogDao;
import com.zy.vote.entity.VoteMemberLog;

@Service
public class VoteMemberLogService extends CommonService<VoteMemberLog,String>{

	@Autowired
	private VoteMemberLogDao voteMemberLogDao;
	
	@Autowired
	public void setVoteMemberLogDao(VoteMemberLogDao voteMemberLogDao) {
		super.setCommonDao(voteMemberLogDao);
	}
	
}
