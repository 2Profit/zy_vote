package com.zy.vote.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.dto.VoteTopicDto;
import com.zy.vote.entity.VoteTopic;

public class VoteTopicDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicCustomDao{

	@SuppressWarnings("unchecked")
	@Override
	public PageModel<VoteTopic> queryForPage(VoteTopicDto queryDto) {
		
		StringBuilder hql = new StringBuilder("select l from VoteTopic l where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(queryDto.getId())){
			hql.append(" and l.id = :id ");
			params.put("id", queryDto.getId());
		}
		if(StringUtils.isNotBlank(queryDto.getTitleContent())){
			hql.append(" and l.titleContent like :titleContent ");
			params.put("titleContent", "%"+queryDto.getTitleContent()+"%");
		}

		if(StringUtils.isNotBlank(queryDto.getDisplayPosition())){
			hql.append(" and l.displayPosition = :displayPosition ");
			params.put("displayPosition", queryDto.getDisplayPosition());
		}
		if(StringUtils.isNotBlank(queryDto.getIsDisplay())){
			hql.append(" and l.isDisplay = :isDisplay ");
			params.put("isDisplay", queryDto.getIsDisplay());
		}
		if(queryDto.getStartDateBegin()!=null){
			hql.append(" and l.startDate >= :startDateBegin ");
			params.put("startDateBegin", queryDto.getStartDateBegin());
		}
		if(queryDto.getStartDateEnd()!=null){
			hql.append(" and l.startDate <= :startDateEnd ");
			params.put("startDateEnd", queryDto.getStartDateEnd());
		}
		
		hql.append(" order by l.updateDate desc ");
		
		return this.queryForPageWithParams(hql.toString(),params,queryDto.getCurrentPage(), queryDto.getPageSize());
	}
	
	

}
