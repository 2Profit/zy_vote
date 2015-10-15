package com.zy.vote.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.zy.common.entity.BaseEntity;
import com.zy.member.entity.Member;

/**
 * 帖子回复的点赞
 * 
 * @author pingan
 * @since  20151015
 *
 */

public class VoteReplayPraise extends BaseEntity{

	private static final long serialVersionUID = 6218631205628959194L;

	private Member member;//举报人
	private VoteTopicPostReplay voteTopicPostReplay;
	private String ipAddress;//举报人IP地址

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne
	@JoinColumn(name="vote_post_replay_id")
	public VoteTopicPostReplay getVoteTopicPostReplay() {
		return voteTopicPostReplay;
	}

	public void setVoteTopicPostReplay(VoteTopicPostReplay voteTopicPostReplay) {
		this.voteTopicPostReplay = voteTopicPostReplay;
	}

	@Column(length=64)
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	
}
