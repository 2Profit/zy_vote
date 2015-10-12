package com.zy.vote.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopicPost;

public class VoteTopicPostDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicPostCustomDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	@SuppressWarnings("unchecked")
	public PageModel<VoteTopicPost> queryForPage(VoteTopicPost queryDto,
			PageModel<VoteTopicPost> pageModel) {
		
		StringBuilder hql = new StringBuilder("select l from VoteTopicPost l where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(queryDto.getId())){
			hql.append(" and l.id = :id ");
			params.put("id", queryDto.getId());
		}
		if(queryDto.getVoteTopic()!=null && StringUtils.isNotBlank(queryDto.getVoteTopic().getId())){
			hql.append(" and l.voteTopic.id = :voteTopicId ");
			params.put("voteTopicId", queryDto.getVoteTopic().getId());
		}
		if(StringUtils.isNotBlank(queryDto.getPostContent())){
			hql.append(" and l.postContent like :postContent ");
			params.put("postContent", "%"+queryDto.getPostContent()+"%");
		}
		if(queryDto.getPublisher()!=null && StringUtils.isNotBlank(queryDto.getPublisher().getMobile())){
			hql.append(" and l.publisher.usemobilerName like :mobile ");
			params.put("mobile", "%"+queryDto.getPublisher().getMobile()+"%");
		}
		if(queryDto.getPublisher()!=null && StringUtils.isNotBlank(queryDto.getPublisher().getEmail())){
			hql.append(" and l.publisher.email like :email ");
			params.put("email", queryDto.getPublisher().getEmail());
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
	
	@Override
	public void updateDeleteFlag(String[] ids,Integer isDelete){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("isDelete", isDelete);
		params.put("ids", Arrays.asList(ids));
		namedParameterJdbcTemplate.update("update vote_topic_post set delete_flag = :isDelete, update_date = now() where id in (:ids) ", params);
	}


}
