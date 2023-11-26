-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 20, 2023 at 05:27 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehouse`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `CustomerId` int NOT NULL AUTO_INCREMENT,
  `CustomerName` varchar(45) DEFAULT NULL,
  `ContactPerson` varchar(30) DEFAULT NULL,
  `Address` varchar(30) DEFAULT NULL,
  `City` varchar(30) DEFAULT NULL,
  `PostCode` varchar(10) DEFAULT NULL,
  `Country` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerId`, `CustomerName`, `ContactPerson`, `Address`, `City`, `PostCode`, `Country`) VALUES
(1, 'Customer1', 'Milos Milosevic', 'Atinska 55', 'Kragujevac', '34000', 'Srbija'),
(2, 'Customer2', 'Jovana Ilic', 'Balkanska 40', 'Kragujevac', '34000', 'Srbija'),
(3, 'Customer3', 'Jovan Jovanovic', 'Kacanicka 20', 'Beograd', '11000', 'Srbija'),
(4, 'Customer4', 'Marija Markovic', 'Palilulska 7', 'Beograd', '11000', 'Srbija');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `EmployeeId` int NOT NULL AUTO_INCREMENT,
  `LastName` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `BirthDate` date DEFAULT NULL,
  PRIMARY KEY (`EmployeeId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`EmployeeId`, `LastName`, `FirstName`, `BirthDate`) VALUES
(1, 'Rakic', 'Ilija', '1980-01-02'),
(2, 'Marinkovic', 'Jelena', '1990-08-07'),
(3, 'Ilic', 'Marko', '1995-05-05');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
CREATE TABLE IF NOT EXISTS `orderdetails` (
  `OrderDetailsId` int NOT NULL AUTO_INCREMENT,
  `OrderId` int DEFAULT NULL,
  `ProductId` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`OrderDetailsId`),
  KEY `OrderId` (`OrderId`),
  KEY `ProductId` (`ProductId`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orderdetails`
--

INSERT INTO `orderdetails` (`OrderDetailsId`, `OrderId`, `ProductId`, `Quantity`) VALUES
(1, 1, 1, 10),
(2, 2, 2, 5),
(3, 3, 2, 3),
(4, 4, 6, 2),
(5, 6, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `OrderId` int NOT NULL AUTO_INCREMENT,
  `OrderDate` date DEFAULT NULL,
  `CustomerId` int DEFAULT NULL,
  `EmployeeId` int DEFAULT NULL,
  `ShipperId` int DEFAULT NULL,
  PRIMARY KEY (`OrderId`),
  KEY `CustomerId` (`CustomerId`),
  KEY `EmployeeId` (`EmployeeId`),
  KEY `ShipperId` (`ShipperId`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderId`, `OrderDate`, `CustomerId`, `EmployeeId`, `ShipperId`) VALUES
(1, '2023-12-11', 1, 1, 1),
(2, '2023-11-10', 2, 1, 1),
(3, '2023-10-11', 3, 2, 2),
(4, '2023-11-21', 1, 1, 1),
(6, '2023-11-13', 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `ProductId` int NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(60) DEFAULT NULL,
  `SupplierId` int DEFAULT NULL,
  `ProductCategory` varchar(40) DEFAULT NULL,
  `PricePerUnit` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ProductId`),
  KEY `SupplierId` (`SupplierId`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ProductId`, `ProductName`, `SupplierId`, `ProductCategory`, `PricePerUnit`) VALUES
(1, 'krevet', 1, 'namestaj', '12020.00'),
(2, 'stolice', 1, 'namestaj', '8000.00'),
(6, 'cipele', 2, 'obuca', '5000.00');

-- --------------------------------------------------------

--
-- Table structure for table `shippers`
--

DROP TABLE IF EXISTS `shippers`;
CREATE TABLE IF NOT EXISTS `shippers` (
  `ShipperId` int NOT NULL AUTO_INCREMENT,
  `ShipperName` varchar(45) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ShipperId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `shippers`
--

INSERT INTO `shippers` (`ShipperId`, `ShipperName`, `Phone`) VALUES
(1, 'AKS', '0622222222'),
(2, 'D Express', '062563265'),
(3, 'City Express', '065326563');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `SupplierId` int NOT NULL AUTO_INCREMENT,
  `SupplierName` varchar(45) DEFAULT NULL,
  `ContactPerson` varchar(45) DEFAULT NULL,
  `Address` varchar(30) DEFAULT NULL,
  `City` varchar(30) DEFAULT NULL,
  `PostCode` varchar(10) DEFAULT NULL,
  `Country` varchar(30) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SupplierId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`SupplierId`, `SupplierName`, `ContactPerson`, `Address`, `City`, `PostCode`, `Country`, `Phone`) VALUES
(1, 'Supplier1', 'Milos jovic', '8. marta11', 'Beograd', '11000', 'Srbija', '0656472862'),
(2, 'Supplier2', 'Ana Savic', 'Panonska 12', 'Kragujevac', '34000', 'Srbija', '062356596');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
