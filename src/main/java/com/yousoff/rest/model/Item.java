package com.yousoff.rest.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Yousoff Effendy
 *
 */
public class Item {

	@ApiModelProperty(dataType = "integer", example = "1")
	private long id;

	@ApiModelProperty(dataType = "string", example = "Item Name 1")
	private String name;

	@ApiModelProperty(dataType = "string", example = "Item Description 1")
	private String description;

	@ApiModelProperty(dataType = "boolean", example = "true")
	private boolean enabled;

	@ApiModelProperty(dataType = "string", example = "2018-03-20 21:00:00")
	private Date createdDate;

	@ApiModelProperty(dataType = "string", example = "Username A")
	private String createdBy;

	@ApiModelProperty(dataType = "string", example = "2018-03-20 21:00:00", notes = "test")
	private Date updatedDate;

	@ApiModelProperty(dataType = "string", example = "Username B")
	private String updatedBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
	

}
