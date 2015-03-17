package cn.newcapec.framework.core.model;

import javax.persistence.MappedSuperclass;

import cn.newcapec.framework.core.model.datacontainer.DataObject;

@MappedSuperclass
public class AppBaseModel extends BaseModel implements DataObject {
	private static final long serialVersionUID = -602515871859035627L;
}