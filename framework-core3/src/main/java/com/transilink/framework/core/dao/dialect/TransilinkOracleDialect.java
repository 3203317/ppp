package com.transilink.framework.core.dao.dialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.BigIntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;

public class TransilinkOracleDialect extends Oracle10gDialect
{
  public TransilinkOracleDialect()
  {
    registerHibernateType(-1, Hibernate.STRING.getName());
    registerHibernateType(-1, Hibernate.TEXT.getName());
    registerHibernateType(3, Hibernate.BIG_INTEGER.getName());
    registerHibernateType(-1, 65535, "text");
  }
}