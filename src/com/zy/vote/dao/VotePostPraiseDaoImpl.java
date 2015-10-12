package com.zy.vote.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteMemberLog;
import com.zy.vote.entity.VotePostPraise;

public class VotePostPraiseDaoImpl extends CustomBaseSqlDaoImpl implements VotePostPraiseCustomDao{

	@SuppressWarnings("unchecked")
	@Override
	public PageModel<VotePostPraise> queryForPage(VotePostPraise queryDto,
			PageModel<VotePostPraise> pageModel) {
		
		StringBuilder hql = new StringBuilder("select l from VotePostPraise l where 1=1 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(StringUtils.isNotBlank(queryDto.getId())){
			hql.append(" and l.id = :id ");
			params.put("id", queryDto.getId());
		}
		if(queryDto.getVoteTopicPost()!=null && StringUtils.isNotBlank(queryDto.getVoteTopicPost().getId())){
			hql.append(" and l.voteTopicPost.id = :voteTopicPostId ");
			params.put("voteTopicPostId", queryDto.getVoteTopicPost().getId());
		}

		if(queryDto.getMember()!=null && StringUtils.isNotBlank(queryDto.getMember().getId())){
			hql.append(" and l.member.id = :memberId ");
			params.put("memberId", queryDto.getMember().getId());
		}
		if(queryDto.getMember()!=null && StringUtils.isNotBlank(queryDto.getMember().getMobile())){
			hql.append(" and l.member.mobile = :mobile ");
			params.put("mobile", queryDto.getMember().getMobile());
		}
		if(queryDto.getMember()!=null && StringUtils.isNotBlank(queryDto.getMember().getEmail())){
			hql.append(" and l.member.email = :email ");
			params.put("email", queryDto.getMember().getEmail());
		}
		
		
		if(queryDto.getCreateDateFrom()!=null){
			hql.append(" and l.createDate >= :createDateFrom ");
			params.put("createDateFrom", queryDto.getCreateDateFrom());
		}
		
		if(queryDto.getCreateDateTo()!=null){
			hql.append(" and l.createDate = :createDateTo ");
			params.put("createDateTo", queryDto.getCreateDateTo());
		}

		
		hql.append(" order by l.updateDate desc ");
		
		return this.queryForPageWithParams(hql.toString(),params,pageModel.getCurrentPage(), pageModel.getPageSize());
	}

}
