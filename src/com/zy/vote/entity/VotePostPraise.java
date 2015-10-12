package com.zy.vote.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.zy.common.entity.BaseEntity;
import com.zy.member.entity.Member;

/**
 * 帖子点赞表
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_post_praise")
public class VotePostPraise extends BaseEntity{

	private static final long serialVersionUID = -339268793436158879L;

	private Member member;//点赞用户
	
	private VoteTopicPost voteTopicPost;//点赞帖子
	
	private String ipAddress;//点赞人IP地址
	
	//用于后台页面查询
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDateFrom;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDateTo;

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne
	@JoinColumn(name="vote_topic_post_id")
	public VoteTopicPost getVoteTopicPost() {
		return voteTopicPost;
	}

	public void setVoteTopicPost(VoteTopicPost voteTopicPost) {
		this.voteTopicPost = voteTopicPost;
	}

	@Column(length=64)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
