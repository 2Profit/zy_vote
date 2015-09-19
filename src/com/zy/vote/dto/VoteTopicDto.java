package com.zy.vote.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zy.common.entity.PageModel;
import com.zy.vote.entity.VoteTopic;

public class VoteTopicDto extends PageModel<VoteTopic>{

	private static final long serialVersionUID = 5134167590120542266L;
	
	private String id;//主键
	private String optionId;//主题投票选项
	private Date startDate;//开始日期
	private int lastDays;//投票持续天数
	private String titleContent;//题目内容
	private String isDisplay;//是否开启显示功能（1-开启，0-关闭）
	private String displayPosition;//显示位置（0-主，1-副）
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDateBegin;//查询时间（开始）
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDateEnd;//查询时间（结束）
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
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
	public String getTitleContent() {
		return titleContent;
	}
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}
	public String getIsDisplay() {
		return isDisplay;
	}
	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}
	public String getDisplayPosition() {
		return displayPosition;
	}
	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}
	public Date getStartDateBegin() {
		return startDateBegin;
	}
	public void setStartDateBegin(Date startDateBegin) {
		this.startDateBegin = startDateBegin;
	}
	public Date getStartDateEnd() {
		return startDateEnd;
	}
	public void setStartDateEnd(Date startDateEnd) {
		this.startDateEnd = startDateEnd;
	}
	
	
	
}
