package com.crell.core.persistence;

import com.crell.common.model.User;
import com.crell.core.util.IdUtils;
import com.crell.core.util.UserUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 数据Entity类
 * @author crell
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected String createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected String updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；）
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(String id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(IdUtils.uuid());
		}
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user.getId();
			this.createBy = user.getId();
		}
		this.createDate = new Date();
		this.updateDate = this.createDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user.getId();
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@JsonIgnore
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@JsonIgnore
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
