SELECT * FROM `member`;
SELECT * FROM `cart`;
SELECT * FROM `product`;
SELECT * FROM `payment`;
SELECT * FROM `store`;
SELECT * FROM `stock`;
SELECT * FROM `tbl_djt`;
SELECT COUNT(*) FROM `member`;
DELETE FROM `tbl_djt`;

DROP TABLE `tbl_djt`;
		WHERE memberId = 'test';
		
		UPDATE MEMBER SET
		memberPw = '0'
		WHERE memberId = 'test';
		
		UPDATE MEMBER SET
		memberPw = '1'
		WHERE memberId = 'test';
		
		DELETE FROM MEMBER
		WHERE memberId = 'test9@9';
		
		SELECT * FROM `member` WHERE memberId = 'gae799@naver.com';
		
DELETE FROM cart
		WHERE memberId = 'do__ri@naver.com';
		
		ALTER TABLE `member` ADD `sex` INT;