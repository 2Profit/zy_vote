package com.zy.vote.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

	private Member member;
	
	private VoteTopicPost voteTopicPost;

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
	
}
