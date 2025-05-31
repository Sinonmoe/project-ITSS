drop database if exists Convenience;
CREATE DATABASE Convenience;
USE Convenience;
ALTER DATABASE Convenience CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE fridge (
  fridgeId INT AUTO_INCREMENT,
  PRIMARY KEY (fridgeId)
);

CREATE TABLE ingredient (
  ingredientName VARCHAR(50),
  quantity DECIMAL(10, 2),
  unitType VARCHAR(20),
  ingredientId INT AUTO_INCREMENT,
  expirationDate DATE,
  fridgeId INT,
  PRIMARY KEY (ingredientId),
  FOREIGN KEY (fridgeId) REFERENCES fridge(fridgeId)
);

CREATE TABLE dish (
  dishName VARCHAR(100),
  dishId INT AUTO_INCREMENT,
  instruction TEXT,
  eatTime ENUM('breakfast', 'lunch', 'dinner'),
  eatDate INT , -- 0 -> 6
  PRIMARY KEY (dishId)
);

CREATE TABLE UserGroup (
  groupId INT AUTO_INCREMENT,
  groupName VARCHAR(100),
  PRIMARY KEY (groupId)
);

CREATE TABLE meal_plan (
  id INT AUTO_INCREMENT,
  eatTime ENUM('breakfast', 'lunch', 'dinner'),
  eatDate INT , -- 0 -> 6
  PRIMARY KEY (id)
);

CREATE TABLE dish_use_ingredient (
  dishId INT,
  ingredientName VARCHAR(50),
    quantity DECIMAL(10, 2),
    unitType VARCHAR(20),
  PRIMARY KEY (dishId, ingredientName),
  FOREIGN KEY (dishId) REFERENCES dish(dishId)
);

CREATE TABLE meal_has_dish (
  mealId INT,
  dishId INT,
  PRIMARY KEY (id, dishId),
  FOREIGN KEY (mealId) REFERENCES meal_plan(id),
  FOREIGN KEY (dishId) REFERENCES dish(dishId)
);

CREATE TABLE User (
  userId INT AUTO_INCREMENT,
  username VARCHAR(100),
  groupId INT,
  PRIMARY KEY (userId),
  FOREIGN KEY (groupId) REFERENCES UserGroup(groupId)
);

CREATE TABLE shoppingList (
  shoppingListId INT AUTO_INCREMENT,
  buyDate DATE,
  groupId INT,
  userId INT,
  PRIMARY KEY (shoppingListId),
  FOREIGN KEY (groupId) REFERENCES UserGroup(groupId),
  FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE buy_ingredient (
  shoppingListId INT,
  ingredientName VARCHAR(50),
  quantity DECIMAL(10, 2),
  unitType VARCHAR(20),
  PRIMARY KEY (shoppingListId, ingredientName),
  FOREIGN KEY (shoppingListId) REFERENCES shoppingList(shoppingListId)
);

INSERT INTO fridge (fridgeId) VALUES (1), (2);

INSERT INTO ingredient (ingredientName, quantity, unitType, ingredientId, expirationDate, fridgeId) VALUES
('Cà rốt', 2.00, 'kg', 1, '2025-06-10', 1),
('Sữa tươi', 1.50, 'l', 2, '2025-06-05', 1),
('Trứng gà', 10.00, 'pcs', 3, '2025-06-08', 1),
('Khoai tây', 3.00, 'kg', 4, '2025-06-12', 2),
('Ức gà', 1.20, 'kg', 5, '2025-06-07', 2);

INSERT INTO dish (dishName, dishId, instruction, eatTime, eatDate) VALUES
('Canh gà', 1, 'Luộc gà rồi cho rau củ vào nấu cùng.', 'lunch', 1),
('Khoai tây nghiền', 2, 'Luộc khoai rồi nghiền với sữa.', 'dinner', 3),
('Trứng luộc', 3, 'Luộc trứng trong 10 phút.', 'breakfast', 0);

INSERT INTO UserGroup (groupId, groupName) VALUES
(1, 'Gia đình A'),
(2, 'Nhóm bạn B');

INSERT INTO meal_plan (id, eatTime, eatDate) VALUES
(1, 'lunch', 1),
(2, 'dinner', 3),
(3, 'breakfast', 0);

INSERT INTO dish_use_ingredient (dishId, ingredientName, quantity, unitType) VALUES
(1, 'Ức gà', 1.00, 'kg'),
(1, 'Cà rốt', 0.50, 'kg'),
(2, 'Khoai tây', 1.50, 'kg'),
(2, 'Sữa tươi', 0.20, 'l'),
(3, 'Trứng gà', 2.00, 'pcs');

INSERT INTO meal_has_dish (mealId, dishId) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO User (userId, username, groupId) VALUES
(1, 'Ngọc', 1),
(2, 'Minh', 1),
(3, 'Hùng', 2);

INSERT INTO shoppingList (shoppingListId, buyDate, groupId, userId) VALUES
(1, '2025-06-01', 1, 1),
(2, '2025-06-02', 2, 3);

INSERT INTO buy_ingredient (shoppingListId, ingredientName, quantity, unitType) VALUES
(1, 'Cà rốt', 2.00, 'kg'),
(1, 'Sữa tươi', 1.00, 'l'),
(1, 'Trứng gà', 6.00, 'pcs'),
(2, 'Khoai tây', 2.50, 'kg'),
(2, 'Ức gà', 1.00, 'kg');

-- Select from meal_plan
SELECT * FROM meal_plan where id = 1;

