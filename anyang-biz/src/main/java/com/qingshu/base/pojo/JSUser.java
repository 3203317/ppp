package com.qingshu.base.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.qingshu.common.annotation.MyField;
import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 用户表
 */
@Entity
@Table(name = "j_s_user")
//@Pojo(name="j_s_user",desc="系统用户")
public class JSUser extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userName;// 用户名
	private String passWord;// 密码
	private String nickName;// 昵称
	private String realName;// 真实姓名
	private String mobile;// 手机
	private String telephone;// 座机
	private String email;// 邮箱
	private String signFile;// 签名
	private String headPic;// 头像
	private Short status;// 状态
	private Short userLevel;//用户级别
	private String userType;//用户类型
	private Short islock ;//是否开启屏幕锁定
	private Short state;//是否重点企业
	private Double x;
	private Double y;
	
	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}

	private Set<JSRole> jsRoles;
	@Column(name = "userName", length = 50)
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "passWord", length = 50)
	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "nickName", length = 50)
	@MyField(name="nickName",islog=true,indesc=true)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "realName", length = 30)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "mobile", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "telephone", length = 15)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "signFile", length = 50)
	public String getSignFile() {
		return this.signFile;
	}

	public void setSignFile(String signFile) {
		this.signFile = signFile;
	}

	@Column(name = "headPic", length = 50)
	public String getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "userLevel")
	public Short getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Short userLevel) {
		this.userLevel = userLevel;
	}
	@Column(name = "userType", length = 50)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "islock")
	public Short getIsLock() {
		return islock;
	}
	public void setIsLock(Short islock) {
		this.islock = islock;
	}
	@ManyToMany()
    @JoinTable(name="j_s_user_role", joinColumns = { 
    @JoinColumn(name="user_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
    @JoinColumn(name="role_id", nullable=false, updatable=false) })
	public Set<JSRole> getJsRoles() {
		return jsRoles;
	}

	public void setJsRoles(Set<JSRole> jsRoles) {
		this.jsRoles = jsRoles;
	}
	@MyField(name="x",islog=true,indesc=true)
	public Double getX() {
		return x;
	}
	@MyField(name="y",islog=true,indesc=true)
	public Double getY() {
		return y;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public void setY(Double y) {
		this.y = y;
	}
}