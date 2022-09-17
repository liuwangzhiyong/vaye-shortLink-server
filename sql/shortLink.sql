-- 短链接表
CREATE TABLE `short_link` (
  `id` bigint(20) unsigned NOT NULL COMMENT '主键id',
  `short_key` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '短链接字符串',
  `original_url` varchar(256) NOT NULL COMMENT '原长链接',
  `biz` char(128) DEFAULT NULL COMMENT '系统标识',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un_short_key` (`short_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;