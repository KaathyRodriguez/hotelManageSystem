/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/5/9 21:34:40                            */
/*==============================================================*/


 
create table user
(
   id char(8) NOT NULL ,
   username varchar(50) not NULL,
   password varchar(50) not NULL,
   nickname varchar(50) NULL DEFAULT NULL,
   email varchar(50)  NULL DEFAULT NULL ,
   phone varchar(20)  NULL DEFAULT NULL,
   address varchar(255) NULL DEFAULT NULL ,
   createTime timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
	 role  enum('普通用户','员工用户','管理员')not NULL DEFAULT('普通用户'),
  PRIMARY KEY (id)
);

drop table user;

/*==============================================================*/
/* Table: 仓库                                                    */
/*==============================================================*/
create table Warehouse
(
   warehouseNo          char(8) not null,
   warehouseName        varchar(50) not null,
   warehouseManager     varchar(50) not null,
   warehousePlace       varchar(255) null,
   storeGoodsList       text null,
   warehouseState        varchar(50) null,
   warehouseSafeLevel   varchar(20) null,
   primary key (warehouseNo)
);

drop table hotel.Warehouse;
/*==============================================================*/
/* Table: 会员                                                    */
/*==============================================================*/
create table Customer
(
   memberNo             char(8) not null,
   memberCardID         char(18) not null,
   memberName           varchar(50) DEFAULT('***'),
   memberSex            varchar(20) null ,
   memberLevel          varchar(20) null,
   memberPoint          int null,
   memberTelephone      char(50) null,
   primary key (memberNo)
);

drop table Customer;
/*==============================================================*/
/* Table: 供应商
                                                   */
/*==============================================================*/
create table Supplier           
(
   supplierNo           char(8) not null,
   supplierName         varchar(50) not null,
   contactPersonName    varchar(50) not null,
   contactTelephone     varchar(50) not null,
   email                varchar(50) not null,
   supplierPlace        text not null,
   supplyGoodsList      text not null,
   contractStartDate    date not null,
   contractOverDate     date not null,
   primary key (supplierNo)
);
drop table Supplier;
/*==============================================================*/
/* Table: 入住                                                    */
/*==============================================================*/
create table CheckInForm
(
   checkInFormNo				char(8) not null,
   memberNo             char(8) null,
   memberCardID         char(18) not null,
   roomNo               varchar(20) not null,
	 livingPersonNum      int not null,
   comingTime           date not null,
   leaveTime            date not null,
   roomAmount           int not null,
	 isFirst              varchar(5) not null,
	 state								enum('进行','完成') not null default '进行',
   primary key (checkInFormNo)
);
drop table CheckInform;


/*==============================================================*/
/* Table: 员工                                                    */
/*==============================================================*/
create table Employee
(
   empNo                char(8) not null,
   empCardID            char(18) ,
   memberName           varchar(50),
   memberSex            varchar(20) ,
   hireTime             date ,
   empGrade             varchar(20) ,
   empPostName          varchar(50) ,
   empBirthday          date ,
   empBankCardID        char(20) ,
   empDepart            varchar(50) ,
   primary key (empNo)
);

drop table employee;
/*==============================================================*/
/* Table: 客房                                                   */
/*==============================================================*/
create table Room
(
   roomNo               char(8) not null,
   roomType             varchar(50) not null,
   roomStatus           enum('空闲','预定','入住') not null default '空闲',
   roomPrice            float(8,2),
   roomPosition         varchar(255),
   primary key (roomNo)
);
drop table room;
/*==============================================================*/
/* Table: 工资表                                                    */
/*==============================================================*/
create table Salary
(
   salaryNo             char(8) not null,
   empNo                char(8),
   baseSalary           float(8,2) ,
   performanceSalary    float(8,2) ,
   deductSalary         float(8,2) ,
   bonus                float(8,2) ,
   primary key (salaryNo)
);

drop table salary;
/*==============================================================*/
/* Table: 提供                                                   */
/*==============================================================*/
create table SupplyForm
(
	 supplyFormNo					char(8) not null,
   goodsNo              char(8) not null,
   purchaseDate         date not null,
   purchaseManager      varchar(50) not null,
   purchaseAmount       float(8,2) not null,
	 purchasePrice        float(8,2) not null,
   primary key (supplyFormNo)
);

drop table supplyform;

/*==============================================================*/
/* Table: 物资损耗单                                                */
/*==============================================================*/
create table MaterialWastageForm
(
	 materialWastageFormNo char(8) not null,
   lossGoods            varchar(50),
   lossQuantity         int,
   lossDate             date,
   lossType             varchar(50),
   note                 text,
	 primary key (materialWastageFormNo)
);
drop table MaterialWastageForm;
/*==============================================================*/
/* Table: 财务报表                                                  */
/*==============================================================*/
create table FinancialStatement
(
   statementNo          char(8) not null,
   statementDate        date not null,
   monthIncome          float(8,2) not null,
   monthExpend          float(8,2) not null,
   monthProfit          float(8,2) not null,
   auditor              varchar(50) not null,
   note                 text,
   primary key (statementNo)
);
drop table FinancialStatement;
/*==============================================================*/
/* Table: 账单                                                     */
/*==============================================================*/
create table Bill
(
   billNo               char(8) not null,
	 roomNo								char(8) not null,
	 memberNo             char(8) not null,
   consumeAmount        float(8,2) not null,
   billTime             date not null,
	 billStatus           enum('未支付','已支付') not null default '未支付',
   primary key (billNo)
);

drop table Bill;

/*==============================================================*/
/* Table: 货物                                                   */
/*==============================================================*/
create table Goods
(
   goodsNo              char(8) not null,
   goodsName            varchar(50) not null,
   goodsType            varchar(50) not null,
   goodsNum             int not null,
   goodsCost            float(8,2) not null,
	 goodsLimit						int not null,
   primary key (goodsNo)
);

drop table Goods;
/*==============================================================*/
/* Table: 部门                                                    */
/*==============================================================*/
create table Department
(
   deptNo               char(8) not null,
   deptName             varchar(50) not null,
   deptManager          varchar(50) not null,
   deptTelephone        varchar(20) not null,
   deptEmpNum           int not null,
   deptDescription      text not null,
   primary key (deptNo)
);

drop table department;
/*==============================================================*/
/* Table: 客房预定单                                                  */
/*==============================================================*/
create table RoomOrderForm
(
   orderID              char(8) not null,
   orderTime            date not null,
	 memberName           char(30) not null,
	 memberCardID         char(18) not null,
   orderRoomNo          char(20) not null,
	 orderRoomType        varchar(50) not null,
   orderComingTime      date not null,
   orderAmount          float(8,2) not null,
	 orderStatus					enum('待定','取消','完成') not null default '待定',
   primary key (orderID)
);
drop table roomorderform;


/*==============================================================*/
/* Table: 餐饮订单                                                  */
/*==============================================================*/
create table CateringOrderForm
(
   orderNo              char(8) not null,
	 memberNo							char(8) not null,
   purchaseDate         date not null,
   orderType            varchar(50) not null,
   orderDeposit         int not null,
   orderDescription     text not null,
	 orderStatus          varchar(20) not null,
   note                 text,
   primary key (orderNo)
);

drop table CateringOrderForm;




