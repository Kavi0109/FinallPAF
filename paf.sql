-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2022 at 08:23 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `BillID` int(10) NOT NULL,
  `BillCode` varchar(10) NOT NULL,
  `BillMonth` varchar(20) NOT NULL,
  `CurrentRead` int(11) NOT NULL,
  `PreviousRead` int(11) NOT NULL,
  `TotalUnits` int(10) NOT NULL,
  `FinalAmount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`BillID`, `BillCode`, `BillMonth`, `CurrentRead`, `PreviousRead`, `TotalUnits`, `FinalAmount`) VALUES
(1, 'B270', 'November', 15000, 10000, 5000, 30000),
(2, 'B280', 'May', 10000, 20000, 20, 102),
(3, 'B300', 'April', 2500, 2000, 500, 3000),
(4, 'B310', 'May', 12000, 10000, 479, 2874);

-- --------------------------------------------------------

--
-- Table structure for table `connection`
--

CREATE TABLE `connection` (
  `connectionID` int(11) NOT NULL,
  `customerName` varchar(20) NOT NULL,
  `connectionType` varchar(20) NOT NULL,
  `requestLoad` varchar(8) NOT NULL,
  `contractDemand` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `connection`
--

INSERT INTO `connection` (`connectionID`, `customerName`, `connectionType`, `requestLoad`, `contractDemand`, `address`, `email`) VALUES
(1000, 'Miya San', 'Personal', '200KW', '200KW', 'Colombo', 'miyaSan12@gmail.com'),
(1001, 'Salern Jio', 'Commercial', '600KW', '500KW', 'Kollupitiya', 'saljio13@gmail.com'),
(1002, 'Kavi Perera', 'Industrial', '800KW', '700KW', 'Malabe', 'kaviperera0109@gmail'),
(1003, 'Rishma Packeeran', 'Personal', '300KW', '200KW', 'Piliyandala', 'rishmapackeeran@gmai'),
(1004, 'Ushani D', 'Industrial', '500KW', '400KW', 'Dehiwala', 'ushaniD@gmail.com'),
(1005, 'Humaira R', 'Industrial', '900KW', '800KW', 'Malabe', 'humairarizwan2000@gm');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentId` int(11) NOT NULL,
  `accountNum` int(11) NOT NULL,
  `amount` float NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentId`, `accountNum`, `amount`, `date`) VALUES
(2, 40012, 2000, '2022-01-20'),
(3, 99745, 7800, '2022-01-15'),
(4, 40026, 1500, '2022-04-05'),
(5, 54100, 8000, '2022-04-10'),
(6, 99589, 5000, '2022-04-28');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `accountNo` int(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `otp` int(10) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `email`, `pass`, `mobile`, `accountNo`, `type`, `otp`, `status`) VALUES
('Rishma Packeeran', 'rishmapackeeran@gmail.com', 'rishma', '0789562458', 31411825, 'Researcher', 291, 'Active'),
('Kaveesha Perera', 'kaviperera0109@gmail.com', 'kavi', '0778942369', 28296459, 'Consumer', 259, 'Active'),
('Humaira Rizwan', 'humairarizwan@gmail.com', 'humaira', '0718223459', 49888694, 'Investor', 340, 'Active'),
('Ushani Dahanayake', 'ushanidaha@gmail.com', 'ushani', '0715442631', 20008607, 'Investor', 72, 'Inactive'),
('Kumari Perera', 'mackperera19@gmail.com', 'kuma', '0715662843', 52548386, 'Consumer', 45, 'Inactive');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`BillID`);

--
-- Indexes for table `connection`
--
ALTER TABLE `connection`
  ADD PRIMARY KEY (`connectionID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `BillID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `connection`
--
ALTER TABLE `connection`
  MODIFY `connectionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1006;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `paymentId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
