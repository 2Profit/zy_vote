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
 * 投票主题帖子回复
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_topic_post_replay")
public class VoteTopicPostReplay extends BaseEntity{

	private static final long serialVersionUID = -8803639319841843405L;

    private VoteTopic voteTopic;
    
    private VoteTopicPost voteTopicPost;
	
	//回复人
	private Member replayer;
	private String replayContent;//回复内容
	private String ipAddress;//评论人IP
	
	
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

	@ManyToOne
	@JoinColumn(name="vote_topic_post_id")
	public VoteTopicPost getVoteTopicPost() {
		return voteTopicPost;
	}

	public void setVoteTopicPost(VoteTopicPost voteTopicPost) {
		this.voteTopicPost = voteTopicPost;
	}

	@ManyToOne
	@JoinColumn(name="replayer_id")
	public Member getReplayer() {
		return replayer;
	}

	public void setReplayer(Member replayer) {
		this.replayer = replayer;
	}

	@Column(length=512)
	public String getReplayContent() {
		return replayContent;
	}

	public void setReplayContent(String replayContent) {
		this.replayContent = replayContent;
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
