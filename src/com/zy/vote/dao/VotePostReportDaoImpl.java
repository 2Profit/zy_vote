package com.zy.vote.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.common.util.HumpPropertyBeanProcessor;
import com.zy.vote.dto.ReportUnionDto;
import com.zy.vote.entity.VotePostReport;

public class VotePostReportDaoImpl extends CustomBaseSqlDaoImpl implements VotePostReportCustomDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private QueryRunner qr = new QueryRunner();
	private static ResultSetHandler<List<ReportUnionDto>> rsh = 
    		new BeanListHandler<ReportUnionDto>(ReportUnionDto.class, new BasicRowProcessor(new HumpPropertyBeanProcessor()));
	
	/**
	 * vote_post_report 联合 vote_replay_report，方便同一个页面展示，便于删除跟帖操作
	 */
	@Override
	public PageModel<ReportUnionDto> queryPage(ReportUnionDto queryDto, PageModel<ReportUnionDto> pageModal) {
		PageModel<ReportUnionDto> result = new PageModel<ReportUnionDto>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select tmp.* from ");
		sb.append("  (select p.id,p.post_content,p.report_count,m.cn_name,u.mobile,r.ip_address,r.create_date,'1' as type,p.delete_flag ");
		sb.append("		from vote_post_report r ");
		sb.append("		left join vote_topic_post p on p.id=r.vote_topic_post_id ");
		sb.append("     left join mem_member m on m.id=r.member_id ");
		sb.append("		left join mem_user u on u.id=m.id ");
		sb.append("		union ");
		sb.append("	  select r3.id,r3.replay_content,r3.report_count,m2.cn_name,u2.mobile,r2.ip_address,r2.create_date,'2' as type,r3.delete_flag ");
		sb.append("		from vote_replay_report r2 ");
		sb.append("		left join vote_topic_post_replay r3 on r3.id=r2.vote_post_replay_id ");
		sb.append("		left join mem_member m2 on m2.id=r2.member_id ");
		sb.append("		left join mem_user u2 on u2.id=m2.id) as tmp where 1=1 ");
		
		if(StringUtils.isNotBlank(queryDto.getPostContent())){
			sb.append(" and tmp.post_content like '%").append(queryDto.getPostContent()).append("%' ");
		}
		if(StringUtils.isNotBlank(queryDto.getIpAddress())){
			sb.append(" and tmp.ip_address like '%").append(queryDto.getIpAddress()).append("%' ");
		}
		if(StringUtils.isNotBlank(queryDto.getCnName())){
			sb.append(" and tmp.cn_name like '%").append(queryDto.getCnName()).append("%' ");
		}
		if(StringUtils.isNotBlank(queryDto.getMobile())){
			sb.append(" and tmp.mobile like '%").append(queryDto.getMobile()).append("%' ");
		}
		if(queryDto.getCreateDateFrom()!=null){
			sb.append(" and tmp.create_date >= ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(queryDto.getCreateDateFrom()));
		}
		if(queryDto.getCreateDateTo()!=null){
			sb.append(" and tmp.create_date <= ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(queryDto.getCreateDateTo()));
		}
		
		sb.append("  order by tmp.create_date desc ");
		
		int currentPage = pageModal.getCurrentPage();
		int pageSize = pageModal.getPageSize();
		
		int totalCount = this.getCount(sb.toString());
		if(totalCount > 0 ){
			
			List<ReportUnionDto> dataList;
			try {
				sb.append(" limit ").append(pageSize).append(" offset ").append((currentPage-1)*pageSize);
				
				Connection connection = jdbcTemplate.getDataSource().getConnection();
				dataList = qr.query(connection,sb.toString(),rsh);
				connection.close();
				
				result.setCurrentPage(currentPage);
				result.setPageSize(pageSize);
				result.setTotalCount(totalCount);
				result.setList(dataList);
				
				int totalPage = 0;
				if(totalCount % pageSize == 0){
					totalPage = totalCount/pageSize;
				}else{
					totalPage = totalCount/pageSize + 1;
				}
				result.setTotalPage(totalPage);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageModel<VotePostReport> queryForPage(VotePostReport queryDto,
			PageModel<VotePostReport> pageModel) {
		
		StringBuilder hql = new StringBuilder("select l from VotePostReport l where 1=1 ");
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
