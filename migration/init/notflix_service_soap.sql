-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: soap-db:3307
-- Generation Time: Nov 13, 2023 at 12:42 AM
-- Server version: 8.1.0
-- PHP Version: 8.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `notflix_service_soap`
--

-- --------------------------------------------------------

--
-- Table structure for table `api_keys`
--

CREATE TABLE `api_keys` (
  `id` int NOT NULL,
  `api_key` varchar(255) NOT NULL,
  `client` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `api_keys`
--

INSERT INTO `api_keys` (`id`, `api_key`, `client`) VALUES
(1, '7e588ca133d33870bf37813863686615', 'notflix_rest_service'),
(2, '9a86dee90e0c2b1696b5545047d4ee44', 'notflix_app');

-- --------------------------------------------------------

--
-- Table structure for table `logging`
--

CREATE TABLE `logging` (
  `id` int NOT NULL,
  `description` text NOT NULL,
  `ip` char(16) NOT NULL,
  `endpoint` text NOT NULL,
  `requested_at` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `request_film`
--

CREATE TABLE `request_film` (
  `requestFilm_id` int NOT NULL,
  `user_id` int NOT NULL,
  `filmName` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `film_path` varchar(256) NOT NULL,
  `film_poster` varchar(256) NOT NULL,
  `film_header` varchar(256) NOT NULL,
  `date_release` date NOT NULL,
  `duration` int NOT NULL,
  `status` enum('pending','accepted','rejected') NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `request_film`
--

INSERT INTO `request_film` (`requestFilm_id`, `user_id`, `filmName`, `description`, `film_path`, `film_poster`, `film_header`, `date_release`, `duration`, `status`) VALUES
(1, 2, 'Movie Title', 'Description of the movie', '/path/to/film', '/path/to/poster', '/path/to/header', '2023-11-07 05:00:00', 120, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `subscription`
--

CREATE TABLE `subscription` (
  `creator_id` int NOT NULL,
  `subscriber_id` int NOT NULL,
  `status` enum('pending','accepted','rejected') NOT NULL DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `api_keys`
--
ALTER TABLE `api_keys`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `logging`
--
ALTER TABLE `logging`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `request_film`
--
ALTER TABLE `request_film`
  ADD PRIMARY KEY (`requestFilm_id`);

--
-- Indexes for table `subscription`
--
ALTER TABLE `subscription`
  ADD PRIMARY KEY (`subscriber_id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `api_keys`
--
ALTER TABLE `api_keys`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `logging`
--
ALTER TABLE `logging`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request_film`
--
ALTER TABLE `request_film`
  MODIFY `requestFilm_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `subscription`
--
ALTER TABLE `subscription`
  MODIFY `subscriber_id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
