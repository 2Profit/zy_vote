package com.zy.vote.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopicPost;

public class VoteTopicPostDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicPostCustomDao{

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
		if(queryDto.getPublisher()!=null && StringUtils.isNotBlank(queryDto.getPublisher().getUserName())){
			hql.append(" and l.publisher.userName like :userName ");
			params.put("userName", "%"+queryDto.getPublisher().getUserName()+"%");
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
