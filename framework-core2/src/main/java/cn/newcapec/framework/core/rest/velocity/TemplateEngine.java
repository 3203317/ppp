package cn.newcapec.framework.core.rest.velocity;

import cn.newcapec.framework.core.utils.springUtils.SpringConext;
import java.io.StringWriter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

public class TemplateEngine
{
  private static final Log log = LogFactory.getLog(TemplateEngine.class);

  public static String parse(String templatePath, Context context)
    throws Exception
  {
    VelocityConfigurer vc = (VelocityConfigurer)SpringConext.getApplicationContext().getBean("velocityConfig");
    Template t = vc.getVelocityEngine().getTemplate(templatePath, "utf-8");
    context.put("paging", "common/includePagination.vm");
    StringWriter sw = new StringWriter();
    t.merge(context, sw);
    return sw.toString();
  }

  static
  {
    Velocity.setProperty("directive.set.null.allowed", "true");
    try
    {
      Velocity.init();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}