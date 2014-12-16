package cn.newcapec.framework.core.dao.hibernate;

import cn.newcapec.framework.core.dao.dialect.NewcapecMySQLDialect;
import cn.newcapec.framework.core.logs.LogEnabled;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ddlutils.PlatformUtils;
import org.apache.log4j.Logger;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class SessionFactoryAutoDialectBean extends AnnotationSessionFactoryBean
  implements LogEnabled
{
  private static final String PROPERTY_NAME_DIALECT = "hibernate.dialect";
  private static final String ORACLE_TYPE = "Oracle";
  private static final String MYSQL_TYPE = "MySQL";
  private String dialect = null;

  public void setDataSource(DataSource dataSource)
  {
    PlatformUtils platformUtils = new PlatformUtils();
    String dbType = platformUtils.determineDatabaseType(dataSource);
    log.info("Database type is \"" + dbType + "\"");

    if ("MySQL".equals(dbType)) {
      this.dialect = NewcapecMySQLDialect.class.getName();
    }
    else if ("Oracle".equals(dbType)) {
      this.dialect = Oracle10gDialect.class.getName();
    }
    else {
      log.error("unknown database :" + dbType);
    }

    super.setDataSource(dataSource);
  }

  public void setHibernateProperties(Properties hibernateProperties)
  {
    if (hibernateProperties.containsKey("hibernate.dialect")) {
      hibernateProperties.remove(hibernateProperties);
    }

    hibernateProperties.setProperty("hibernate.dialect", this.dialect);

    super.setHibernateProperties(hibernateProperties);
  }
}