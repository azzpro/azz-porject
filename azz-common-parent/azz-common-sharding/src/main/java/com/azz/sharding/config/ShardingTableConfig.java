package com.azz.sharding.config;

/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:26:03
 */
public class ShardingTableConfig {
	
    private String tableName;
    
    private String[] actTables;
    
    private ShardingStrategyConfig databaseShardingStrategy;
    
    private ShardingStrategyConfig tableShardingStrategy;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getActTables() {
        return actTables;
    }

    public void setActTables(String[] actTables) {
        this.actTables = actTables;
    }

    public ShardingStrategyConfig getDatabaseShardingStrategy() {
        return databaseShardingStrategy;
    }

    public void setDatabaseShardingStrategy(ShardingStrategyConfig databaseShardingStrategy) {
        this.databaseShardingStrategy = databaseShardingStrategy;
    }

    public ShardingStrategyConfig getTableShardingStrategy() {
        return tableShardingStrategy;
    }

    public void setTableShardingStrategy(ShardingStrategyConfig tableShardingStrategy) {
        this.tableShardingStrategy = tableShardingStrategy;
    }
}
