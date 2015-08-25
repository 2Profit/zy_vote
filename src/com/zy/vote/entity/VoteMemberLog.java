package com.zy.vote.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zy.common.entity.BaseEntity;
import com.zy.member.entity.Member;

/**
 * 用户投票日志
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_member_log")
public class VoteMemberLog extends BaseEntity{

	private static final long serialVersionUID = 4457309321856124971L;

	private Member member;
	
	private VoteTopic voteTopic;
	
	private VoteTopicOption voteTopicOption;

	@ManyToOne
	@JoinColumn(name="member_id")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@ManyToOne
	@JoinColumn(name="vote_topic_id")
	public VoteTopic getVoteTopic() {
		return voteTopic;
	}

	public void setVoteTopic(VoteTopic voteTopic) {
		this.voteTopic = voteTopic;
	}

	@ManyToOne
	@JoinColumn(name="vote_topic_option_id")
	public VoteTopicOption getVoteTopicOption() {
		return voteTopicOption;
	}

	public void setVoteTopicOption(VoteTopicOption voteTopicOption) {
		this.voteTopicOption = voteTopicOption;
	}

}
