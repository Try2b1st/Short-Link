package org.wgz.shortlink.admin;

public class UserTableTest {
    private static final String SQL = "CREATE TABLE `t_user_%d` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `username` varchar(256) DEFAULT NULL COMMENT '用户名',\n" +
            "  `password` varchar(512) DEFAULT NULL COMMENT '密码',\n" +
            "  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',\n" +
            "  `phone` varchar(128) DEFAULT NULL COMMENT '手机号',\n" +
            "  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',\n" +
            "  `deletion_time` bigint(20) DEFAULT NULL COMMENT '注销时间戳',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            "  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标识 0：未删除 1：已删除',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `t_user_username_uindex` (`username`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1813103115209748483 DEFAULT CHARSET=utf8mb4\n" +
            "\n";

    public static void main(String[] args) {
        for(int i = 0; i< 16;i++){
            System.out.printf((SQL) + "%n", i);
        }
    }
}
