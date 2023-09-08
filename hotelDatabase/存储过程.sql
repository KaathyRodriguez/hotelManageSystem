DELIMITER //

CREATE PROCEDURE Increase_Salary()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE empNoVal char(8);
    DECLARE VempGrade varchar(20);
    DECLARE increment FLOAT(8, 2);
    
    DECLARE emp_cursor CURSOR FOR
        SELECT empNo, empGrade FROM Employee;
        
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN emp_cursor;
    
    emp_loop: LOOP
        FETCH emp_cursor INTO empNoVal, VempGrade;
        
        IF done THEN
            LEAVE emp_loop;
        END IF;
        
        IF VempGrade = '1' THEN
            SET increment = 0.1;
        ELSEIF VempGrade = '2' THEN
            SET increment = 0.2;
        ELSEIF VempGrade = '3' THEN
            SET increment = 0.3;
        ELSEIF VempGrade = '4' THEN
            SET increment = 0.4;
        END IF;
        
        UPDATE Salary
        SET baseSalary = baseSalary * (1 + increment)
        WHERE Salary.empNo = empNoVal;
    END LOOP;
    
    CLOSE emp_cursor;
END //

DELIMITER ;





CALL Increase_Salary()
drop PROCEDURE Increase_Salary