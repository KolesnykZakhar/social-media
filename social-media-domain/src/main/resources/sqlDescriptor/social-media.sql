-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Май 08 2017 г., 20:02
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
-- Структура таблицы `authority`
--

CREATE TABLE `authority` (
  `id_authority` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `authority` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `authority`
--

INSERT INTO `authority` (`id_authority`, `id_user`, `authority`) VALUES
(1, 1, 'ROLE_USER'),
(2, 2, 'ROLE_USER'),
(3, 2, 'ROLE_ADMIN'),
(4, 4, 'ROLE_USER'),
(5, 6, 'ROLE_USER'),
(6, 7, 'ROLE_USER'),
(7, 8, 'ROLE_USER'),
(8, 9, 'ROLE_USER'),
(9, 11, 'ROLE_USER'),
(10, 12, 'ROLE_USER'),
(11, 13, 'ROLE_USER'),
(12, 14, 'ROLE_USER'),
(13, 15, 'ROLE_USER'),
(14, 16, 'ROLE_USER'),
(15, 18, 'ROLE_USER'),
(16, 19, 'ROLE_USER'),
(17, 20, 'ROLE_USER'),
(18, 21, 'ROLE_USER'),
(20, 24, 'ROLE_USER'),
(21, 25, 'ROLE_USER'),
(22, 26, 'ROLE_USER'),
(23, 27, 'ROLE_USER'),
(56, 61, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `confirm_emails`
--

CREATE TABLE `confirm_emails` (
  `id_user` int(11) NOT NULL,
  `hashed_email` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
(1, 6, '2017-03-10 11:15:27'),
(1, 7, '2017-03-10 11:15:39'),
(1, 8, '2017-03-10 11:15:52'),
(1, 9, '2017-03-10 11:16:01'),
(1, 11, '2017-03-10 11:16:11'),
(1, 12, '2017-03-10 11:16:19'),
(2, 1, '2017-02-07 09:14:32'),
(2, 3, '2017-05-08 13:33:28'),
(2, 6, '2017-05-08 13:33:42'),
(2, 7, '2017-05-08 13:38:16'),
(3, 2, '2017-05-08 13:33:28'),
(6, 1, '2017-03-10 11:16:43'),
(6, 2, '2017-05-08 13:33:42'),
(7, 1, '2017-03-10 11:16:52'),
(7, 2, '2017-05-08 13:38:16'),
(8, 1, '2017-03-10 11:16:59'),
(9, 1, '2017-03-10 11:17:07'),
(11, 1, '2017-03-10 11:17:12'),
(12, 1, '2017-03-10 11:17:17');

-- --------------------------------------------------------

--
-- Структура таблицы `inviting_for_friendship`
--

CREATE TABLE `inviting_for_friendship` (
  `id_user` int(11) NOT NULL,
  `id_friend` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `inviting_for_friendship`
--

INSERT INTO `inviting_for_friendship` (`id_user`, `id_friend`) VALUES
(2, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `messages`
--

CREATE TABLE `messages` (
  `id_message` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_friend` int(11) NOT NULL,
  `text_message` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `messages`
--

INSERT INTO `messages` (`id_message`, id_sender, id_receiver, `text_message`) VALUES
(1, 1, 2, 'hello1'),
(2, 1, 2, 'hello2'),
(3, 1, 4, 'hello4'),
(4, 2, 1, 'hello3'),
(5, 1, 6, 'hello6'),
(6, 1, 2, 'fgdfgdfgdf'),
(7, 1, 2, 'sdfsdfsd'),
(8, 1, 2, 'sdfsdfsdfsd'),
(9, 1, 2, 'sdfsdfsdfsd'),
(10, 1, 2, 'sdfsdfsdfs'),
(11, 1, 2, 'dasdasdas'),
(12, 1, 2, 'asdasdasda'),
(13, 1, 2, 'asdasdasdas'),
(14, 1, 2, 'asdasdasd'),
(15, 1, 2, 'sssssssssssssssssssssssss'),
(16, 2, 1, 'asdasdasdasda'),
(17, 1, 2, 'fdfgdf'),
(18, 1, 2, 'vcbcvbcvbc'),
(19, 1, 2, 'zzzzzzzzzzzzzzzzzz');

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

INSERT INTO `posts` (`id_post`, text_comment, `date_post`, `id_user`) VALUES
(1, 'asssss', '2017-02-08 16:43:00', 1),
(2, '2222222222', '2017-02-08 16:43:07', 2),
(3, 'asssss', '2017-02-08 16:43:30', 1),
(4, 'st', '2017-02-08 17:04:41', 1),
(5, 'sta', '2017-02-08 17:04:54', 1),
(6, 'ast', '2017-02-08 17:05:07', 1),
(7, 'astfdd', '2017-02-08 17:05:18', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `restore_password`
--

CREATE TABLE `restore_password` (
  `id_user` int(11) NOT NULL,
  `hashed_password` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `birth_date` varchar(32) DEFAULT NULL,
  `email` varchar(32) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) DEFAULT NULL,
  `login` varchar(32) NOT NULL,
  `pass` varchar(128) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `state` tinyint(4) NOT NULL,
  `gender` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `birth_date`, `email`, `first_name`, `last_name`, `login`, `pass`, `phone`, `state`, `gender`) VALUES
(1, '1917-12-24', 'wdas@gmail.com', 'John', 'Trump', 'john', '2bce8464403a72966161c2e3cab92694', '123123', 0, 0),
(2, '2017-03-24', 'tom@tom', 'Tom', 'Fill', 'tom', '2bce8464403a72966161c2e3cab92694', '351535', 0, 0),
(3, '2017-03-24', 'dasd@', 'Jim', 'Mann', 'jim', '2bce8464403a72966161c2e3cab92694', '231231', 1, 0),
(4, '2017-03-24', 'asrw@', 'Frank', 'Odesk', 'frank', '2bce8464403a72966161c2e3cab92694', 'q23123', 0, 0),
(6, '2017-03-24', 'ad.asd@dadasmail.', 'Petro', 'Fedov', 'petFed', '2bce8464403a72966161c2e3cab92694', '+123124124', 0, 0),
(7, '2017-03-24', 'adgaef@maiwersef', 'Vid', 'Tor', 'vidTor', '2bce8464403a72966161c2e3cab92694', '+6578519', 0, 0),
(8, '2017-03-24', 'wqevfq8we8@mrqwbrwqr', 'Franco', 'Tor', 'fran', '2bce8464403a72966161c2e3cab92694', '+375671', 0, 0),
(9, '2017-03-24', 'sfasf8@msfdfds', 'Cam', 'Sed', 'cam', '2bce8464403a72966161c2e3cab92694', '+82506', 0, 0),
(11, '2017-03-24', 'fgsfdg@maasf', 'Fsed', 'Fedov', 'focin', '2bce8464403a72966161c2e3cab92694', '+dskfjh', 0, 0),
(12, '2017-03-24', 'asfas8@mfwef', 'Doom', 'Ca', 'doomca', '2bce8464403a72966161c2e3cab92694', '+842364', 0, 0),
(13, '2017-03-24', 'Ki@m.a', 'Fill', 'Kimber', 'Kim', '2bce8464403a72966161c2e3cab92694', '+563782413', 0, 1),
(14, '2017-03-24', 'fill@pot.com', 'Fillan', 'Potin', 'potifil', '2bce8464403a72966161c2e3cab92694', '78563147821', 0, 1),
(15, '2017-03-24', 'chen@gmail.com', 'Chen', 'Billow', 'chen', '2bce8464403a72966161c2e3cab92694', '+52718342', 0, 0),
(16, '2006-09-29', 'comja@gmail.com', 'Johnathan', 'Com', 'joc', '2bce8464403a72966161c2e3cab92694', '+5169783613', 0, 0),
(18, '2017-03-24', 'aSDasd88@mail.ru', 'Zak', 'Ken', 'zak', '2bce8464403a72966161c2e3cab92694', '+6511203812', 0, 0),
(19, '2004-08-28', 'cha@dsjkfa.com', 'kim', 'cha', 'chaki', '2bce8464403a72966161c2e3cab92694', '187593512542', 0, 0),
(20, '2002-10-28', 'pitak@gamil.com', 'Pit', 'Pack', 'pitac', '2bce8464403a72966161c2e3cab92694', '+1630575234', 0, 0),
(21, '2017-03-28', 'qweqweq8@mail.ru', 'Kima', 'Miko', 'kimiko', '2bce8464403a72966161c2e3cab92694', '+32198', 0, 1),
(24, '2011-12-12', 'asdasd@asd', 'Fillina', 'Fillatova', '2222', '7d56e9d8b5d4861d5b0115da1f804b1c', '2222', 0, 1),
(25, '2010-01-20', 'ssda@dsfs.sdf', 'Mike', 'Mitchel', '3333', 'da8fd23b6178ff144a26254421c54d5e', '+38012121212', 0, 0),
(26, '2014-10-29', 'gdskjfgfhdskj2@sad', '4444', '4444', '4444', 'c4d5aa6df06a7864f1c98a2541e4baaf', '24124215', 0, 1),
(27, '2015-10-27', 'ssafsdf@hgflh.d', '5555', '5555', '5555', '64f81d141fe7b609afeeb42f0047607f', '5555', 0, 0),
(28, '2014-11-29', 'vend.88@eqw.qweru', '6666', '6666', '6666', 'd1ae269fcecfd2c7cc1924cc20abbbeb', '6666', 2, 0),
(61, '2015-10-29', 'vend.88@inbox.ru', '111', '111', '111', '9c1f8b5839047b495cde1bec9eabf1a9', '111', 0, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id_authority`),
  ADD KEY `authority_users_id_user_fk` (`id_user`);

--
-- Индексы таблицы `confirm_emails`
--
ALTER TABLE `confirm_emails`
  ADD UNIQUE KEY `confirm_emails_hashed_email_uindex` (`hashed_email`),
  ADD KEY `hashed_emails_users_id_user_fk` (`id_user`);

--
-- Индексы таблицы `friends`
--
ALTER TABLE `friends`
  ADD PRIMARY KEY (`id_user`,`id_friend`),
  ADD KEY `FK_9pwml5q21cfq50vrhnqitl3qw` (`id_friend`);

--
-- Индексы таблицы `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id_message`);

--
-- Индексы таблицы `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id_post`),
  ADD KEY `FK_7yanggi6cfb61rtl9am6q10d8` (`id_user`);

--
-- Индексы таблицы `restore_password`
--
ALTER TABLE `restore_password`
  ADD UNIQUE KEY `restore_password_id_user_uindex` (`id_user`),
  ADD UNIQUE KEY `restore_password_hashed_password_uindex` (`hashed_password`);

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
-- AUTO_INCREMENT для таблицы `authority`
--
ALTER TABLE `authority`
  MODIFY `id_authority` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT для таблицы `messages`
--
ALTER TABLE `messages`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id_post` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `authority`
--
ALTER TABLE `authority`
  ADD CONSTRAINT `authority_users_id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `confirm_emails`
--
ALTER TABLE `confirm_emails`
  ADD CONSTRAINT `hashed_emails_users_id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `friends`
--
ALTER TABLE `friends`
  ADD CONSTRAINT `FK_9pwml5q21cfq50vrhnqitl3qw` FOREIGN KEY (`id_friend`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_lmwtvmmtgc3gth8shjhmrjfhq` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `FK_7yanggi6cfb61rtl9am6q10d8` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `restore_password`
--
ALTER TABLE `restore_password`
  ADD CONSTRAINT `restore_password_users_id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
