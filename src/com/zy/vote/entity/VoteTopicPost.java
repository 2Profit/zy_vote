package com.zy.vote.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import com.zy.common.entity.BaseEntity;
import com.zy.member.entity.Member;
/**
 * 投票主题帖子
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_topic_post")
public class VoteTopicPost extends BaseEntity{

	private static final long serialVersionUID = 2539439941089502853L;

	private VoteTopic voteTopic;
	@OrderBy("createDate desc")
	private List<VoteTopicPostReplay> postReplays;
	
	//发帖人
	private Member publisher;
	private String postContent;//评论内容
	private String ipAddress;//评论人IP
	private Integer praiseCount;//点赞总数
	private Integer reportCount;//举报总数
	
	
	//以下字段用于查询
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDateFrom;//评论时间开始
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDateTo;//评论时间截止
	
	@ManyToOne
	@JoinColumn(name="vote_topic_id")
	public VoteTopic getVoteTopic() {
		return voteTopic;
	}

	public void setVoteTopic(VoteTopic voteTopic) {
		this.voteTopic = voteTopic;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "voteTopicPost")
	public List<VoteTopicPostReplay> getPostReplays() {
		return postReplays;
	}

	public void setPostReplays(List<VoteTopicPostReplay> postReplays) {
		this.postReplays = postReplays;
	}
	@ManyToOne
	@JoinColumn(name="publisher_id")
	public Member getPublisher() {
		return publisher;
	}
	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}
	@Column(length=512)
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	@Column(length=64)
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	
	
	

	@javax.persistence.Transient
	public Date getCreateDateFrom() {
		return createDateFrom;
	}

	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}
	@javax.persistence.Transient
	public Date getCreateDateTo() {
		return createDateTo;
	}

	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}
	
	
	
}
