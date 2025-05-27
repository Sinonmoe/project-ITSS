drop database if exists Convenience;
CREATE DATABASE Convenience;
USE Convenience;
CREATE TABLE fridge (
  fridgeId INT,
  PRIMARY KEY (fridgeId)
);

CREATE TABLE ingredient (
  ingredientName VARCHAR(50),
  quantity DECIMAL(10, 2),
  unitType VARCHAR(20),
  ingredientId INT,
  expirationDate DATE,
  fridgeId INT,
  PRIMARY KEY (ingredientId),
  FOREIGN KEY (fridgeId) REFERENCES fridge(fridgeId)
);

CREATE TABLE dish (
  dishName VARCHAR(100),
  dishId INT,
  instruction TEXT,
  PRIMARY KEY (dishId)
);

CREATE TABLE UserGroup (
  groupId INT,
  groupName VARCHAR(100),
  PRIMARY KEY (groupId)
);

CREATE TABLE meal_plan (
  id INT,
  time DATETIME,
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
  id INT,
  dishId INT,
  PRIMARY KEY (id, dishId),
  FOREIGN KEY (id) REFERENCES meal_plan(id),
  FOREIGN KEY (dishId) REFERENCES dish(dishId)
);

CREATE TABLE User (
  userId INT,
  username VARCHAR(100),
  groupId INT,
  PRIMARY KEY (userId),
  FOREIGN KEY (groupId) REFERENCES UserGroup(groupId)
);

CREATE TABLE shoppingList (
  shoppingListId INT,
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
