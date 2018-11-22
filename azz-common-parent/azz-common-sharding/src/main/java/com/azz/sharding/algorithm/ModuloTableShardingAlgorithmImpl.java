package com.azz.sharding.algorithm;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.azz.core.sequence.core.BaseService;
import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:25:36
 */
public final class ModuloTableShardingAlgorithmImpl implements SingleKeyTableShardingAlgorithm<Long> ,BaseService{

	public String doEqualSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
        int mod = tableNames.size();
        for (String each : tableNames) {
            if (each.endsWith(shardingValue.getValue() % mod + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    public Collection<String> doInSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
        int mod = tableNames.size();
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value % mod + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    public Collection<String> doBetweenSharding(final Collection<String> tableNames, final ShardingValue<Long> shardingValue) {
        int mod = tableNames.size();
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
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
