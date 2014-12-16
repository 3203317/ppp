package cn.newcapec.framework.core.handler;

import cn.newcapec.framework.core.exception.BaseException;
import cn.newcapec.framework.core.exception.asserts.AssertObject;
import cn.newcapec.framework.core.logs.LogEnabled;
import cn.newcapec.framework.core.model.FileAttach;
import cn.newcapec.framework.core.rest.Msg;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class MultiViewResource
  implements LogEnabled
{
  private JSONObject jsonObject;
  private List<FileAttach> fileAttachs = null;

  @ExceptionHandler({Exception.class})
  public void exceptionHandler(Exception e, HttpServletResponse response)
  {
    log.error(ExceptionUtils.getFullStackTrace(e));
    try
    {
      Msg msg = new Msg();
      msg.setMsg("系统出现错误了！");
      response.getWriter().write(msg.toJSONObject().toString());
    } catch (Exception ex) {
      e.printStackTrace();
    }
  }

  public List<FileAttach> getfiles()
  {
    return this.fileAttachs;
  }

  public List<FileAttach> setfiles(List<FileAttach> fileAttachs)
  {
    return this.fileAttachs = fileAttachs;
  }

  public JSONObject getJsonObject()
  {
    return this.jsonObject;
  }

  public void setJsonObject(JSONObject jsonObject) {
    this.jsonObject = jsonObject;
  }

  protected ModelAndView toView(String path, Map<String, Object> model)
  {
    ModelAndView result = new ModelAndView(path, model);
    result.addAllObjects(model);
    return result;
  }

  protected String toView(String path)
  {
    return path;
  }

  protected String redirect(String path)
  {
    return "redirect:" + path;
  }

  protected String forward(String path)
  {
    return "forward:" + path;
  }

  protected String getUrl(String key)
  {
    if ((UrlMapping.loadUrlMap != null) &&
      (UrlMapping.loadUrlMap.containsKey(key))) {
      return (String)UrlMapping.loadUrlMap.get(key);
    }

    return "";
  }

  public Msg doExpAssert(AssertObject assertObject)
  {
    Msg msg = new Msg();
    try {
      assertObject.AssertMethod(msg);
      msg.setSuccess(true);
    } catch (Exception e) {
      msg.setMsg("系统出错了!");
      log.error(ExceptionUtils.getFullStackTrace(e));
      if ((e instanceof BaseException)) {
        throw ((BaseException)e);
      }
      throw new BaseException("系统出错了!", e);
    }

    return msg;
  }
}