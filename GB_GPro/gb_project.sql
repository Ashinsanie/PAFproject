-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 09:48 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gb_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE `projects` (
  `Project_ID` int(11) NOT NULL,
  `Project_Name` varchar(100) NOT NULL,
  `Project_About` varchar(1000) NOT NULL,
  `Project_Context` varchar(1000) NOT NULL,
  `Project_Goals` varchar(500) NOT NULL,
  `Project_Budget` double NOT NULL,
  `Project_Timeline` varchar(1000) NOT NULL,
  `Team_Name1` varchar(50) NOT NULL,
  `Team_Dis1` varchar(100) NOT NULL,
  `Team_Name2` varchar(50) NOT NULL,
  `Team_Dis2` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`Project_ID`, `Project_Name`, `Project_About`, `Project_Context`, `Project_Goals`, `Project_Budget`, `Project_Timeline`, `Team_Name1`, `Team_Dis1`, `Team_Name2`, `Team_Dis2`) VALUES
(2, 'name', 'about', 'context', 'goals', 0, 'timeline', 'name1', 'dis1', 'name2', 'dis2'),
(3, 'fdfdfds', 'dsadsad', 'safsasa', 'ddwdfdfsf', 1000, 'fdsfdsfdsfd', 'cdsdc', 'cdcdcd', 'cscdscds', 'cdscdscd'),
(4, 'Test1', 'Test2', 'Test3', 'Test4', 5000, 'Test5', 'Test6', 'Test7', 'Test8', 'Test9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`Project_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `Project_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
