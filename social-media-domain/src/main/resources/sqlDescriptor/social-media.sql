-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Мар 05 2017 г., 14:31
-- Версия сервера: 10.1.19-MariaDB
-- Версия PHP: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `social-media`
--

-- --------------------------------------------------------

--
-- Структура таблицы `friends`
--

CREATE TABLE `friends` (
  `id_user` int(11) NOT NULL,
  `id_friend` int(11) NOT NULL,
  `date_friendship` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `friends`
--

INSERT INTO `friends` (`id_user`, `id_friend`, `date_friendship`) VALUES
(1, 2, '2017-02-07 09:14:23'),
(1, 3, '2017-02-08 16:37:06'),
(2, 1, '2017-02-07 09:14:32'),
(3, 1, '2017-02-08 16:37:13');

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

CREATE TABLE `posts` (
  `id_post` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `date_post` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `posts`
--

INSERT INTO `posts` (`id_post`, `comment`, `date_post`, `id_user`) VALUES
(1, 'asssss', '2017-02-08 16:43:00', 1),
(2, '2222222222', '2017-02-08 16:43:07', 2),
(3, 'asssss', '2017-02-08 16:43:30', 1),
(4, 'st', '2017-02-08 17:04:41', 1),
(5, 'sta', '2017-02-08 17:04:54', 1),
(6, 'ast', '2017-02-08 17:05:07', 1),
(7, 'astfdd', '2017-02-08 17:05:18', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `birth_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `email` varchar(32) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) DEFAULT NULL,
  `login` varchar(32) NOT NULL,
  `pass` varchar(128) NOT NULL,
  `phone` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `birth_date`, `email`, `first_name`, `last_name`, `login`, `pass`, `phone`) VALUES
(1, '2017-03-04 20:31:07', 'wdas@', 'John', 'Trump', 'john', 'B59C67BF196A4758191E42F76670CEBA', '123123'),
(2, '2017-03-04 20:31:09', 'sadas@', 'Tom', NULL, 'tom', 'B59C67BF196A4758191E42F76670CEBA', '351535'),
(3, '2017-03-04 20:31:10', 'dasd@', 'Jim', NULL, 'jim', 'B59C67BF196A4758191E42F76670CEBA', '231231'),
(4, '2017-03-04 20:31:11', 'asrw@', 'Frank', NULL, 'frank', 'B59C67BF196A4758191E42F76670CEBA', 'q23123'),
(5, '2016-11-29 22:00:00', '11@111', '111', '111', '1111', 'B59C67BF196A4758191E42F76670CEBA', '111');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id_user`,`id_friend`),
  ADD KEY `FK_9pwml5q21cfq50vrhnqitl3qw` (`id_friend`);

--
-- Индексы таблицы `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id_post`),
  ADD KEY `FK_7yanggi6cfb61rtl9am6q10d8` (`id_user`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `users_email_uindex` (`email`),
  ADD UNIQUE KEY `users_login_uindex` (`login`),
  ADD UNIQUE KEY `users_phone_uindex` (`phone`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id_post` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `FK_9pwml5q21cfq50vrhnqitl3qw` FOREIGN KEY (`id_friend`) REFERENCES `users` (`id_user`),
  ADD CONSTRAINT `FK_lmwtvmmtgc3gth8shjhmrjfhq` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Ограничения внешнего ключа таблицы `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK_7yanggi6cfb61rtl9am6q10d8` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
