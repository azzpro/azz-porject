package com.azz.sharding;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.azz.core.sequence.core.SystemServiceFactory;
import com.azz.druid.config.ShardingDatasourceConfig;
import com.azz.druid.datasource.config.DataSourceConfig;
import com.azz.sharding.config.ShardingStrategyConfig;
import com.azz.sharding.config.ShardingTableConfig;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataNode;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.MultipleKeysDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.NoneKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.MultipleKeysTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;


/**
 * <P>TODO</P>
 * @version 1.0
 * @author 刘建麟  2018年11月19日 下午4:26:14
 */
@Configuration
@EnableConfigurationProperties(ShardingDatasourceConfig.class)
public class AzzDatasourceAutoConfig {
	
    private Logger logger = LoggerFactory.getLogger(AzzDatasourceAutoConfig.class);

    @Autowired
    private ShardingDatasourceConfig datasourceConfig;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSource result = null;
        if (datasourceConfig == null) {
            throw new RuntimeException("zmpay-sharding need a datasource config: zmpay.datasource");
        }
        if (datasourceConfig.getDataSourceConfig() == null) {
            throw new RuntimeException("zmpay-sharding need a datasource config: zmpay.datasource.dataSourceConfig");
        }

        logger.info("\n------------------------------------------------\n" +
                "----start initialing zmpay-sharding component----" +
                "\n------------------------------------------------\n");

        if (datasourceConfig.getDataSourceConfig().length == 1) {
            logger.info("there is only one datasource config, create one datasource");
            result = createDataSource(datasourceConfig.getDataSourceConfig()[0]);
        } else {
        	logger.info("there is two datasource config,use dangdang sharding......");
            Map<String, DataSource> dataSourceMap = new HashMap<>(2);
            String defaultDb = datasourceConfig.getDataSourceConfig()[0].getDb();
            logger.info("the first db name is=======>"+defaultDb);
            for (DataSourceConfig dataSourceConfig : datasourceConfig.getDataSourceConfig()) {
                if (dataSourceConfig.isDefaultDatasource()) {
                    defaultDb = dataSourceConfig.getDb();
                }
                dataSourceMap.put(dataSourceConfig.getDb(), createDataSource(dataSourceConfig));
            }
            DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, defaultDb);

            List<TableRule> tableRules = new ArrayList<>();

            if (datasourceConfig.getShardingTableConfig() != null) {
                for (ShardingTableConfig shardingTableConfig : datasourceConfig.getShardingTableConfig()) {
                    TableRule.TableRuleBuilder tableRuleBuilder = TableRule.builder(shardingTableConfig.getTableName());
                    tableRuleBuilder
                            .actualTables(Arrays.asList(shardingTableConfig.getActTables()))
                            .dataSourceRule(dataSourceRule);
                    if (shardingTableConfig.getDatabaseShardingStrategy() != null) {
                        tableRuleBuilder.databaseShardingStrategy(getDatabaseShardingStrategy(shardingTableConfig.getDatabaseShardingStrategy()));
                    }
                    if (shardingTableConfig.getTableShardingStrategy() != null) {
                        tableRuleBuilder.tableShardingStrategy(getTableShardingStrategy(shardingTableConfig.getTableShardingStrategy()));
                    }
                   
                    tableRules.add(tableRuleBuilder.build());
                }
            }
            for (TableRule tableRule : tableRules) {
            	List<DataNode> actualTables = tableRule.getActualTables();
            	for (DataNode dataNode : actualTables) {
					logger.info("逻辑表======>"+dataNode.getDataSourceName()+":::"+dataNode.getTableName());
				}
			}
            ShardingRule.ShardingRuleBuilder shardingRuleBuilder = ShardingRule.builder();
            shardingRuleBuilder.dataSourceRule(dataSourceRule)
                    .tableRules(tableRules);

            if (datasourceConfig.getDefaultDatabaseShardingStrategy() != null) {
                shardingRuleBuilder.databaseShardingStrategy(getDatabaseShardingStrategy(datasourceConfig.getDefaultDatabaseShardingStrategy()));
            }
            if (datasourceConfig.getDefaultTableShardingStrategy() != null) {
                shardingRuleBuilder.tableShardingStrategy(getTableShardingStrategy(datasourceConfig.getDefaultTableShardingStrategy()));
            }

            ShardingRule shardingRule = shardingRuleBuilder.build();

            try {
                result = ShardingDataSourceFactory.createDataSource(shardingRule);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        logger.info("\n----------------------------------------------------\n" +
                "----success to initialize zmpay-sharding component----" +
                "\n-----------------------------------------------------\n");
        return result;
    }
    
    final int TWO = 2;
    
    private DatabaseShardingStrategy getDatabaseShardingStrategy(ShardingStrategyConfig databaseShardingStrategy) {
        String type = databaseShardingStrategy.getType();
        String[] columns = databaseShardingStrategy.getShardingColumns();
        if (columns == null || columns.length == 0) {
            NoneKeyDatabaseShardingAlgorithm databaseShardingAlgorithm = SystemServiceFactory.getService(type, NoneKeyDatabaseShardingAlgorithm.class);
            return new DatabaseShardingStrategy(databaseShardingAlgorithm);
        } else if (columns.length == 1) {
            SingleKeyDatabaseShardingAlgorithm singleKeyDatabaseShardingAlgorithm = SystemServiceFactory.getService(type, SingleKeyDatabaseShardingAlgorithm.class);
            return new DatabaseShardingStrategy(columns[0], singleKeyDatabaseShardingAlgorithm);
        } else if (columns.length == TWO) {
            MultipleKeysDatabaseShardingAlgorithm multipleKeysDatabaseShardingAlgorithm = SystemServiceFactory.getService(type, MultipleKeysDatabaseShardingAlgorithm.class);
            return new DatabaseShardingStrategy(Arrays.asList(columns), multipleKeysDatabaseShardingAlgorithm);
        } else {
            throw new RuntimeException("too much sharding columns");
        }
    }


    private TableShardingStrategy getTableShardingStrategy(ShardingStrategyConfig tableShardingStrategyConfig) {
        String type = tableShardingStrategyConfig.getType();
        String[] columns = tableShardingStrategyConfig.getShardingColumns();
        if (columns == null || columns.length == 0) {
            throw new NullPointerException("at least one sharding column is needed");
        } else if (columns.length == 1) {
            SingleKeyTableShardingAlgorithm singleKeyTableShardingAlgorithm = SystemServiceFactory.getService(type, SingleKeyTableShardingAlgorithm.class);
            return new TableShardingStrategy(columns[0], singleKeyTableShardingAlgorithm);
        } else if (columns.length == TWO) {
            MultipleKeysTableShardingAlgorithm multipleKeysTableShardingAlgorithm = SystemServiceFactory.getService(type, MultipleKeysTableShardingAlgorithm.class);
            ;
            return new TableShardingStrategy(Arrays.asList(columns), multipleKeysTableShardingAlgorithm);
        } else {
            throw new RuntimeException("too much sharding columns");
        }
    }

    private DataSource createDataSource(DataSourceConfig dataSourceConfig) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(dataSourceConfig.getUrl());
        datasource.setUsername(dataSourceConfig.getUsername());
        datasource.setPassword(dataSourceConfig.getPassword());
        datasource.setDriverClassName(dataSourceConfig.getDriverClassName());

        datasource.setInitialSize(dataSourceConfig.getInitialSize());
        datasource.setMinIdle(dataSourceConfig.getMinIdle());
        datasource.setMaxActive(dataSourceConfig.getMaxActive());
        datasource.setMaxWait(dataSourceConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(dataSourceConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(dataSourceConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(dataSourceConfig.getValidationQuery());
        datasource.setTestWhileIdle(dataSourceConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(dataSourceConfig.isTestOnBorrow());
        datasource.setTestOnReturn(dataSourceConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(dataSourceConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(dataSourceConfig.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(dataSourceConfig.getConnectionProperties());
        return datasource;
    }


}
