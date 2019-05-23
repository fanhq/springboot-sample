package com.fanhq.example.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanhaiqiu
 * @date 2019/5/22
 */
public class MabatisPlusGenerator {

    public static void main(String[] args) {

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/test_db?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8&allowPublicKeyRetrieval=true");
        dsc.setUsername("root");
        dsc.setPassword("mysql");

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("src/main/java");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setAuthor("fanhaiqiu");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");

        StrategyConfig strategy = new StrategyConfig();
        // 此处可以修改为您的表前缀
        //strategy.setTablePrefix(new String[] { "SYS_" });
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(new String[]{"goods"});

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.fanhq.example");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setXml("mapper");

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //xml生成路径
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);

        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);

        AutoGenerator mpg = new AutoGenerator();
        //数据源配置
        mpg.setDataSource(dsc);
        //全局配置
        mpg.setGlobalConfig(gc);
        //生成策略配置
        mpg.setStrategy(strategy);
        //包配置
        mpg.setPackageInfo(pc);
        mpg.setCfg(cfg);
        //xml配置
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();

    }
}
