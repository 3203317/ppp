package cn.newcapec.framework.core.rest.velocity;

import cn.newcapec.framework.core.utils.dataUtils.DateUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.tools.generic.DateTool;

public class VelocityView extends org.springframework.web.servlet.view.velocity.VelocityView
{
  protected Context createVelocityContext(Map model, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    DateTool dateTool = new DateTool();
    model.put("dateTool", dateTool);

    model.put("DATE_FORMAT_SHORT", DateUtil.DATE_FORMAT);
    model.put("DATE_FORMAT_LONG", DateUtil.DATETIME_FORMAT);
    return super.createVelocityContext(model, request, response);
  }

  protected void mergeTemplate(Template template, Context context, HttpServletResponse response) throws Exception
  {
    StringWriter sw = new StringWriter();
    try
    {
      template.merge(context, sw);

      response.getWriter().write(sw.toString());
    }
    catch (MethodInvocationException ex) {
      throw new Exception("Method invocation failed during rendering of Velocity view with name '" + getBeanName() + "': " + ex.getMessage() + "; reference [" + ex.getReferenceName() + "], method '" + ex.getMethodName() + "'", ex.getWrappedThrowable());
    }
  }
}