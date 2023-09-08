package com.example.hotelmanagesystem.entity;

import java.io.Serializable;
import java.time.LocalDate;

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
 * @since 2023-05-31
 */
@Getter
@Setter
@TableName("employee")
@ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("empNo")
    @TableId(value = "empNo", type = IdType.INPUT)
    private String empNo;

    private String empCardID;

    private String memberName;

    private String memberSex;

    private LocalDate hireTime;

    private String empGrade;

    private String empPostName;

    private LocalDate empBirthday;

    private String empBankCardID;

    private String empDepart;


}
