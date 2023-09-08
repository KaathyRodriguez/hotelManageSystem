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
 * @since 2023-06-01
 */
@Getter
@Setter
@TableName("salary")
@ApiModel(value = "Salary对象", description = "")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("salaryNo")
    @TableId(value = "salaryNo", type = IdType.INPUT)
    private String salaryNo;

    private String empNo;

    private Float baseSalary;

    private Float performanceSalary;

    private Float deductSalary;

    private Float bonus;


}
