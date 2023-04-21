CREATE TABLE `mockbean` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) NOT NULL,
  `bean_id` bigint NOT NULL,
  `bean_name` varchar(200) NOT NULL,
  `class_name` varchar(200) NOT NULL,
  `method_name` varchar(200) NOT NULL,
  `mock_value` varchar(1000) DEFAULT NULL,
  `is_delete` tinyint NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bean_id` (`bean_id`),
  KEY `app_name_bean_id__class_method` (`app_name`,`bean_id`,`class_name`,`method_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;