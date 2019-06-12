package com.yousoff.springboot.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Yousoff Effendy
 *
 */
@Getter @Setter @ToString
public class Item {

	@ApiModelProperty(dataType = "integer", example = "1")
	private long id;

	@ApiModelProperty(dataType = "string", example = "Item Name 1")
	private String name;

	@ApiModelProperty(dataType = "string", example = "Item Description 1")
	private String description;

	@ApiModelProperty(dataType = "string", example = "Y")
	private String enabled;

	@ApiModelProperty(dataType = "string", example = "2018-03-20 21:00:00")
	private Date createdDate;

	@ApiModelProperty(dataType = "string", example = "Username A")
	private String createdBy;

	@ApiModelProperty(dataType = "string", example = "2018-03-20 21:00:00", notes = "test")
	private Date updatedDate;

	@ApiModelProperty(dataType = "string", example = "Username B")
	private String updatedBy;

}
