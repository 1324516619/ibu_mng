package com.luolong.common.util.sharding;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.luolong.common.util.DateUtil;
import com.luolong.common.util.DateUtils;


public class OrderTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Comparable<?>>{
	private final static Logger logger = LoggerFactory.getLogger(OrderTableShardingAlgorithm.class);
   
	@Override
    public Collection<String> doBetweenSharding(Collection<String> arg0,ShardingValue<Comparable<?>> arg1){
		Set<String> result = new HashSet<String>();
		String[] createDate = null;
		List<String> dateList = new ArrayList<String>();
	    try{
			if(StringUtils.equals("create_date", arg1.getColumnName())){
				createDate = arg1.getValueRange().toString().replaceAll("\\[", "").replaceAll("\\]", "").trim().split("‥");
				if (null != createDate && createDate.length > 0) {
					dateList = DateUtils.getMonthBetween(createDate[0], createDate[1]);
					if (dateList.size() > 0) {
						for (String date : dateList) {
							 StringBuffer tableName = new StringBuffer(arg1.getLogicTableName());
							 tableName.append("_").append(date.replace("-", ""));
							 result.add(tableName.toString());
						}
					}
				}else{
					 logger.error("时间是必须的分表字段！");
				}
	        }        
		}catch(Exception e){
            logger.error("分表规则计算错误。");
            logger.error(e.getMessage(),e);
        }
        return result;
    }

    @Override
    public String doEqualSharding(Collection<String> arg0,ShardingValue<Comparable<?>> arg1){
    	String createDate = "";
    	StringBuffer tableName = new StringBuffer(arg1.getLogicTableName());
        try{
            if(StringUtils.equals("create_date", arg1.getColumnName())){
                createDate = arg1.getValue().toString();
            }
            if(StringUtils.isNotEmpty(createDate)){
            	tableName.append("_").append(DateUtil.format(DateUtil.start2Date(createDate), "yyyyMM"));
            }else{
                logger.error("时间是必须的分表字段！");
            }
        }catch(Exception e){
            logger.error("分表规则计算错误。");
            logger.error(e.getMessage(),e);
        }
        return tableName.toString();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> arg0,ShardingValue<Comparable<?>> arg1){
        return null;
    }

}
