package com.azz.sharding.config;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:25:56
 */
public class ShardingStrategyConfig {
    private String type;
    private String[] shardingColumns;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getShardingColumns() {
        return shardingColumns;
    }

    public void setShardingColumns(String[] shardingColumns) {
        this.shardingColumns = shardingColumns;
    }
}
