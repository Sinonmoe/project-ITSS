drop database if exists Convenience;
CREATE DATABASE Convenience;
USE Convenience;
-- Bảng nhóm người dùng (gia đình)
CREATE TABLE UserGroup (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100)
);

-- Bảng người dùng, dùng ENUM cho role
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) UNIQUE,
    role ENUM('ADMIN', 'MEMBER', 'HOUSEWIFE') NOT NULL DEFAULT 'MEMBER',
    groupId INT,
    FOREIGN KEY (groupId) REFERENCES UserGroup(id)
);

-- Bảng tủ lạnh
CREATE TABLE Fridge (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userGroupId INT,
    FOREIGN KEY (userGroupId) REFERENCES UserGroup(id)
);

-- Bảng loại thực phẩm
CREATE TABLE FoodType (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    unit ENUM('kg', 'l', 'pcs') NOT NULL DEFAULT 'kg'
);

-- Bảng thực phẩm
CREATE TABLE Food (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantity DOUBLE,
    expirationDate DATE,
    foodTypeId INT,
    fridgeId INT,
    FOREIGN KEY (fridgeId) REFERENCES Fridge(id),
    FOREIGN KEY (foodTypeId) REFERENCES FoodType(id)
);

-- Bảng món ăn
CREATE TABLE Dish (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    description TEXT
);

-- Bảng món ăn – nguyên liệu
CREATE TABLE DishFood (
    dishId INT,
    foodTypeId INT,
    quantity DOUBLE,
    PRIMARY KEY (dishId, foodTypeId),
    FOREIGN KEY (dishId) REFERENCES Dish(id),
    FOREIGN KEY (foodTypeId) REFERENCES FoodType(id)
);

-- Bảng kế hoạch bữa ăn
CREATE TABLE MealPlan (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userGroupId INT,
    date DATE,
    FOREIGN KEY (userGroupId) REFERENCES UserGroup(id)
);

-- Bảng chi tiết kế hoạch bữa ăn: món ăn theo buổi
CREATE TABLE MealPlanDish (
    mealPlanId INT,
    dishId INT,
    mealType ENUM('Sáng', 'Trưa', 'Tối') NOT NULL DEFAULT 'Trưa',
    PRIMARY KEY (mealPlanId, dishId),
    FOREIGN KEY (mealPlanId) REFERENCES MealPlan(id),
    FOREIGN KEY (dishId) REFERENCES Dish(id)
);

-- Bảng danh sách mua sắm
CREATE TABLE ShoppingList (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userGroupId INT,
    buyDate DATETIME,
    FOREIGN KEY (userGroupId) REFERENCES UserGroup(id)
);

-- Bảng mục trong danh sách mua sắm
CREATE TABLE ShoppingItem (
    foodTypeId INT,
    listId INT,
    foodName VARCHAR(100),
    quantity DOUBLE,
    isPurchased BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (foodTypeId, listId),
    FOREIGN KEY (listId) REFERENCES ShoppingList(id),
    FOREIGN KEY (foodTypeId) REFERENCES FoodType(id)
);