package com.qingshu.base.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 系统菜单表
 */
@Entity
@Table(name = "j_s_menu")
public class JSMenu extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String menuName;		/*菜单名称*/
	private String menuCode;		/*菜单编码*/
	private Short menuLevel;		/*菜单级别*/
	private JSMenu menuPid;			/*父菜单*/ 
	private String menuUrl;			/*菜单地址*/
	private String menuImg;  		/*菜单图标*/
	private String menuType;		/*菜单类型*/
	private Short  allowDelete;     /*是否允许删除*/

	@Column(name = "menuName", length = 50)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "menuCode", length = 20)
	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "menuLevel")
	public Short getMenuLevel() {
		return this.menuLevel;
	}

	public void setMenuLevel(Short menuLevel) {
		this.menuLevel = menuLevel;
	}

	@Column(name = "menuPid", length = 32)
	public JSMenu getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(JSMenu menuPid) {
		this.menuPid = menuPid;
	}
	@Column(name = "menuUrl", length = 32)
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Column(name = "menuImg", length = 32)
	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}
	@Column(name = "menuType", length = 32)
	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	@Column(name = "allowDelete")
	public Short getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(Short allowDelete) {
		this.allowDelete = allowDelete;
	}

}