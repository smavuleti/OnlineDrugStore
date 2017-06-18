
delimiter ;
DROP SCHEMA IF EXISTS `drugstore`; 
CREATE SCHEMA `drugstore` ;
use `drugstore`;

CREATE TABLE `customer` (
  `custId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  PRIMARY KEY (`custId`)
);

CREATE TABLE `category` (
  `catId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`catId`)
);

CREATE TABLE `product` (
  `prodId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `catId` INT NOT NULL,
  PRIMARY KEY (`prodId`),
  INDEX `prod_cat_FK_idx` (`catId` ASC),
  CONSTRAINT `prod_cat_FK`
    FOREIGN KEY (`catId`)
    REFERENCES `category` (`catId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE TABLE `orders` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `custId` INT NOT NULL,
  `subtotal` DECIMAL(11,3) NULL,
  `tax` DECIMAL(6,2) NULL,
  `total` DECIMAL(15,3) NULL,
  PRIMARY KEY (`orderId`),
  INDEX `order_cust_FK_idx` (`custId` ASC),
  CONSTRAINT `order_cust_FK`
    FOREIGN KEY (`custId`)
    REFERENCES `customer` (`custId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);

CREATE TABLE `orderitem` (
  `orderItemId` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` int(11) NOT NULL,
  `prodId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `orderItem_product_FK_idx` (`prodId`),
  KEY `orderItem_order_FK` (`orderId`),
  CONSTRAINT `orderItem_order_FK` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderItem_product_FK` FOREIGN KEY (`prodId`) REFERENCES `product` (`prodId`) ON DELETE NO ACTION ON UPDATE NO ACTION
); 


CREATE TABLE `inventory` (
  `invId` INT NOT NULL AUTO_INCREMENT,
  `prodId` INT NOT NULL,
  `initialCount` INT NOT NULL,
  `availableCount` INT NOT NULL,
  `soldCount` INT NOT NULL,
  `sellingDetails` VARCHAR(45) NULL,
  PRIMARY KEY (`invId`),
  UNIQUE INDEX `prodId_UNIQUE` (`prodId` ASC),
  CONSTRAINT `Inventory_Prod_FK` FOREIGN KEY (`prodId`) REFERENCES `product` (`prodId`) ON DELETE NO ACTION ON UPDATE NO ACTION
);



INSERT INTO `drugstore`.`category` (`name`) VALUES ('Inhalants');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Cannabinoids');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Depressants');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Opioids & Morphine');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Anabolic Steroids');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Hallucinogens');
INSERT INTO `drugstore`.`category` (`name`) VALUES ('Prescription');

INSERT INTO `drugstore`.`customer` (`name`, `state`) VALUES ('Susan', 'MI');
INSERT INTO `drugstore`.`customer` (`name`, `state`) VALUES ('Joy', 'CA');
INSERT INTO `drugstore`.`customer` (`name`, `state`) VALUES ('Garam', 'CA');
INSERT INTO `drugstore`.`customer` (`name`, `state`) VALUES ('Lucas', 'LA');
INSERT INTO `drugstore`.`customer` (`name`, `state`) VALUES ('Henry', 'TX');

INSERT INTO `drugstore`.`product` (`name`, `price`, `catId`) VALUES ('Aerosol sprays\n', '40', '1');
INSERT INTO `drugstore`.`product` (`name`, `price`, `catId`) VALUES ('Oxycodone', '30', '4');
INSERT INTO `drugstore`.`product` (`name`, `price`, `catId`) VALUES ('Glues', '30', '1');
INSERT INTO `drugstore`.`product` (`name`, `price`, `catId`) VALUES ('Durabolin', '25', '5');


INSERT INTO `drugstore`.`inventory` (`prodId`, `initialCount`, `availableCount`, `soldCount`) VALUES ('3', '30', '30', '30');
INSERT INTO `drugstore`.`inventory` (`prodId`, `initialCount`, `availableCount`, `soldCount`) VALUES ('2', '20', '20', '20');
INSERT INTO `drugstore`.`inventory` (`prodId`, `initialCount`, `availableCount`, `soldCount`) VALUES ('1', '30', '30', '30');
