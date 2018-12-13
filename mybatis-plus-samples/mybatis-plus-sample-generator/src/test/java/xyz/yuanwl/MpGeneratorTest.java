package xyz.yuanwl;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义代码生成器单元测试类
 * @author Yuanwl
 * @date 2018/11/21 14:29
 */
@Slf4j
public class MpGeneratorTest extends BaseTest{

	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	/**
	 * # 1、打印数据库连接信息和项目路径，看看对不对，再调整配置文件和 generate(String module, String tables) 方法里面的参数
	 * @author Yuanwl
	 * @date 2018-11-21 14:43:35
	 * @version v1.0.0
	 */
	@Test
	public void printProjectInfo() {
		log.warn("url={}，username={}，password={}，driver={}", url, username, password, driver);
		String projectPath = System.getProperty("user.dir"); //项目路径
		log.warn("项目路径：{}", projectPath);
	}

	/**
	 * # 2、执行生成器生成代码。有需要的话可以先修改模块名和表名再执行
	 * @author Yuanwl
	 * @date 2018-11-21 14:47:42
	 * @version v1.0.0
	 */
	@Test
	public void doGenerate() {
		generate(null, ".*");
	}

	/**
	 * 自定义生成代码
	 * @param moduleName 模块名
	 * @param tableNames 表名，可输入单个或正则表达式。比如 user 代表只生成 user 表对应代码；user|order 代表 user和order 表；.* 代表所有表
	 * @author Yuanwl
	 * @date 2018-11-21 14:44:27
	 * @version v1.0.0
	 */
	private void generate(String moduleName, String tableNames) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		//项目路径
		String projectPath = System.getProperty("user.dir");
		System.err.println(projectPath);
		//main文件夹路径
		String mainPath = projectPath + "/src/main/";

		// 全局配置
		GlobalConfig gc = new GlobalConfig();

		gc.setOutputDir(mainPath + "java");
		gc.setFileOverride(true);
		gc.setEnableCache(false);
//		gc.setSwagger2(true);
		gc.setBaseResultMap(true);
		gc.setAuthor("");
		gc.setServiceName("%sService"); //设置service类名规范，去掉service的 I 前缀
		gc.setIdType(IdType.AUTO);
		gc.setOpen(false);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(url);
		// dsc.setSchemaName("public");
		dsc.setDriverName(driver);
		dsc.setUsername(username);
		dsc.setPassword(password);
//		dsc.setDbType(DbType.MYSQL); //这个一般不用设置，mp会根据连接信息判断数据库类型
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(moduleName);
		pc.setParent("xyz.yuanwl");
		pc.setServiceImpl("");
		pc.setMapper("mapper");
		pc.setEntity("entity");
		pc.setXml("mapper");
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		List<FileOutConfig> focList = new ArrayList<>();
		// 配置文件存放文件夹路径
		String mapperDirPath = mainPath + "resource/"
				+ (StringUtils.isNotEmpty(pc.getXml()) ? pc.getXml() : "mapper") + "/"
				+ (StringUtils.isNotEmpty(pc.getModuleName()) ? pc.getModuleName() + "/" : "");
		File dir = new File(mapperDirPath);
		if (!dir.exists()) dir.mkdirs(); // 如果这个文件夹不存在就创建

		// 自定义生成 *Mapper.xml 文件
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义每个
				return mapperDirPath + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);

		// 自定义输出文件。配置 FileOutConfig 指定模板文件、输出文件达到自定义文件生成目的
		cfg.setFileCreate((ConfigBuilder configBuilder, FileType fileType, String s) -> {
			// 不生成service实现类
			if (s.endsWith("Impl.java")) return false;
			else return true;
		});

		mpg.setCfg(cfg);

		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置、数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass("xyz.yuanwl.common.BaseEntity"); //生成实体类将继承的基类全类名，这个类要手动创建，生成器不会自动生成
		strategy.setSuperEntityColumns("id"); //指定的字段不会在实体类中生成，这些字段将由我们自己在基类中手动创建，结合上面的选项使用
//		strategy.setSuperEntityClass(null); //为null则不会在生成实体类中生成继承基类语句
		strategy.setEntityLombokModel(true);
//		strategy.setSuperControllerClass("xyz.yuanwl.common.BaseController"); //与上面实体类一样
		strategy.setSuperControllerClass(null); //与上面实体类一样
		strategy.setRestControllerStyle(true);
		strategy.setInclude(tableNames);
		strategy.setControllerMappingHyphenStyle(true);
//		strategy.setEntityTableFieldAnnotationEnable(true); //貌似这个属性被删掉了
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);

		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
