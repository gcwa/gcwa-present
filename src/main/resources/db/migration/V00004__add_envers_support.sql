--
-- Table structure for table `category_aud`
--

CREATE TABLE `category_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `description_en` varchar(1024) DEFAULT NULL,
  `description_fr` varchar(1024) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title_en` varchar(255) DEFAULT NULL,
  `title_fr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `subcategory_aud`
--

CREATE TABLE `subcategory_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `description_en` varchar(1024) DEFAULT NULL,
  `description_fr` varchar(1024) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `title_en` varchar(255) DEFAULT NULL,
  `title_fr` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `collection_aud`
--

CREATE TABLE `collection_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `description_en` varchar(1024) DEFAULT NULL,
  `description_fr` varchar(1024) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `title_en` varchar(255) DEFAULT NULL,
  `title_fr` varchar(255) DEFAULT NULL,
  `subcategory_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `seed_aud`
--

CREATE TABLE `seed_aud` (
  `id` bigint(20) NOT NULL,
  `rev` int(11) NOT NULL,
  `revtype` tinyint(4) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `collection_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `revinfo`
--

CREATE TABLE `revinfo` (
  `rev` int(11) NOT NULL,
  `revtstmp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- --------------------------------------------------------



--
-- Indexes for dumped tables
--

--
-- Indexes for table `category_aud`
--
ALTER TABLE `category_aud`
  ADD PRIMARY KEY (`id`,`rev`),
  ADD KEY `FKc9m640crhsib2ws80um6xuk1w` (`rev`);

--
-- Indexes for table `subcategory_aud`
--
ALTER TABLE `subcategory_aud`
  ADD PRIMARY KEY (`id`,`rev`),
  ADD KEY `FKerejiyqws3e1pirqpkvh956ir` (`rev`);
  
--
-- Indexes for table `collection_aud`
--
ALTER TABLE `collection_aud`
  ADD PRIMARY KEY (`id`,`rev`),
  ADD KEY `FKi85irm542lohmbe1scaoacfad` (`rev`);

--
-- Indexes for table `seed_aud`
--
ALTER TABLE `seed_aud`
  ADD PRIMARY KEY (`id`,`rev`),
  ADD KEY `FK3vbakoptodrnyjy4xfpyw6a6y` (`rev`);

--
-- Indexes for table `revinfo`
--
ALTER TABLE `revinfo`
  ADD PRIMARY KEY (`rev`);



--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `revinfo`
--
ALTER TABLE `revinfo`
  MODIFY `rev` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `category_aud`
--
ALTER TABLE `category_aud`
  ADD CONSTRAINT `FKc9m640crhsib2ws80um6xuk1w` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`);

  --
-- Constraints for table `subcategory_aud`
--
ALTER TABLE `subcategory_aud`
  ADD CONSTRAINT `FKerejiyqws3e1pirqpkvh956ir` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`);
  
--
-- Constraints for table `collection_aud`
--
ALTER TABLE `collection_aud`
  ADD CONSTRAINT `FKi85irm542lohmbe1scaoacfad` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`);

--
-- Constraints for table `seed_aud`
--
ALTER TABLE `seed_aud`
  ADD CONSTRAINT `FK3vbakoptodrnyjy4xfpyw6a6y` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`);



