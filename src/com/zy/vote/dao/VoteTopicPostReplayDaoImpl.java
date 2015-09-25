package com.zy.vote.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopicPostReplay;

public class VoteTopicPostReplayDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicPostReplayCustomDao{

	@SuppressWarnings("unchecked")
	@Override
	public PageModel<VoteTopicPostReplay> queryForPage(
			VoteTopicPostReplay queryDto,PageModel<VoteTopicPostReplay> pageModel) {
		StringBuilder hql = new StringBuilder("select l from VoteTopicPostReplay l where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(queryDto.getVoteTopic()!=null && StringUtils.isNotBlank(queryDto.getVoteTopic().getId())){
			hql.append(" and l.voteTopic.id = :voteTopicId ");
			params.put("voteTopicId", queryDto.getVoteTopic().getId());
		}
		
		if(StringUtils.isNotBlank(queryDto.getId())){
			hql.append(" and l.id = :id ");
			params.put("id", queryDto.getId());
		}
		if(queryDto.getVoteTopic()!=null && StringUtils.isNotBlank(queryDto.getVoteTopic().getId())){
			hql.append(" and l.voteTopic.id = :voteTopicId ");
			params.put("voteTopicId", queryDto.getVoteTopic().getId());
		}
		if(queryDto.getReplayer()!=null && StringUtils.isNotBlank(queryDto.getReplayer().getUserName())){
			hql.append(" and l.replayer.userName like :userName ");
			params.put("userName", "%"+queryDto.getReplayer().getUserName()+"%");
		}
		if(StringUtils.isNotBlank(queryDto.getReplayContent())){
			hql.append(" and l.replayContent like :replayContent ");
			params.put("replayContent", "%"+queryDto.getReplayContent()+"%");
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
