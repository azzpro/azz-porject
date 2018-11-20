package com.azz.druid.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.azz.druid.datasource.config.DataSourceConfig;
import com.azz.druid.monitor.config.MonitorConfig;
import com.azz.sharding.config.ShardingStrategyConfig;
import com.azz.sharding.config.ShardingTableConfig;



/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:26:53
 */
@ConfigurationProperties(prefix = "azz.datasource")
public class ShardingDatasourceConfig {

    private DataSourceConfig[] dataSourceConfig;

    private MonitorConfig monitorConfig;

    private ShardingTableConfig[] shardingTableConfig;

    private ShardingStrategyConfig defaultDatabaseShardingStrategy;
    
    private ShardingStrategyConfig defaultTableShardingStrategy;

    public MonitorConfig getMonitorConfig() {
        return monitorConfig;
    }

    public void setMonitorConfig(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
    }

    public DataSourceConfig[] getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig[] dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public ShardingTableConfig[] getShardingTableConfig() {
        return shardingTableConfig;
    }

    public void setShardingTableConfig(ShardingTableConfig[] shardingTableConfig) {
        this.shardingTableConfig = shardingTableConfig;
    }

    public ShardingStrategyConfig getDefaultDatabaseShardingStrategy() {
        return defaultDatabaseShardingStrategy;
    }

    public void setDefaultDatabaseShardingStrategy(ShardingStrategyConfig defaultDatabaseShardingStrategy) {
        this.defaultDatabaseShardingStrategy = defaultDatabaseShardingStrategy;
    }

    public ShardingStrategyConfig getDefaultTableShardingStrategy() {
        return defaultTableShardingStrategy;
    }

    public void setDefaultTableShardingStrategy(ShardingStrategyConfig defaultTableShardingStrategy) {
        this.defaultTableShardingStrategy = defaultTableShardingStrategy;
    }
}
