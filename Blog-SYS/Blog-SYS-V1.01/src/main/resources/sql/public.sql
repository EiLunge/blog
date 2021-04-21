/*
 Navicat PostgreSQL Data Transfer

 Source Server         : test
 Source Server Type    : PostgreSQL
 Source Server Version : 100011
 Source Host           : localhost:5432
 Source Catalog        : blog
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100011
 File Encoding         : 65001

 Date: 26/03/2020 14:35:19
*/


-- ----------------------------
-- Sequence structure for comment_like_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."comment_like_id_seq" CASCADE;
CREATE SEQUENCE "public"."comment_like_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial01
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial01" CASCADE;
CREATE SEQUENCE "public"."serial01" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial02
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial02" CASCADE;
CREATE SEQUENCE "public"."serial02" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial03
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial03" CASCADE;
CREATE SEQUENCE "public"."serial03" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial04
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial04" CASCADE;
CREATE SEQUENCE "public"."serial04" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial05
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial05" CASCADE;
CREATE SEQUENCE "public"."serial05" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial06
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial06" CASCADE;
CREATE SEQUENCE "public"."serial06" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial07
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial07" CASCADE;
CREATE SEQUENCE "public"."serial07" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial08
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial08" CASCADE;
CREATE SEQUENCE "public"."serial08" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for serial09
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."serial09" CASCADE;
CREATE SEQUENCE "public"."serial09" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."admin_user" ;
CREATE TABLE "public"."admin_user" (
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO "public"."admin_user" VALUES ('掘csdn幕后黑手', '123456');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS "public"."article";
CREATE TABLE "public"."article" (
  "article_id" int4 NOT NULL DEFAULT nextval('serial01'::regclass),
  "user_id" int4 NOT NULL,
  "tag_id" varchar(60) COLLATE "pg_catalog"."default" NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "abstracts" varchar(255) COLLATE "pg_catalog"."default",
  "pc_content" text COLLATE "pg_catalog"."default" NOT NULL,
  "wx_content" text COLLATE "pg_catalog"."default",
  "likes" int4,
  "comment_num" int4,
  "create_time" timestamp(0) NOT NULL,
  "update_time" timestamp(0) NOT NULL,
  "images" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4 NOT NULL,
  "browse" int4,
  "recommend" int4
)
;
COMMENT ON COLUMN "public"."article"."article_id" IS '博文id';
COMMENT ON COLUMN "public"."article"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."article"."tag_id" IS '标签id';
COMMENT ON COLUMN "public"."article"."title" IS '标题';
COMMENT ON COLUMN "public"."article"."abstracts" IS '摘要';
COMMENT ON COLUMN "public"."article"."pc_content" IS '内容';
COMMENT ON COLUMN "public"."article"."wx_content" IS '内容';
COMMENT ON COLUMN "public"."article"."likes" IS '点赞数';
COMMENT ON COLUMN "public"."article"."comment_num" IS '评论数';
COMMENT ON COLUMN "public"."article"."images" IS '页面大图';
COMMENT ON COLUMN "public"."article"."status" IS '博文状态  0:草稿  1:已发表  2:已删除';
COMMENT ON COLUMN "public"."article"."browse" IS '浏览量';
COMMENT ON COLUMN "public"."article"."recommend" IS '推荐 0不推荐 1推荐';

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO "public"."article" VALUES (65, 25, '3,2,4', '111', '111', 'qweqeq', NULL, 0, 0, '2020-03-26 14:14:48', '2020-03-26 14:14:48', NULL, 1, 1, 0);
INSERT INTO "public"."article" VALUES (66, 25, '3,2,4', '123', '111', '1111', NULL, 0, 0, '2020-03-26 14:15:44', '2020-03-26 14:15:44', NULL, 1, 0, 0);
INSERT INTO "public"."article" VALUES (21, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 559, 1);
INSERT INTO "public"."article" VALUES (27, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, NULL);
INSERT INTO "public"."article" VALUES (25, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 559, 1);
INSERT INTO "public"."article" VALUES (16, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (18, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, 1);
INSERT INTO "public"."article" VALUES (17, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 571, 1);
INSERT INTO "public"."article" VALUES (19, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, 1);
INSERT INTO "public"."article" VALUES (10, 25, '1,2', '你好', '你是个但事实上', '大飒飒大师的撒打算湿哒哒', NULL, 2, 0, '2019-12-01 17:58:00', '2019-12-08 17:58:00', NULL, 1, 51, 0);
INSERT INTO "public"."article" VALUES (60, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (22, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, 1);
INSERT INTO "public"."article" VALUES (61, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (26, 25, '2', '不可以1111111111111111111111111111111111111', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, NULL);
INSERT INTO "public"."article" VALUES (1, 44, '2,3', '可不可以', '预先取之，必先予之', '你瞅啥，抽你咋地', '法第三方士大夫', 2, 10, '2020-01-02 13:54:40', '2020-01-02 13:58:28', '/images/img22.jpg', 1, 690, 1);
INSERT INTO "public"."article" VALUES (62, 25, '2', '不可wqeqeqeq', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (15, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, 1);
INSERT INTO "public"."article" VALUES (14, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (63, 25, '2', '不可wqeqwe', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (64, 25, '2', '不可sdcxz以wqeqw', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (23, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 560, 1);
INSERT INTO "public"."article" VALUES (20, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 561, 1);
INSERT INTO "public"."article" VALUES (9, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '', 1, 560, 1);
INSERT INTO "public"."article" VALUES (24, 25, '2', '不可以', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 7, '2019-11-01 10:59:27', '2019-11-01 13:59:32', '/images/img', 1, 634, 1);
INSERT INTO "public"."article" VALUES (12, 25, '2,3,4', 'bios', '我的灵和魂魄不停守候', ' 在你心门口，我的伤痛眼泪化为乌有，为你而流！
 ![](/images/1584320692967.jpg)', NULL, 1, 0, '2020-03-16 09:05:10', '2020-03-16 09:05:10', '', 1, 42, 0);
INSERT INTO "public"."article" VALUES (13, 25, '2', '不可以我我我我我我我我我我我我哦我呜呜呜呜呜呜呜呜无无无我我哦我', '666666666666', '啥叫电脑撒娇的贺卡的撒广大科技的国家', '撒打算打算', 1, 3, '2019-11-01 10:59:27', '2020-03-25 13:59:32', '/images/img', 1, 573, 1);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS "public"."collect";
CREATE TABLE "public"."collect" (
  "collect_id" int4 NOT NULL DEFAULT nextval('serial02'::regclass),
  "user_id" int4 NOT NULL,
  "article_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."collect"."collect_id" IS '收藏id';
COMMENT ON COLUMN "public"."collect"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."collect"."article_id" IS '博文id';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO "public"."collect" VALUES (50, 2, 9);
INSERT INTO "public"."collect" VALUES (8, 47, 1);
INSERT INTO "public"."collect" VALUES (65, 2, 1);
INSERT INTO "public"."collect" VALUES (81, 25, 10);
INSERT INTO "public"."collect" VALUES (83, 25, 1);
INSERT INTO "public"."collect" VALUES (86, 25, 12);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS "public"."comment";
CREATE TABLE "public"."comment" (
  "comment_id" int4 NOT NULL DEFAULT nextval('serial03'::regclass),
  "user_id" int4 NOT NULL,
  "article_id" int4 NOT NULL,
  "comment_text" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "likes" int4 NOT NULL,
  "parent_id" int4,
  "create_time" timestamp(0),
  "first_level_comment_id" int4
)
;
COMMENT ON COLUMN "public"."comment"."comment_id" IS '评论id';
COMMENT ON COLUMN "public"."comment"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."comment"."article_id" IS '博文id';
COMMENT ON COLUMN "public"."comment"."comment_text" IS '评论内容';
COMMENT ON COLUMN "public"."comment"."likes" IS '点赞量';
COMMENT ON COLUMN "public"."comment"."parent_id" IS '父评论id';
COMMENT ON COLUMN "public"."comment"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."comment"."first_level_comment_id" IS '一级评论id';

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO "public"."comment" VALUES (306, 25, 1, '1111111', 0, NULL, '2020-03-26 09:58:20', NULL);
INSERT INTO "public"."comment" VALUES (307, 25, 1, '11111', 0, NULL, '2020-03-26 09:58:22', NULL);
INSERT INTO "public"."comment" VALUES (308, 25, 1, '111111111111111111111111111111111111111', 0, NULL, '2020-03-26 09:58:25', NULL);
INSERT INTO "public"."comment" VALUES (309, 25, 1, '1111111111111111111111111111111111111111111111111111111111111111', 0, NULL, '2020-03-26 10:09:03', NULL);
INSERT INTO "public"."comment" VALUES (310, 25, 12, '12313', 0, NULL, '2020-03-26 11:01:32', NULL);
INSERT INTO "public"."comment" VALUES (311, 25, 12, '213', 0, 310, '2020-03-26 11:01:35', 310);

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS "public"."comment_like";
CREATE TABLE "public"."comment_like" (
  "comment_like_id" int4 NOT NULL DEFAULT nextval('comment_like_id_seq'::regclass),
  "comment_id" int4 NOT NULL,
  "user_id" int4 NOT NULL
)
;

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS "public"."fans";
CREATE TABLE "public"."fans" (
  "fans_id" int4 NOT NULL DEFAULT nextval('serial04'::regclass),
  "user_id" int4 NOT NULL,
  "user_fans" int4 NOT NULL,
  "status" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."fans"."fans_id" IS '主键';
COMMENT ON COLUMN "public"."fans"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."fans"."user_fans" IS '粉丝id';
COMMENT ON COLUMN "public"."fans"."status" IS '是否相互关注  0 未关注   1已关注';

-- ----------------------------
-- Records of fans
-- ----------------------------
INSERT INTO "public"."fans" VALUES (21, 25, 41, 0);
INSERT INTO "public"."fans" VALUES (22, 25, 36, 0);
INSERT INTO "public"."fans" VALUES (24, 24, 36, 0);
INSERT INTO "public"."fans" VALUES (1, 25, 44, 1);
INSERT INTO "public"."fans" VALUES (2, 25, 45, 1);
INSERT INTO "public"."fans" VALUES (3, 25, 46, 1);
INSERT INTO "public"."fans" VALUES (4, 25, 47, 1);
INSERT INTO "public"."fans" VALUES (25, 25, 35, 0);
INSERT INTO "public"."fans" VALUES (91, 25, 2, 0);
INSERT INTO "public"."fans" VALUES (94, 44, 2, 0);
INSERT INTO "public"."fans" VALUES (115, 2, 25, 0);
INSERT INTO "public"."fans" VALUES (119, 44, 25, 0);

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS "public"."follow";
CREATE TABLE "public"."follow" (
  "follow_id" int4 NOT NULL DEFAULT nextval('serial05'::regclass),
  "user_id" int4 NOT NULL,
  "follow_user" int4 NOT NULL,
  "status" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."follow"."follow_id" IS '关注主键';
COMMENT ON COLUMN "public"."follow"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."follow"."follow_user" IS '关注人id';
COMMENT ON COLUMN "public"."follow"."status" IS '相互关注   0 未相互关注  1相互关注';

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO "public"."follow" VALUES (21, 24, 48, 0);
INSERT INTO "public"."follow" VALUES (87, 2, 25, 0);
INSERT INTO "public"."follow" VALUES (90, 2, 44, 0);
INSERT INTO "public"."follow" VALUES (126, 25, 2, 0);
INSERT INTO "public"."follow" VALUES (136, 25, 44, 0);

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS "public"."like";
CREATE TABLE "public"."like" (
  "user_id" int4 NOT NULL,
  "article_id" int4 NOT NULL,
  "like_id" int4 NOT NULL DEFAULT nextval('serial06'::regclass),
  "status" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."like"."like_id" IS '点赞id';
COMMENT ON COLUMN "public"."like"."status" IS '点赞状态  0:取消  1:点赞';

-- ----------------------------
-- Records of like
-- ----------------------------
INSERT INTO "public"."like" VALUES (2, 1, 93, 1);
INSERT INTO "public"."like" VALUES (11, 10, 51, 1);
INSERT INTO "public"."like" VALUES (24, 9, 18, 1);
INSERT INTO "public"."like" VALUES (25, 10, 117, 1);
INSERT INTO "public"."like" VALUES (25, 12, 119, 1);
INSERT INTO "public"."like" VALUES (25, 1, 121, 1);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS "public"."report";
CREATE TABLE "public"."report" (
  "report_id" int4 NOT NULL DEFAULT nextval('serial07'::regclass),
  "user_id" int4,
  "article_id" int4,
  "msg" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."report"."report_id" IS '举报';
COMMENT ON COLUMN "public"."report"."msg" IS '举报内容';

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO "public"."report" VALUES (2, 37, 2, '生命
');
INSERT INTO "public"."report" VALUES (3, 25, 6, '不好看');
INSERT INTO "public"."report" VALUES (1, 25, 1, '水水水水水水水水');
INSERT INTO "public"."report" VALUES (4, 25, 9, '社会黄');
INSERT INTO "public"."report" VALUES (9, 25, 1, '');
INSERT INTO "public"."report" VALUES (10, 25, 1, '写的不够seqing');
INSERT INTO "public"."report" VALUES (11, 25, 1, '涩情');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS "public"."tag";
CREATE TABLE "public"."tag" (
  "tag_id" int4 NOT NULL DEFAULT nextval('serial08'::regclass),
  "tag_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."tag"."tag_id" IS '标签id';
COMMENT ON COLUMN "public"."tag"."tag_name" IS '标签名';

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO "public"."tag" VALUES (2, 'python');
INSERT INTO "public"."tag" VALUES (3, 'c++');
INSERT INTO "public"."tag" VALUES (4, 'c');
INSERT INTO "public"."tag" VALUES (5, 'php');
INSERT INTO "public"."tag" VALUES (7, '异常');
INSERT INTO "public"."tag" VALUES (8, '框架');
INSERT INTO "public"."tag" VALUES (9, '结构');
INSERT INTO "public"."tag" VALUES (10, '旅游');
INSERT INTO "public"."tag" VALUES (11, '照片');
INSERT INTO "public"."tag" VALUES (12, '风景');
INSERT INTO "public"."tag" VALUES (13, '人文');
INSERT INTO "public"."tag" VALUES (14, '情感');
INSERT INTO "public"."tag" VALUES (15, '心理');
INSERT INTO "public"."tag" VALUES (16, '时事');
INSERT INTO "public"."tag" VALUES (17, '地理');
INSERT INTO "public"."tag" VALUES (18, '环境');
INSERT INTO "public"."tag" VALUES (19, '明星');
INSERT INTO "public"."tag" VALUES (20, '八卦');
INSERT INTO "public"."tag" VALUES (21, '奇闻');
INSERT INTO "public"."tag" VALUES (22, '一世');
INSERT INTO "public"."tag" VALUES (6, 'java');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "public"."user";
CREATE TABLE "public"."user" (
  "user_id" int4 NOT NULL DEFAULT nextval('serial09'::regclass),
  "username" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "e_mail" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "gender" int4,
  "job" varchar(64) COLLATE "pg_catalog"."default",
  "introduce" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4 NOT NULL,
  "create_time" timestamp(0) NOT NULL,
  "update_time" timestamp(0) NOT NULL,
  "tags" varchar(64) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."user"."user_id" IS '主键id';
COMMENT ON COLUMN "public"."user"."username" IS '用户名';
COMMENT ON COLUMN "public"."user"."password" IS '密码';
COMMENT ON COLUMN "public"."user"."e_mail" IS '联系电话';
COMMENT ON COLUMN "public"."user"."icon" IS '头像';
COMMENT ON COLUMN "public"."user"."gender" IS '性别  0:女  1:男';
COMMENT ON COLUMN "public"."user"."job" IS '工作简介';
COMMENT ON COLUMN "public"."user"."introduce" IS '个人简介';
COMMENT ON COLUMN "public"."user"."status" IS '禁启用  0启用  1禁用';
COMMENT ON COLUMN "public"."user"."tags" IS '用户标签 最多添加3个';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "public"."user" VALUES (35, '123123', '698d51a19d8a121ce581499d7b701668', '943247922@qq.com', '/images/cheng-5.jpg', 1, 'cccccc', 'jjhhhhhhhhhh', 0, '2019-12-31 15:04:39', '2019-12-31 15:05:13', NULL);
INSERT INTO "public"."user" VALUES (36, '111111', '96e79218965eb72c92a549dd5a330112', '9432479211@qq.com', '/images/111111-36.jpg', NULL, NULL, NULL, 0, '2020-01-02 17:31:09', '2020-01-02 17:40:55', NULL);
INSERT INTO "public"."user" VALUES (45, 'dalao2', '81dc9bdb52d04dc20036dbd8313ed055', '111@qq.com', '/images/wukong-2.jpg', NULL, 'dl', '我欲成仙', 0, '2020-01-13 11:50:04', '2020-01-13 11:50:07', NULL);
INSERT INTO "public"."user" VALUES (44, 'dalao1', '81dc9bdb52d04dc20036dbd8313ed055', '111@qq.com', '/images/bajie-1.jpg', NULL, 'dl', '法力无边', 0, '2020-01-13 11:50:04', '2020-01-13 11:50:07', NULL);
INSERT INTO "public"."user" VALUES (46, 'dalao3', '81dc9bdb52d04dc20036dbd8313ed055', '11111@qq.com', '/images/tangseng-3.jpg', 0, 'dl', '贫jia库!贫jia库!', 0, '2020-01-13 11:50:04', '2020-01-13 11:50:07', NULL);
INSERT INTO "public"."user" VALUES (47, 'dalao4', '81dc9bdb52d04dc20036dbd8313ed055', '111@qq.com', '/images/shasen-4.jpg', NULL, 'dl', '欧拉欧拉欧拉欧拉欧拉欧拉欧拉！', 0, '2020-01-13 11:50:04', '2020-01-13 11:50:07', NULL);
INSERT INTO "public"."user" VALUES (2, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', '1', '/images/wugui.jpg', NULL, NULL, NULL, 0, '2019-12-26 17:07:09', '2019-12-26 00:00:00', NULL);
INSERT INTO "public"."user" VALUES (41, '123444', 'c4ca4238a0b923820dcc509a6f75849b', '9432479222@qq.com', '/images/default.jpg', NULL, '', NULL, 0, '2020-01-09 14:53:51', '2020-01-09 14:54:11', NULL);
INSERT INTO "public"."user" VALUES (49, '11', '6512bd43d9caa6e02c990b0a82652dca', '9432110@qq.com', 'images/default.jpg', NULL, NULL, NULL, 0, '2020-01-21 14:13:37', '2020-01-21 14:13:56', NULL);
INSERT INTO "public"."user" VALUES (50, '111', '698d51a19d8a121ce581499d7b701668', '94324220@qq.com', NULL, NULL, NULL, NULL, 1, '2020-01-21 14:14:34', '2020-01-21 14:14:34', NULL);
INSERT INTO "public"."user" VALUES (51, '1111', 'b59c67bf196a4758191e42f76670ceba', '943247920@qq.com', 'images/default.jpg', NULL, NULL, NULL, 0, '2020-01-21 14:20:28', '2020-01-21 14:20:54', NULL);
INSERT INTO "public"."user" VALUES (25, '1234', '81dc9bdb52d04dc20036dbd8313ed055', '943247921@qq.com', '/images/1234-25.jpg', 1, '挨踢猛男', '活得健康', 0, '2019-12-30 15:25:21', '2020-03-26 09:35:49', NULL);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."comment_like_id_seq"', 283, true);
SELECT setval('"public"."serial01"', 67, true);
SELECT setval('"public"."serial02"', 87, true);
SELECT setval('"public"."serial03"', 312, true);
SELECT setval('"public"."serial04"', 120, true);
SELECT setval('"public"."serial05"', 137, true);
SELECT setval('"public"."serial06"', 122, true);
SELECT setval('"public"."serial07"', 12, true);
SELECT setval('"public"."serial08"', 3, false);
SELECT setval('"public"."serial09"', 57, true);

-- ----------------------------
-- Primary Key structure for table article
-- ----------------------------
ALTER TABLE "public"."article" ADD CONSTRAINT "article_pkey" PRIMARY KEY ("article_id");

-- ----------------------------
-- Primary Key structure for table collect
-- ----------------------------
ALTER TABLE "public"."collect" ADD CONSTRAINT "collect_pkey" PRIMARY KEY ("collect_id");

-- ----------------------------
-- Primary Key structure for table comment
-- ----------------------------
ALTER TABLE "public"."comment" ADD CONSTRAINT "comment_pkey" PRIMARY KEY ("comment_id");

-- ----------------------------
-- Primary Key structure for table comment_like
-- ----------------------------
ALTER TABLE "public"."comment_like" ADD CONSTRAINT "comment_like_pkey" PRIMARY KEY ("comment_like_id");

-- ----------------------------
-- Primary Key structure for table fans
-- ----------------------------
ALTER TABLE "public"."fans" ADD CONSTRAINT "fans_pkey" PRIMARY KEY ("fans_id");

-- ----------------------------
-- Primary Key structure for table follow
-- ----------------------------
ALTER TABLE "public"."follow" ADD CONSTRAINT "follow_pkey" PRIMARY KEY ("follow_id");

-- ----------------------------
-- Primary Key structure for table like
-- ----------------------------
ALTER TABLE "public"."like" ADD CONSTRAINT "like_pkey" PRIMARY KEY ("like_id");

-- ----------------------------
-- Primary Key structure for table report
-- ----------------------------
ALTER TABLE "public"."report" ADD CONSTRAINT "report_pkey" PRIMARY KEY ("report_id");

-- ----------------------------
-- Primary Key structure for table tag
-- ----------------------------
ALTER TABLE "public"."tag" ADD CONSTRAINT "tag_pkey" PRIMARY KEY ("tag_id");

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "public"."user" ADD CONSTRAINT "user_pkey" PRIMARY KEY ("user_id");
