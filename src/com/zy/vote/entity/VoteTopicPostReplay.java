package com.zy.vote.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
}
