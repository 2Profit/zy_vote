package com.zy.vote.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	//发帖人
	private Member publisher;

	@ManyToOne
	@JoinColumn(name="vote_topic_id")
	public VoteTopic getVoteTopic() {
		return voteTopic;
	}

	public void setVoteTopic(VoteTopic voteTopic) {
		this.voteTopic = voteTopic;
	}

	@ManyToOne
	@JoinColumn(name="publisher_id")
	public Member getPublisher() {
		return publisher;
	}

	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}
	
}
