-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 03 Sty 2018, 11:45
-- Wersja serwera: 10.1.28-MariaDB
-- Wersja PHP: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `baza`
--
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `baza`
--

CREATE TABLE `baza` (
  `ID` int(11) NOT NULL,
  `Title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL,
  `Artist` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL,
  `Album` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci NOT NULL,
  `Year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `baza`
--

INSERT INTO `baza` (`ID`, `Title`, `Artist`, `Album`, `Year`) VALUES
(1, 'Without Me', 'Eminem', 'The Eminem Show', 2002),
(2, 'Hello', 'Adele', 'Unknown', 2016),
(3, 'Lose Yourself', 'Eminem', '8 mile', 2002),
(4, 'Insomnia', 'Faithless', 'Unknown', 1996),
(5, 'Jestem Bogiem', 'Paktofonika', 'Kinematografia', 2000);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `baza`
--
ALTER TABLE `baza`
  ADD PRIMARY KEY (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
