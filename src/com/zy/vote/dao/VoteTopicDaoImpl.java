package com.zy.vote.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.dto.VoteTopicDto;
import com.zy.vote.entity.VoteTopic;

public class VoteTopicDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicCustomDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
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
		if(StringUtils.isNotBlank(queryDto.getDisplayType())){
			hql.append(" and l.displayType = :displayType ");
			params.put("displayType", queryDto.getDisplayType());
		}
		if(queryDto.getIsComment()!=null){
			hql.append(" and l.isComment = :isComment ");
			params.put("isComment", queryDto.getIsComment());
		}
		if(queryDto.getStartDateBegin()!=null){
			hql.append(" and l.startDate >= :startDateBegin ");
			params.put("startDateBegin", queryDto.getStartDateBegin());
		}
		if(queryDto.getStartDateEnd()!=null){
			hql.append(" and l.startDate <= :startDateEnd ");
			params.put("startDateEnd", queryDto.getStartDateEnd());
		}
		if(queryDto.getToDateBegin()!=null){
			hql.append(" and l.startDate >= :startDateBegin ");
			params.put("startDateBegin", queryDto.getToDateBegin());
		}
		if(queryDto.getToDateEnd()!=null){
			hql.append(" and l.endDate <= :toDateEnd ");
			params.put("toDateEnd", queryDto.getToDateEnd());
		}
		
		hql.append(" order by l.updateDate desc ");
		
		return this.queryForPageWithParams(hql.toString(),params,queryDto.getCurrentPage(), queryDto.getPageSize());
	}
	
	@Override
	public void updateDeleteFlag(String[] ids,Integer isDelete){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("isDelete", isDelete);
		params.put("ids", Arrays.asList(ids));
		namedParameterJdbcTemplate.update("update vote_topic set delete_flag = :isDelete, update_date = now() where id in (:ids) ", params);
	}

}
