package com.zy.vote.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import com.zy.common.entity.BaseEntity;

/**
 * 投票主题
 * @author Jeff Xu
 *
 */
@Entity
@Table(name = "vote_topic")
public class VoteTopic extends BaseEntity{

	private static final long serialVersionUID = -99057880624174607L;

	private List<VoteTopicOption> options;//主题投票选项
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;//开始日期
	private int lastDays;//投票持续天数
	private String titleContent;//题目内容
	private String isDisplay;//是否开启显示功能（1-开启，0-关闭）
	private String displayPosition;//显示位置（1-主，0-副）
	private Integer praiseCount;//点赞总数
	private Integer reportCount;//举报总数
	
	private String[] optionContent;//选项内容
	
	@JsonIgnore
	@OneToMany(mappedBy = "voteTopic")
	public List<VoteTopicOption> getOptions() {
		return options;
	}
	public void setOptions(List<VoteTopicOption> options) {
		this.options = options;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getLastDays() {
		return lastDays;
	}
	public void setLastDays(int lastDays) {
		this.lastDays = lastDays;
	}
	@Column(length=128)
	public String getTitleContent() {
		return titleContent;
	}
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}
	@Column(length=2)
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	@Column(length=2)
	public String getDisplayPosition() {
		return displayPosition;
	}
	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
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
	public String[] getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String[] optionContent) {
		this.optionContent = optionContent;
	}
	
	
}
