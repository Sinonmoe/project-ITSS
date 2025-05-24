CREATE database IF NOT EXISTS convenience;
USE convenience;

CREATE TABLE fridge (
  fridgeId INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE dish (
  dishId INT AUTO_INCREMENT PRIMARY KEY,
  dishName VARCHAR(100) DEFAULT NULL,
  instruction TEXT DEFAULT NULL
);

CREATE TABLE UserGroup (
  groupId INT AUTO_INCREMENT PRIMARY KEY,
  groupName VARCHAR(100) DEFAULT NULL
);

CREATE TABLE User (
  userId INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) DEFAULT NULL,
  groupId INT DEFAULT NULL,
  FOREIGN KEY (groupId) REFERENCES UserGroup(groupId)
);

CREATE TABLE shoppingList (
  shoppingListId INT AUTO_INCREMENT PRIMARY KEY,
  buyDate DATE DEFAULT NULL,
  groupId INT DEFAULT NULL,
  userId INT DEFAULT NULL,
  FOREIGN KEY (groupId) REFERENCES UserGroup(groupId),
  FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE food (
  foodId INT AUTO_INCREMENT PRIMARY KEY,
  foodName VARCHAR(100) DEFAULT NULL,
  quantity DECIMAL(10, 2) DEFAULT NULL,
  unitType VARCHAR(50) DEFAULT NULL,
  expirationDate DATE DEFAULT NULL,
  fridgeId INT DEFAULT NULL,
  shoppingListId INT DEFAULT NULL,
  FOREIGN KEY (fridgeId) REFERENCES fridge(fridgeId),
  FOREIGN KEY (shoppingListId) REFERENCES shoppingList(shoppingListId)
);

CREATE TABLE dish_use_food (
  dishId INT NOT NULL,
  foodName VARCHAR(100) NOT NULL,
  quantity DECIMAL(10, 2) DEFAULT NULL,
  PRIMARY KEY (dishId, foodName),
  FOREIGN KEY (dishId) REFERENCES dish(dishId)
);

DELIMITER //

CREATE TRIGGER prevent_duplicate_dishName
BEFORE INSERT ON dish
FOR EACH ROW
BEGIN
  DECLARE countDish INT;

  SELECT COUNT(*) INTO countDish
  FROM dish
  WHERE dishName = NEW.dishName;

  IF countDish > 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Tên món ăn đã tồn tại trong cơ sở dữ liệu.';
  END IF;
END;
//

DELIMITER ;

DELIMITER //

CREATE TRIGGER prevent_expired_food
BEFORE INSERT ON food
FOR EACH ROW
BEGIN
  IF NEW.expirationDate <= CURDATE() THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Thực phẩm có hạn sử dụng không hợp lệ (đã hết hạn hoặc hết hạn hôm nay).';
  END IF;
END;
//

DELIMITER ;


-- Retrieve all food items in the fridge
SELECT f.foodName, f.quantity, f.unitType, f.expirationDate 
FROM food f WHERE fridgeId = 1 ORDER BY expirationDate;

-- Retrieve all dishes 
SELECT 
  d.dishName, 
  f.foodName, 
  duf.quantity
FROM 
  dish d
JOIN 
  dish_use_food duf ON d.dishId = duf.dishId;

-- Retrieve all food items used in a specific dish
SELECT 
  d.dishName, 
  f.foodName, 
  duf.quantity
FROM 
  dish d 
JOIN
    dish_use_food duf ON d.dishId = duf.dishId
WHERE 
  d.dishName = 'Pasta';

-- Insert a new fridge
INSERT INTO fridge VALUES ();
SELECT * FROM fridge;

-- Insert a new dish
INSERT INTO dish (dishName, instruction) 
VALUES ('Pasta', 'Boil water, add pasta, cook for 10 minutes.');
INSERT INTO dish (dishName, instruction)
VALUES ('KFC', 'Fry chicken, add spices, serve hot.');
SELECT d.dishName FROM dish d;

-- Insert a new user group
INSERT INTO UserGroup (groupName) VALUES ('ITSS');
SELECT * FROM UserGroup;

-- Insert a new user
INSERT INTO User (username, groupId) VALUES ('Meikou', 1);
SELECT * FROM User;

-- Insert a new shopping list
INSERT INTO shoppingList (buyDate, groupId, userId) VALUES ('2025-10-05', 1, 1);
SELECT * FROM shoppingList;
UPDATE shoppingList SET buyDate = '2025-5-10' WHERE buyDate = '2025-10-05';

-- Insert foods item
INSERT INTO food (foodName, quantity, unitType, expirationDate) 
VALUES ('Tomato', 5, 'kg', '2025-6-10');
INSERT INTO food (foodName, quantity, unitType, expirationDate)
VALUES ('Pasta', 2, 'kg', '2025-6-10');
INSERT INTO food (foodName, quantity, unitType, expirationDate)
VALUES ('Chicken', 1, 'kg', '2025-5-10');
Delete FROM food where foodName = 'Tomato';
SELECT * FROM food;

-- Insert food used in a dish
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (1, 'Pasta', 0.2);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (1, 'Chicken', 0.3);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (1, 'Tomato', 0.1);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (2, 'Chicken', 0.5);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (2, 'Pepper', 0.05);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (2, 'Salt', 0.02);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (2, 'Oil', 0.1);
INSERT INTO dish_use_food (dishId, foodName, quantity)
VALUES (2, 'Onion', 0.05);

-- Retrieve all food items used in a specific dish
SELECT 
  d.dishName, 
  duf.foodName, 
  duf.quantity
FROM 
  dish d JOIN dish_use_food duf ON d.dishId = duf.dishId
WHERE
  d.dishName = 'KFC';
