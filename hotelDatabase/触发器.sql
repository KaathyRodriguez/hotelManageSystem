/*==============================================================*/
/* Table: 入住                                                    */
/*==============================================================*/

/*
	该触发器实现的功能：当入住单的isFirst状态为‘是’时，含义为客户第一次入住，则新增一条客户信息到Customer表中									
*/

DELIMITER //
CREATE TRIGGER tri_insert_checkInForm
AFTER INSERT ON CheckInForm
FOR EACH ROW
BEGIN
   
   -- 检查是否为首次入住
   SET @isFirstCheckIn = '';
	 SELECT isFirst INTO @isFirstCheckIn FROM checkinform WHERE memberCardID = NEW.memberCardID;
   
   IF @isFirstCheckIn = '是' THEN
      -- 生成随机的会员编号
      SET @randomMemberNo = LPAD(FLOOR(RAND() * 100000000), 8, '0');
      
      -- 插入新的会员记录
      INSERT INTO customer(memberNo, memberCardID,  memberSex, memberLevel, memberPoint, memberTelephone)
      VALUES (@randomMemberNo, NEW.memberCardID, '', '0', 0, '');
      
   END IF;
END //
DELIMITER ;

DROP TRIGGER tri_insert_checkInForm;


/*
	该触发器实现的功能：（1）当入住单的状态由'进行'变为'完成'时，相应房间的状态变为'空闲'
											（2）从bill查询客户号和房间号一致的账单信息，如果没有则插入一条新账单记录，有则消费金额会自动加上房费											
*/

DELIMITER //
CREATE TRIGGER tri_update_checkInForm
AFTER UPDATE ON CheckInForm
FOR EACH ROW
BEGIN
   IF NEW.state = '完成' AND OLD.state = '进行' THEN
      UPDATE Room
      SET roomStatus = '空闲'
      WHERE roomNo = NEW.roomNo;

			-- 检查是否存在房间对应的账单
			SET @billCount = 0;
			SELECT COUNT(*) INTO @billCount FROM Bill WHERE roomNo = NEW.roomNo;
			
			IF @billCount = 0 THEN
				-- 生成随机的账单号
				SET @randomBillNo = LPAD(FLOOR(RAND() * 100000000), 8, '0');
				
				-- 获取当前时间
				SET @currentDateTime = NOW();
				
				-- 插入新的账单记录
				INSERT INTO Bill (billNo, roomNo, memberNo, consumeAmount, billTime, billStatus)
        VALUES (@randomBillNo, NEW.roomNo, NEW.memberNo, NEW.roomAmount, @currentDateTime, '未支付');
			ELSE
				-- 账单的消费金额会自动加上房费
				UPDATE Bill SET consumeAmount = consumeAmount + NEW.roomAmount WHERE roomNo = NEW.roomNo;
			END IF;
   END IF;
	 
	 IF NEW.isFirst = '是' AND NEW.isFirst = '否' THEN
				-- 生成随机的会员编号
      SET @randomMemberNo = LPAD(FLOOR(RAND() * 100000000), 8, '0');
      
      -- 插入新的会员记录
      INSERT INTO customer(memberNo, memberCardID,  memberSex, memberLevel, memberPoint, memberTelephone)
      VALUES (@randomMemberNo, NEW.memberCardID, '', '0', 0, '');
	 END IF;
			
END //
DELIMITER ;

DROP TRIGGER tri_update_checkInForm;

/*
	该触发器实现的功能：根据换房的房间号，变更房间的状态
*/

CREATE TRIGGER tri_change_room
AFTER UPDATE ON CheckInForm
FOR EACH ROW
BEGIN
   IF NEW.roomNo <> OLD.roomNo THEN -- 检查 roomNo 列是否被更新
       UPDATE Room
       SET roomStatus = '入住'
       WHERE roomNo = NEW.roomNo;
       
       UPDATE Room
       SET roomStatus = '空闲'
       WHERE roomNo = OLD.roomNo;
   END IF;
END;


DROP TRIGGER tri_change_room;

/*==============================================================*/
/* Table: 客房预定单                                                  */
/*==============================================================*/

/*
	该触发器实现的功能：（1）当插入一条入住单时，对应的房间号状态由'空闲'变为'预定'状态
																		
*/
DELIMITER //

CREATE TRIGGER tri_insert_roomOrderForm
AFTER INSERT ON RoomOrderForm
FOR EACH ROW
BEGIN

	UPDATE room
	SET roomStatus = '预定'
	WHERE roomNo = NEW.orderRoomNo;

END //

DELIMITER ;


DROP TRIGGER tri_insert_roomOrderForm;


/*
	该触发器实现的功能：（1）当客房预订单的状态从'待定'更新为'完成'时，插入一条新数据到入住单中，相应房间的状态更新为'入住'
											（2）当客房预订单的状态从'待定'更新为'取消'时，相应房间的状态更新为'空闲'
*/

DELIMITER //

CREATE TRIGGER tri_update_roomOrderForm
AFTER UPDATE ON RoomOrderForm
FOR EACH ROW
BEGIN
   -- 根据roomType设置livingPersonNum的值
   SET @livingPersonNum = (
      CASE 
         WHEN NEW.orderRoomType IN ('普通客房', '贵宾套房', '总统套房') THEN 3
         WHEN NEW.orderRoomType = '情侣套房' THEN 2
         ELSE 0
      END
   );

   -- 获取roomAmount的值
   SELECT roomPrice INTO @roomAmount
   FROM Room
   WHERE roomNo = NEW.orderRoomNo;

	  -- 生成随机的会员编号
    SET @randomcheckInFormNo= LPAD(FLOOR(RAND() * 100000000), 8, '0');
	 
   IF NEW.orderStatus = '完成' AND OLD.orderStatus = '待定' THEN
      -- 插入数据到CheckInForm表
      INSERT INTO CheckInForm (checkInFormNo,memberCardID, roomNo, comingTime, livingPersonNum, leaveTime, roomAmount, isFirst, state)
      VALUES (@randomcheckInFormNo,NEW.memberCardID, NEW.orderRoomNo, CURDATE(), @livingPersonNum, DATE_ADD(CURDATE(), INTERVAL 1 DAY), @roomAmount, '否','进行');
			UPDATE Room SET roomStatus = '入住'
      WHERE roomNo = NEW.orderRoomNo;
   END IF;

   IF NEW.orderStatus = '取消' AND OLD.orderStatus = '待定' THEN
      -- 更新Room表中的roomStatus
      UPDATE Room SET roomStatus = '空闲'
      WHERE roomNo = NEW.orderRoomNo;
   END IF;
END //

DELIMITER ;

DROP TRIGGER tri_update_roomOrderForm;

/*==============================================================*/
/* Table: 物资损耗单                                                */
/*==============================================================*/

DELIMITER //

CREATE TRIGGER sta_goods_1 
AFTER INSERT ON MaterialWastageForm
FOR EACH ROW
BEGIN
    -- 被损耗的物品编号
    SET @lossGoods = (
        SELECT lossGoods
        FROM MaterialWastageForm
        WHERE materialWastageFormNo = NEW.materialWastageFormNo
    );

    -- 消费数量
    SET @lossQuantity = (
        SELECT lossQuantity
        FROM MaterialWastageForm
        WHERE materialWastageFormNo = NEW.materialWastageFormNo
    );

    -- 物品编号、物品名称、物品数量
    SELECT goodsNo, goodsName, goodsNum
    INTO @goodsNo, @goodsName, @goodsNum
    FROM Goods
    WHERE goodsNo = @lossGoods;

    -- 更新物品数量
    UPDATE Goods
    SET goodsNum = goodsNum - @lossQuantity
    WHERE goodsNo = @lossGoods;

--     -- 当某种物品的量少于最低库存预警值时，提醒需要补充
--     IF (SELECT goodsNum FROM Goods WHERE goodsNo = @lossGoods) < NEW.goodsLimit THEN
--         SET @message = CONCAT('物品', @goodsName, '缺少，需要补充！');
--         SELECT @message;
--     END IF;
END //

DELIMITER ;

DROP TRIGGER sta_goods_1;

/*==============================================================*/
/* Table: 员工                                                    */
/*==============================================================*/


/*	
		该触发器实现的功能：员工升职时，修改员工信息表，修改员工工资（员工职位修改时，也需要修改权限） 																	
*/

DELIMITER //

CREATE TRIGGER Promotions
AFTER UPDATE ON Employee
FOR EACH ROW
BEGIN

    SET @I_empNo = NEW.empNo;-- 更新的员工编号
    SET @I_empPostName = NEW.empPostName;-- 更新的员工职称

    IF OLD.empPostName <> NEW.empPostName THEN  -- 升职，修改员工信息和员工工资记录
        IF NEW.empPostName = '组长' THEN
            UPDATE Salary
            SET baseSalary = 3000  -- 工资加到3000
            WHERE empNo = @I_empNo;

--             UPDATE Employee
--             SET empGrade = '2'  -- 修改员工职级
--             WHERE empNo = @I_empNo;
        ELSEIF NEW.empPostName = '主管' THEN
            UPDATE Salary
            SET baseSalary = 5000  -- 工资加到5000
            WHERE empNo = @I_empNo;

--             UPDATE Employee
--             SET empGrade = '3'  -- 修改员工职级
--             WHERE empNo = @I_empNo;
        ELSEIF NEW.empPostName = '经理' THEN
            UPDATE Salary
            SET baseSalary = 8000  -- 工资加到8000
            WHERE empNo = @I_empNo;

--             UPDATE Employee
--             SET empGrade = '4'  -- 修改员工职级
--             WHERE empNo = @I_empNo;
        END IF;
    END IF;
END //

DELIMITER ;

DROP TRIGGER Promotions;

 /* 
   该触发器实现的功能: 新员工离职时，删除员工工资表中的记录
*/


CREATE TRIGGER tri_Dimission
AFTER DELETE ON Employee
FOR EACH ROW
BEGIN
    DELETE FROM Salary
    WHERE empNo = OLD.empNo;
END;

DROP TRIGGER tri_Dimission;

/*==============================================================*/
/* Table: 餐饮订单                                                  */
/*==============================================================*/

/*
	该触发器实现的功能：当餐饮订单的状态由'进行'变为'完成'时，账单的消费金额自动加上订单费用											
*/

CREATE TRIGGER tri_update_Catering
AFTER UPDATE ON CateringOrderForm
FOR EACH ROW
BEGIN
    IF NEW.Orderstatus = '完成' AND OLD.Orderstatus = '进行' THEN
        UPDATE Bill
        SET consumeAmount = consumeAmount + NEW.orderDeposit
        WHERE memberNo = NEW.memberNo;
    END IF;
END;


DROP TRIGGER tri_update_Catering;


/*==============================================================*/
/* Table: 账单                                                     */
/*==============================================================*/

/*
   触发器实现功能：当账单状态转为已支付时，自动更新财务报表中的月收入和月利润                                                  
 */


DELIMITER //

CREATE TRIGGER tri_income
AFTER UPDATE ON Bill
FOR EACH ROW
BEGIN
    
    IF NEW.billStatus <> OLD.billStatus THEN -- 检查 billStatus 列是否被更新
        SET @STATUS = NEW.billStatus;
        SET @AMOUNT = NEW.consumeAmount;
        SET @YM = DATE_FORMAT(NOW(), '%Y%m');
        SET @STATEMENT_NO = CONCAT(@YM, '00');
        
        IF @STATUS = '已支付' THEN
            -- 检查当前年份和月份的财务报表是否存在
            IF NOT EXISTS (
                SELECT 1
                FROM FinancialStatement
                WHERE statementNo COLLATE utf8mb4_unicode_ci= @STATEMENT_NO
            ) THEN
                -- 如果不存在，则插入新的财务报表记录
                INSERT INTO FinancialStatement (statementNo, statementDate, monthIncome, monthExpend, monthProfit, auditor, note)
                VALUES (@STATEMENT_NO, NOW(), 0, 0, 0, 'Auditor', '无');
            END IF;
            
            -- 更新月收入和月利润
            UPDATE FinancialStatement
            SET monthIncome = monthIncome + @AMOUNT,
                monthProfit = monthIncome - monthExpend
            WHERE statementNo COLLATE utf8mb4_unicode_ci = @STATEMENT_NO;
        END IF;
    END IF;
END //

DELIMITER ;


DROP TRIGGER tri_income;










