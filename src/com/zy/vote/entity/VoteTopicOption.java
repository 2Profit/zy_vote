package com.zy.vote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zy.common.entity.BaseEntity;

/**
 * 投票主题选项
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_topic_option")
public class VoteTopicOption extends BaseEntity{

	private static final long serialVersionUID = -1622217961046399009L;

	private VoteTopic voteTopic;
	private String optionContent;//选项内容
	private Integer voteCount;//选项被投票累计次数

	
	//以下数据用于页面传值
	private String[] idsAdjust;//对应Id的选项，投票累计数修改id与修改数用【~】分隔
	
	@ManyToOne
	@JoinColumn(name="vote_topic_id")
	public VoteTopic getVoteTopic() {
		return voteTopic;
	}

	public void setVoteTopic(VoteTopic voteTopic) {
		this.voteTopic = voteTopic;
	}

	@Column(length=256)
	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	@javax.persistence.Transient
	public String[] getIdsAdjust() {
		return idsAdjust;
	}

	public void setIdsAdjust(String[] idsAdjust) {
		this.idsAdjust = idsAdjust;
	}

	
	
}
