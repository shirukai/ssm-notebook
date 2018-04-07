-- 创建数据库
CREATE DATABASE `notebook` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 创建用户表
CREATE TABLE IF NOT EXISTS `user`(
  `uid` VARCHAR(64) PRIMARY KEY COMMENT '用户id',
  `user_name` VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名:手机号/邮箱',
  `user_pwd`VARCHAR(20) NOT NULL COMMENT '密码',
  `nick_name` VARCHAR(20) NOT NULL UNIQUE COMMENT '昵称',
  `role` INT(1) DEFAULT 0 COMMENT '角色',
  `avatar` VARCHAR(64) COMMENT '头像',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 创建便签表
CREATE TABLE IF NOT EXISTS `short_note`(
  `sid` VARCHAR(64) PRIMARY KEY COMMENT '便签id',
  `uid` VARCHAR(64) COMMENT '用户id',
  `note_content` TEXT COMMENT '便签内容',
  `view_number` INT(10) DEFAULT 0 COMMENT '浏览量',
  `like_number` INT(10) DEFAULT 0 COMMENT '喜欢数',
  `is_public` INT(1) DEFAULT 1 COMMENT '是否公开 1：为公开 0 ：为不公开',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  FOREIGN KEY (`uid`) REFERENCES `user`(`uid`)
) ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT='便签表';

-- 创建笔记表
CREATE TABLE IF NOT EXISTS `book_note`(
  `bid` VARCHAR(64) PRIMARY KEY COMMENT '笔记id',
  `uid` VARCHAR(64) COMMENT '用户id',
  `tid` VARCHAR(64) COMMENT '类别id',
  `book_title` VARCHAR(100) COMMENT '笔记标题',
  `book_content` TEXT COMMENT '笔记内容',
  `view_number` INT(10) DEFAULT 0 COMMENT '浏览量',
  `like_number` INT(10) DEFAULT 0 COMMENT '喜欢数',
  `is_public` INT(1) DEFAULT 1 COMMENT '是否公开 1：为公开 0 ：为不公开',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  FOREIGN KEY (`uid`) REFERENCES `user`(`uid`)
) ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记表';

-- 创建类别表
CREATE TABLE IF NOT EXISTS `type`(
  `tid` VARCHAR(64) PRIMARY KEY COMMENT '类别id',
  `uid` VARCHAR(64) COMMENT '用户id',
  `type` VARCHAR(100) COMMENT '类名',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  FOREIGN KEY (`uid`) REFERENCES `user`(`uid`)
)ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT='类别表';

-- 创建评论表
CREATE TABLE IF NOT EXISTS `comment`(
  `cid` VARCHAR(64) PRIMARY KEY COMMENT '评论id',
  `sender_uid` VARCHAR(64) COMMENT '发送用户id',
  `answer_uid` VARCHAR(64) COMMENT '接受用户id',
  `bid` VARCHAR(64) COMMENT'笔记id',
  `content` TEXT COMMENT '评论内容',
  `like_number` INT(10) DEFAULT 0 COMMENT '喜欢数',
  `is_public` INT(1) DEFAULT 1 COMMENT '是否公开 1：为公开 0 ：为不公开（仅彼此可见）',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  FOREIGN KEY (`bid`) REFERENCES `book_note`(`bid`)
) ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';

-- 创建评论互动表
CREATE TABLE IF NOT EXISTS `interactive`(
  `iid` VARCHAR(64) PRIMARY KEY COMMENT '互动id',
  `sender_uid` VARCHAR(64) COMMENT '发送用户id',
  `answer_uid` VARCHAR(64) COMMENT '接受用户id',
  `cid` VARCHAR(64) COMMENT'评论id',
  `content` TEXT COMMENT '评论内容',
  `create_time` DATETIME DEFAULT NOW() COMMENT '创建日期',
  `modify_time` TIMESTAMP NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp COMMENT '修改时间',
  FOREIGN KEY (`cid`) REFERENCES `comment`(`cid`)
) ENGINE =InnoDB DEFAULT CHARSET=utf8 COMMENT='互动表';