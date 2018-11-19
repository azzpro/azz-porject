package com.azz.sharding.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.azz.core.sequence.core.BaseService;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:25:47
 */
public class ModuloDatabaseShardingAlgorithmImpl implements SingleKeyDatabaseShardingAlgorithm<Long> ,BaseService{

	@Override
    public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
		int mod = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(shardingValue.getValue() % mod + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
        int mod = availableTargetNames.size();
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : availableTargetNames) {
                if (tableName.endsWith(value % mod + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
                                                ShardingValue<Long> shardingValue) {
        int mod = availableTargetNames.size();
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : availableTargetNames) {
                if (each.endsWith(i % mod + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public String getType() {
        return "hash";
    }
}