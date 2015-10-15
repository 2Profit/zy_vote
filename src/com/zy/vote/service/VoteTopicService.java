package com.zy.vote.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zy.common.entity.PageModel;
import com.zy.common.service.CommonService;
import com.zy.vote.dao.VoteTopicDao;
import com.zy.vote.dto.VoteTopicDto;
import com.zy.vote.entity.VoteTopic;
import com.zy.vote.entity.VoteTopicOption;

@Service
public class VoteTopicService extends CommonService<VoteTopic,String>{

	@Autowired
	private VoteTopicDao voteTopicDao;
	@Autowired
	private VoteTopicOptionService  voteTopicOptionService;
	
	@Autowired
	public void setVoteTopicDao(VoteTopicDao voteTopicDao) {
		super.setCommonDao(voteTopicDao);
	}
	
	public PageModel<VoteTopic> queryPage(VoteTopicDto queryDto){
		return voteTopicDao.queryForPage(queryDto);
	}
	
	public int findTopicByStartDate(Date startDate){
		return voteTopicDao.findTopicByEndDate(startDate);
	}
	
	public int findTopicByEndDate(Date startDate){
		return voteTopicDao.findTopicByEndDate(startDate);
	}
	
	/**
	 * 当前投票主题
	 * 为保证页面排版不乱，一定有投票主题返回
	 * @return
	 */
	public VoteTopic getCurrentTopic(){
		List<VoteTopic> topics = voteTopicDao.getCurrentTopic();
		if(CollectionUtils.isNotEmpty(topics)){
			return topics.get(0);
		}else{
			return voteTopicDao.getOrderedTopic().get(0);
		}
	}
	
	/**
	 * 下期投票主题
	 * 为保证页面排版不乱，一定有投票主题返回
	 * @return
	 */
	public VoteTopic getNextTopic(){
		List<VoteTopic> topics = voteTopicDao.getNextTopic();
		if(CollectionUtils.isNotEmpty(topics)){
			return topics.get(0);
		}else{
			return voteTopicDao.getOrderedTopic().get(0);
		}
	}
	
	/**
	 * 更多投票
	 * @return
	 */
	public List<VoteTopic> getHistoryTopics(){
		return voteTopicDao.getOrderedTopic();
	}
	
	public void updateDeleteFlag(String[] ids,Integer isDelete){
		voteTopicDao.updateDeleteFlag(ids, isDelete);
	}
	
	/**
	 * 新增投票主题、投票选项
	 * @param dto
	 */
	public void saveTopicAndOptions(VoteTopic dto){
		if(dto.getOptionContent()==null || dto.getOptionContent().length<1)
			throw new RuntimeException("选项内容空");
		VoteTopic entity = voteTopicDao.save(dto);
		for(String optionContent:dto.getOptionContent()){
			VoteTopicOption option = new VoteTopicOption();
			option.setVoteTopic(entity);
			option.setOptionContent(optionContent);
			option.setCreateName(dto.getCreateName());
			voteTopicOptionService.save(option);
		}
	}
	
	/**
	 * 修改投票主题、投票选项
	 * @param dto
	 */
	public void updateTopicAndOptions(VoteTopic dto){
		if(dto.getOptionContent()==null || dto.getOptionContent().length<1)
			throw new RuntimeException("选项内容空");
		VoteTopic entity = voteTopicDao.save(dto);
		List<VoteTopicOption> options = voteTopicOptionService.getOptionByVoteTopic(dto.getId());
		for(int i=0; i<options.size(); i++){
			VoteTopicOption option = options.get(i);
			option.setOptionContent(dto.getOptionContent()[i]);
			option.setCreateName(dto.getCreateName());
			voteTopicOptionService.update(option);
		}
		//编辑的时候新增了选项
		if(dto.getOptionContent().length>options.size()){
			for(int i=options.size(); i<dto.getOptionContent().length; i++){
				VoteTopicOption option = new VoteTopicOption();
				option.setOptionContent(dto.getOptionContent()[i]);
				option.setVoteTopic(entity);
				option.setCreateName(dto.getCreateName());
				voteTopicOptionService.save(option);
			}
		}
	}
}
