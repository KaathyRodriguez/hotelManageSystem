package com.example.hotelmanagesystem.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Kevin
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("department")
@ApiModel(value = "Department对象", description = "")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("deptNo")
    @TableId(value = "deptNo", type = IdType.INPUT)
    private String deptNo;

    private String deptName;

    private String deptManager;

    private String deptTelephone;

    private Integer deptEmpNum;

    private String deptDescription;


}
