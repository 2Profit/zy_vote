package com.zy.vote.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zy.common.dao.CustomBaseSqlDaoImpl;
import com.zy.common.entity.PageModel;
import com.zy.common.util.HumpPropertyBeanProcessor;
import com.zy.vote.dto.PostUnionReplayDto;
import com.zy.vote.entity.VoteTopicPost;

public class VoteTopicPostDaoImpl extends CustomBaseSqlDaoImpl implements VoteTopicPostCustomDao{

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private QueryRunner qr = new QueryRunner();
	private static ResultSetHandler<List<PostUnionReplayDto>> rsh = 
    		new BeanListHandler<PostUnionReplayDto>(PostUnionReplayDto.class, new BasicRowProcessor(new HumpPropertyBeanProcessor()));
	
	/**
	 * vote_topic_post 联合 vote_topic_post_replay，方便同一个页面展示，便于删除跟帖操作
	 */
	@Override
	public PageModel<PostUnionReplayDto> queryPage(PostUnionReplayDto queryDto, PageModel<PostUnionReplayDto> pageModal) {
		PageModel<PostUnionReplayDto> result = new PageModel<PostUnionReplayDto>();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select tmp.* from ");
		sb.append("  (select t.id topic_id,t.title_content,p.id post_id,p.post_content,p.create_date,p.ip_address,");
		sb.append(" 	p.report_count,u.mobile,m.cn_name,'1' as type,p.delete_flag ");
		sb.append("		from vote_topic_post p  ");
		sb.append("		left JOIN vote_topic t on t.id = p.vote_topic_id  ");
		sb.append("     left join mem_member m on m.id = p.publisher_id ");
		sb.append("		left join mem_user u on u.id = m.id ");
		sb.append("		union ");
		sb.append("	  select t2.id topic_id,t2.title_content,r.id post_id,r.replay_content post_content,r.create_date,r.ip_address,");
		sb.append("		r.report_count,u2.mobile,m2.cn_name,'2' as type,r.delete_flag ");
		sb.append("		from vote_topic_post_replay r ");
		sb.append("		left join vote_topic_post p2 on p2.id = r.vote_topic_post_id ");
		sb.append("		left join vote_topic t2 on t2.id = p2.vote_topic_id ");
		sb.append("		left join mem_member m2 on m2.id = r.replayer_id ");
		sb.append("		left join mem_user u2 on u2.id = m2.id ) as tmp where 1=1 ");
		
		if(StringUtils.isNotBlank(queryDto.getPostId())){
			sb.append(" and tmp.post_id = '").append(queryDto.getPostId()).append("' ");
		}
		if(StringUtils.isNotBlank(queryDto.getTitleContent())){
			sb.append(" and tmp.title_content like '%").append(queryDto.getTitleContent()).append("%' ");
		}
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
			
			List<PostUnionReplayDto> dataList;
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
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<VoteTopicPost> queryMostPraisePost(String topicId) {
		
		StringBuilder hql = new StringBuilder("select l from VoteTopicPost l where 1=1 and l.deleteFlag=0 ");
		Map<String,Object> params = new HashMap<String,Object>();
		
		if(StringUtils.isNoneBlank(topicId)){
			hql.append(" and l.voteTopic.id = :topicId ");
			params.put("topicId", topicId);
		}
		
		hql.append(" order by l.praiseCount desc ");
		
		return this.queryForPageWithParams(hql.toString(),params,1,2).getList();
	}
	
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
		if(queryDto.getDeleteFlag()!=null){
			hql.append(" and l.deleteFlag = :deleteFlag ");
			params.put("deleteFlag", queryDto.getDeleteFlag());
		}
		
		if(StringUtils.isNotBlank(queryDto.getOrderByParam())){
			hql.append(" order by l.").append(queryDto.getOrderByParam()).append(" desc ");
		}else{
			hql.append(" order by l.updateDate desc ");
		}
		
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
