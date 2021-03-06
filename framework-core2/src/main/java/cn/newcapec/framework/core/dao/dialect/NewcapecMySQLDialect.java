package cn.newcapec.framework.core.dao.dialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.type.BigIntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;

public class NewcapecMySQLDialect extends MySQL5Dialect
{
  public NewcapecMySQLDialect()
  {
    registerHibernateType(-1, Hibernate.STRING.getName());
    registerHibernateType(-1, Hibernate.TEXT.getName());
    registerHibernateType(3, Hibernate.BIG_INTEGER.getName());
    registerHibernateType(-1, 65535, "text");
  }
}