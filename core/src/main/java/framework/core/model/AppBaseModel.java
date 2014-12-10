package framework.core.model;

import javax.persistence.MappedSuperclass;

import framework.core.model.datacontainer.DataObject;

/**
 * 功能描述：应用框架中业务模型的简单扩展基类
 *
 * @author huangxin
 */
@MappedSuperclass
public class AppBaseModel extends BaseModel implements DataObject {

	private static final long serialVersionUID = -602515871859035627L;

}