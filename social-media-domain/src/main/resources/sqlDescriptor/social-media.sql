-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Май 12 2017 г., 21:31
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
-- Структура таблицы `bookmarks`
--

CREATE TABLE `bookmarks` (
  `id_bookmark` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_post` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `bookmarks`
--

INSERT INTO `bookmarks` (`id_bookmark`, `id_user`, `id_post`) VALUES
(1, 1, 43),
(2, 1, 44);

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
(1, 2, '2017-05-12 19:10:27'),
(1, 6, '2017-03-10 11:15:27'),
(1, 7, '2017-03-10 11:15:39'),
(1, 8, '2017-03-10 11:15:52'),
(1, 9, '2017-03-10 11:16:01'),
(1, 11, '2017-05-09 18:34:00'),
(1, 12, '2017-05-09 18:33:56'),
(2, 1, '2017-05-12 19:10:27'),
(2, 3, '2017-05-08 13:33:28'),
(2, 4, '2017-05-09 18:39:45'),
(2, 6, '2017-05-08 13:33:42'),
(2, 7, '2017-05-08 13:38:16'),
(2, 8, '2017-05-09 18:37:42'),
(3, 2, '2017-05-08 13:33:28'),
(4, 2, '2017-05-09 18:39:45'),
(6, 1, '2017-03-10 11:16:43'),
(6, 2, '2017-05-08 13:33:42'),
(7, 1, '2017-03-10 11:16:52'),
(7, 2, '2017-05-08 13:38:16'),
(8, 1, '2017-03-10 11:16:59'),
(8, 2, '2017-05-09 18:37:42'),
(9, 1, '2017-03-10 11:17:07'),
(11, 1, '2017-05-09 18:34:00'),
(12, 1, '2017-05-09 18:33:56');

-- --------------------------------------------------------

--
-- Структура таблицы `images`
--

CREATE TABLE `images` (
  `id_image` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `name_image` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `images`
--

INSERT INTO `images` (`id_image`, `id_user`, `name_image`) VALUES
(7, 1, '1494511209128.jpg'),
(9, 1, '1494511224712.jpg'),
(10, 1, '1494511238199.jpg'),
(11, 1, '1494511244514.jpg'),
(13, 1, '1494537124768.png');

-- --------------------------------------------------------

--
-- Структура таблицы `inviting_for_friendship`
--

CREATE TABLE `inviting_for_friendship` (
  `id_user` int(11) NOT NULL,
  `id_friend` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `media_posts`
--

CREATE TABLE `media_posts` (
  `id_media` int(11) NOT NULL,
  `id_post` int(11) NOT NULL,
  `media_files` varchar(256) NOT NULL,
  `media_type` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `media_posts`
--

INSERT INTO `media_posts` (`id_media`, `id_post`, `media_files`, `media_type`) VALUES
(3, 34, '1494536968133.bmp', 0),
(4, 34, '1494536968148.bmp', 0),
(5, 35, '1494537300567.png', 0),
(7, 36, '1494537336335.bmp', 0),
(8, 36, '1494537336346.png', 0),
(9, 37, '1494537575358.bmp', 0),
(10, 37, '1494537575370.png', 0),
(11, 38, '1494575175326.bmp', 0),
(12, 38, '1494575175306.png', 0),
(37, 43, '1494588744478.mp3', 1),
(38, 43, '1494588744351.bmp', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `messages`
--

CREATE TABLE `messages` (
  `id_message` int(11) NOT NULL,
  `id_sender` int(11) NOT NULL,
  `id_receiver` int(11) NOT NULL,
  `text_message` varchar(256) NOT NULL,
  `unread` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `messages`
--

INSERT INTO `messages` (`id_message`, `id_sender`, `id_receiver`, `text_message`, `unread`) VALUES
(1, 1, 2, 'hello1', 0),
(2, 1, 2, 'hello2', 0),
(3, 1, 4, 'hello4', 0),
(4, 2, 1, 'hello3', 0),
(5, 1, 6, 'hello6', 0),
(6, 1, 2, 'fgdfgdfgdf', 0),
(7, 1, 2, 'sdfsdfsd', 0),
(8, 1, 2, 'sdfsdfsdfsd', 0),
(9, 1, 2, 'sdfsdfsdfsd', 0),
(10, 1, 2, 'sdfsdfsdfs', 0),
(11, 1, 2, 'dasdasdas', 0),
(12, 1, 2, 'asdasdasda', 0),
(13, 1, 2, 'asdasdasdas', 0),
(14, 1, 2, 'asdasdasd', 0),
(15, 1, 2, 'sssssssssssssssssssssssss', 0),
(16, 2, 1, 'asdasdasdasda', 0),
(17, 1, 2, 'fdfgdf', 0),
(18, 1, 2, 'vcbcvbcvbc', 0),
(19, 1, 2, 'zzzzzzzzzzzzzzzzzz', 0),
(20, 1, 2, 'fdgdfgfdgdf', 0),
(21, 1, 2, 'bvnvbnvbn', 0),
(22, 1, 6, 'fdgdfgfdgdfgdf', 0),
(23, 1, 6, 'ghnfghfghfg', 0),
(24, 7, 1, 'svfdas  safa gar\nga\nrg a\nreg\n a\nrg\n aerg\n a', 0),
(25, 7, 1, 'vbnvbnvb', 0),
(26, 6, 1, 'Bdhhjdjd\n', 0),
(27, 4, 1, 'nvhjgjhgjhg\n', 0),
(28, 4, 1, 'sfdfasdfsf', 0),
(29, 2, 1, 'dasdasdas', 0),
(30, 2, 1, '111111111111', 0),
(31, 2, 1, 'sdfsdfsdf', 0),
(32, 2, 1, 'sdfsdfsd', 0),
(33, 1, 2, 'ggggggggggggg', 0),
(34, 1, 2, 'hhhhhhhhhhhhhhhhhhhhhhhhhhhhh', 0),
(35, 1, 2, 'fsdfdsfsdfsd', 0),
(36, 1, 2, 'sdfsdfsdf', 0),
(37, 1, 2, 'gggggggggggg', 0),
(38, 1, 2, '11111111111111111111', 0),
(39, 2, 1, 'dsfsdfsdfsdfsfas hbfhjlsdhfad flkjhdsfsdfsdfsdfsfas hbfhjlsdhfad flkjhdsfsdfsdfsdfsfas hbfhjlsdhfad flkjhdsfsdfsdfsdfsfas hbfhjlsdhfad flkjhdsfsdfsdfsdfsfas hbfhjlsdhfad flkjh', 0),
(40, 1, 2, 'fdfdfd', 0),
(41, 1, 2, 'dhkgkjagf kafhasf hlkhf shjdf lsdhf f;shjdfkjsdlkfj asjfaskjf;lask dlask;ld kas;lkd;l skd;sa; da', 0),
(42, 2, 1, 'asdasda sd asd', 0),
(43, 2, 8, 'asdasdasdas', 0),
(44, 8, 2, 'asdsadsada', 0),
(45, 2, 4, 'asdasdsadasasda s', 0),
(46, 4, 2, 'asdasdasd', 0),
(47, 4, 1, 'asdasdasd', 0),
(48, 4, 1, 'asdasdasdas', 0),
(49, 1, 4, 'asdasdasda', 0),
(50, 2, 1, 'asdasd s asd as', 0),
(51, 1, 2, 'asdasd as', 0),
(52, 2, 1, 'asdasda sdas d', 0),
(53, 1, 2, 'sdfsdf dfsdfsd fsd', 0),
(54, 2, 1, 'asdasd asd a', 0),
(55, 1, 2, 'asdasdas', 0),
(56, 1, 2, 'asdasdsad', 0),
(57, 2, 1, 'sadasda ', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `posts`
--

CREATE TABLE `posts` (
  `id_post` int(11) NOT NULL,
  `text_post` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date_post` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `posts`
--

INSERT INTO `posts` (`id_post`, `text_post`, `id_user`, `date_post`) VALUES
(1, 'asssss', 1, '2017-05-10 09:25:52'),
(2, '2222222222', 2, '2017-05-10 09:25:52'),
(3, 'asssss', 1, '2017-05-10 09:25:52'),
(4, 'st', 1, '2017-05-10 09:25:52'),
(5, 'sta', 1, '2017-05-10 09:25:52'),
(6, 'ast', 1, '2017-05-10 09:25:52'),
(7, 'astfdd', 1, '2017-05-10 09:25:52'),
(8, 'sad asda\n s\nda\nsd\n sa\nd\n as\nd\na ', 1, '2017-05-10 10:04:17'),
(9, '111111111111111111111111111111111\n11111111111111111111111111111', 1, '2017-05-10 10:04:39'),
(10, '11111111111', 1, '2017-05-10 10:04:47'),
(11, '222222222222222222222222222', 1, '2017-05-10 10:07:28'),
(12, 'sdfsdf', 1, '2017-05-10 10:09:33'),
(13, 's', 1, '2017-05-10 10:10:24'),
(14, 'dkljshf lsdjhf sd\nf\nsd\n fsdfjsdlkfj lksdjfk sdlfj;sdkd''a;LDP[A SDSAD \nASDAS DKSAJFDA', 1, '2017-05-10 10:11:09'),
(15, 'D', 1, '2017-05-10 10:11:18'),
(16, 'SDFSDFSD', 1, '2017-05-10 10:11:20'),
(17, 'DSAD ASKJDHA SDJASI; DASDQWEQW', 1, '2017-05-10 10:11:24'),
(18, 'DF ASLUFSLAFK;SDKFL;''SDK FSLKFD\nFSD\nF\nSD\nF\nSD\nF\nSDFS', 1, '2017-05-10 10:11:30'),
(19, 'SADAS', 1, '2017-05-10 10:11:42'),
(20, 'ewrwervweb we rw', 1, '2017-05-10 10:33:59'),
(21, 'wrwer', 1, '2017-05-10 10:34:06'),
(22, 'wer werwe', 1, '2017-05-10 10:34:09'),
(23, 'werw er', 1, '2017-05-10 10:34:16'),
(24, 'wer werw', 1, '2017-05-10 10:34:23'),
(25, 'sdfsdf sdfsd fs', 1, '2017-05-10 10:58:58'),
(26, 'sdfsdfsd', 1, '2017-05-10 10:59:06'),
(27, 'sdfsdfsdfsdf', 1, '2017-05-10 10:59:26'),
(28, 'fsdfsdfsdfsd', 1, '2017-05-10 10:59:39'),
(29, 'asdasdasasdas as\nd\n as\nd\n as\nd a', 1, '2017-05-10 11:03:29'),
(30, 'fsdfsdf11111111111111111111111111', 1, '2017-05-11 15:26:02'),
(31, '222', 4, '2017-05-11 15:26:10'),
(32, 'tttt', 1, '2017-05-11 16:15:31'),
(33, 'sssssss', 1, '2017-05-11 21:03:23'),
(34, 'image2', 1, '2017-05-11 21:09:28'),
(35, 'sdsds', 1, '2017-05-11 21:15:00'),
(36, 'ddd', 1, '2017-05-11 21:15:36'),
(37, 'ddd', 1, '2017-05-11 21:19:35'),
(38, 'sadasdasd', 1, '2017-05-12 07:46:15'),
(43, 'audio', 2, '2017-05-12 11:32:25'),
(44, 'ss', 2, '2017-05-12 11:44:46'),
(45, 'asdasd', 2, '2017-05-12 11:45:05'),
(46, 'asdasdsad', 2, '2017-05-12 11:45:07'),
(47, 'asdasdasd', 2, '2017-05-12 11:45:09'),
(48, 'dasdasdas', 2, '2017-05-12 11:45:11'),
(50, 'ssssssssssssssssssssssssssssssssss', 2, '2017-05-12 11:49:26'),
(51, 'ddd', 1, '2017-05-12 11:59:26');

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
  `gender` tinyint(4) NOT NULL,
  `visibility` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id_user`, `birth_date`, `email`, `first_name`, `last_name`, `login`, `pass`, `phone`, `state`, `gender`, `visibility`) VALUES
(1, '1917-12-24', 'wdas@gmail.com', 'John', 'Trump', 'john', '2bce8464403a72966161c2e3cab92694', '123123', 0, 0, 0),
(2, '2017-03-24', 'tom@tom', 'Tom', 'Fill', 'tom', '2bce8464403a72966161c2e3cab92694', '351535', 0, 0, 0),
(3, '2017-03-24', 'dasd@', 'Jim', 'Mann', 'jim', '2bce8464403a72966161c2e3cab92694', '231231', 1, 0, 0),
(4, '2017-03-24', 'asrw@', 'Frank', 'Odesk', 'frank', '2bce8464403a72966161c2e3cab92694', 'q23123', 0, 1, 0),
(6, '2017-03-24', 'ad.asd@dadasmail.', 'Petro', 'Fedov', 'petFed', '2bce8464403a72966161c2e3cab92694', '+123124124', 0, 0, 0),
(7, '2017-03-24', 'adgaef@maiwersef', 'Vid', 'Tor', 'vidTor', '2bce8464403a72966161c2e3cab92694', '+6578519', 0, 0, 0),
(8, '2017-03-24', 'wqevfq8we8@mrqwbrwqr', 'Franco', 'Tor', 'fran', '2bce8464403a72966161c2e3cab92694', '+375671', 0, 0, 0),
(9, '2017-03-24', 'sfasf8@msfdfds', 'Cam', 'Sed', 'cam', '2bce8464403a72966161c2e3cab92694', '+82506', 0, 0, 0),
(11, '2017-03-24', 'fgsfdg@maasf', 'Fsed', 'Fedov', 'focin', '2bce8464403a72966161c2e3cab92694', '+dskfjh', 0, 0, 0),
(12, '2017-03-24', 'asfas8@mfwef', 'Doom', 'Ca', 'doomca', '2bce8464403a72966161c2e3cab92694', '+842364', 0, 0, 0),
(13, '2017-03-24', 'Ki@m.a', 'Fill', 'Kimber', 'Kim', '2bce8464403a72966161c2e3cab92694', '+563782413', 0, 1, 0),
(14, '2017-03-24', 'fill@pot.com', 'Fillan', 'Potin', 'potifil', '2bce8464403a72966161c2e3cab92694', '78563147821', 0, 1, 0),
(15, '2017-03-24', 'chen@gmail.com', 'Chen', 'Billow', 'chen', '2bce8464403a72966161c2e3cab92694', '+52718342', 0, 0, 0),
(16, '2006-09-29', 'comja@gmail.com', 'Johnathan', 'Com', 'joc', '2bce8464403a72966161c2e3cab92694', '+5169783613', 0, 0, 0),
(18, '2017-03-24', 'aSDasd88@mail.ru', 'Zak', 'Ken', 'zak', '2bce8464403a72966161c2e3cab92694', '+6511203812', 0, 0, 0),
(19, '2004-08-28', 'cha@dsjkfa.com', 'kim', 'cha', 'chaki', '2bce8464403a72966161c2e3cab92694', '187593512542', 0, 0, 0),
(20, '2002-10-28', 'pitak@gamil.com', 'Pit', 'Pack', 'pitac', '2bce8464403a72966161c2e3cab92694', '+1630575234', 0, 0, 0),
(21, '2017-03-28', 'qweqweq8@mail.ru', 'Kima', 'Miko', 'kimiko', '2bce8464403a72966161c2e3cab92694', '+32198', 0, 1, 0),
(24, '2011-12-12', 'asdasd@asd', 'Fillina', 'Fillatova', '2222', '7d56e9d8b5d4861d5b0115da1f804b1c', '2222', 0, 1, 0),
(25, '2010-01-20', 'ssda@dsfs.sdf', 'Mike', 'Mitchel', '3333', 'da8fd23b6178ff144a26254421c54d5e', '+38012121212', 0, 0, 0),
(26, '2014-10-29', 'gdskjfgfhdskj2@sad', '4444', '4444', '4444', 'c4d5aa6df06a7864f1c98a2541e4baaf', '24124215', 0, 1, 0),
(27, '2015-10-27', 'ssafsdf@hgflh.d', '5555', '5555', '5555', '64f81d141fe7b609afeeb42f0047607f', '5555', 0, 0, 0),
(28, '2014-11-29', 'vend.88@eqw.qweru', '6666', '6666', '6666', 'd1ae269fcecfd2c7cc1924cc20abbbeb', '6666', 2, 0, 0),
(61, '2015-10-29', 'vend.88@inbox.ru', '111', '111', '111', '9c1f8b5839047b495cde1bec9eabf1a9', '111', 0, 1, 0);

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
-- Индексы таблицы `bookmarks`
--
ALTER TABLE `bookmarks`
  ADD PRIMARY KEY (`id_bookmark`),
  ADD KEY `bookmarks_users_id_user_fk` (`id_user`),
  ADD KEY `bookmarks_posts_id_post_fk` (`id_post`);

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
-- Индексы таблицы `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id_image`),
  ADD UNIQUE KEY `images_name_image_uindex` (`name_image`);

--
-- Индексы таблицы `media_posts`
--
ALTER TABLE `media_posts`
  ADD PRIMARY KEY (`id_media`),
  ADD KEY `media_posts_posts_id_post_fk` (`id_post`);

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
-- AUTO_INCREMENT для таблицы `bookmarks`
--
ALTER TABLE `bookmarks`
  MODIFY `id_bookmark` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `images`
--
ALTER TABLE `images`
  MODIFY `id_image` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT для таблицы `media_posts`
--
ALTER TABLE `media_posts`
  MODIFY `id_media` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- AUTO_INCREMENT для таблицы `messages`
--
ALTER TABLE `messages`
  MODIFY `id_message` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;
--
-- AUTO_INCREMENT для таблицы `posts`
--
ALTER TABLE `posts`
  MODIFY `id_post` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
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
-- Ограничения внешнего ключа таблицы `bookmarks`
--
ALTER TABLE `bookmarks`
  ADD CONSTRAINT `bookmarks_posts_id_post_fk` FOREIGN KEY (`id_post`) REFERENCES `posts` (`id_post`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bookmarks_users_id_user_fk` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Ограничения внешнего ключа таблицы `media_posts`
--
ALTER TABLE `media_posts`
  ADD CONSTRAINT `media_posts_posts_id_post_fk` FOREIGN KEY (`id_post`) REFERENCES `posts` (`id_post`) ON DELETE CASCADE ON UPDATE CASCADE;

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
