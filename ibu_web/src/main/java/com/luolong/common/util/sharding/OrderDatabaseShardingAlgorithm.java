package com.luolong.common.util.sharding;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;


public class OrderDatabaseShardingAlgorithm  implements SingleKeyDatabaseShardingAlgorithm<Comparable<?>>{

    private final static Logger logger = LoggerFactory.getLogger(OrderDatabaseShardingAlgorithm.class);
    /**
     * 查询默认数据库
     * 不实现接口可能会导致找不到数据源或查询多个数据库的数据
     */
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames,ShardingValue<Comparable<?>> shardingValue) {
		return "db_1";
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames,ShardingValue<Comparable<?>> shardingValue) {
		Set<String> databaseSet = new HashSet<String>();
		databaseSet.add("db_1");
		return databaseSet;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,ShardingValue<Comparable<?>> shardingValue) {
		Set<String> databaseSet = new HashSet<String>();
		databaseSet.add("db_1");
		return databaseSet;
	}

    
}