package com.zy.vote.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteMemberLog;

public class VoteMemberLogDaoImpl extends CustomBaseSqlDaoImpl implements VoteMemberLogCustomDao{

	@Override
	@SuppressWarnings("unchecked")
	public PageModel<VoteMemberLog> queryForPage(VoteMemberLog queryDto, 
			PageModel<VoteMemberLog> pageModel) {
		
		StringBuilder hql = new StringBuilder("select l from VoteMemberLog l where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		
		if(StringUtils.isNotBlank(queryDto.getId())){
			hql.append(" and l.id = :id ");
			params.put("id", queryDto.getId());
		}

		if(queryDto.getVoteTopic()!=null && StringUtils.isNotBlank(queryDto.getVoteTopic().getId())){
			hql.append(" and l.voteTopic.id = :voteTopicId ");
			params.put("voteTopicId", queryDto.getVoteTopic().getId());
		}
		
		if(queryDto.getMember()!=null && StringUtils.isNotBlank(queryDto.getMember().getUserName())){
			hql.append(" and l.member.userName like :userName ");
			params.put("userName", "%"+queryDto.getMember().getUserName()+"%");
		}
		
		if(queryDto.getCreateDateFrom()!=null){
			hql.append(" and l.createDate >= :createDateFrom ");
			params.put("createDateFrom", queryDto.getCreateDateFrom());
		}
		if(queryDto.getCreateDateTo()!=null){
			hql.append(" and l.createDate <= :createDateTo ");
			params.put("createDateTo", queryDto.getCreateDateTo());
		}
		
		hql.append(" order by l.updateDate desc ");
		
		return this.queryForPageWithParams(hql.toString(),params,pageModel.getCurrentPage(), pageModel.getPageSize());
	}

}
