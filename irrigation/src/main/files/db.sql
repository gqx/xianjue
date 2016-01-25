DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
    `id`    		int   			AUTO_INCREMENT	COMMENT 'id',
    `name`	varchar(64)   	NOT NULL    	COMMENT '����',
    `password`		VARCHAR(64)		NOT NULL		COMMENT '����',
	`user_type`	varchar(20)   	NOT NULL    	COMMENT '�û����ͣ�admin,common',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '�û���';

#admin xianjue
insert into User (name, password, user_type) value ('admin','c3ad3f05b7eeae0113e72be9446cf2de','admin');

DROP TABLE IF EXISTS `gprs`;
CREATE TABLE `gprs` (
    `id`			int   			AUTO_INCREMENT	COMMENT 'id',
    `name`			varchar(100)		DEFAULT NULL	COMMENT 'name',
    `mac` 			varchar(32) 		DEFAULT NULL 	COMMENT 'mac',
    `ip` 			varchar(32) 		DEFAULT NULL 	COMMENT 'ip',
    `illumination`	int   	DEFAULT '0' 	COMMENT '����',
	`temperature` 	int		DEFAULT '0' 	COMMENT '�¶�',
	`humidity` 		int		DEFAULT '0' 	COMMENT 'ʪ��',
	`state` 		varchar(32) 		DEFAULT NULL 	COMMENT 'gprs state:init, connected',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT 'gprs��';

DROP TABLE IF EXISTS `zigbee`;
CREATE TABLE `zigbee` (
    `id`			int   			AUTO_INCREMENT	COMMENT 'id',
    `name`			varchar(100)		DEFAULT NULL	COMMENT 'name',
    `mac` 			varchar(32) 		DEFAULT NULL 	COMMENT 'mac',
    `gmac` 			varchar(32) 		DEFAULT NULL 	COMMENT 'gprs mac',
    `state` 		varchar(32) 		DEFAULT NULL 	COMMENT 'zigbee state:init, connected',
    `ztype` 		int 				DEFAULT NULL 	COMMENT 'zigbee type',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT 'Zigbee��';

DROP TABLE IF EXISTS `valve`;
CREATE TABLE `valve` (
    `id`		int   			AUTO_INCREMENT	COMMENT 'id',
    `zmac`		VARCHAR(32)		COMMENT 'zigbee mac',
    `zorder`	int				COMMENT 'zigbee�е�˳��',
    `name`		VARCHAR(100)	COMMENT '��������',
    `state`		int   	NOT NULL   DEFAULT '0' 	COMMENT '����״̬��1��0��',
    `valve_type` VARCHAR(16)	COMMENT '��������',
    `group_id`	VARCHAR(16)		COMMENT '����',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '���ű�';

DROP TABLE IF EXISTS `pump`;
CREATE TABLE `pump` (
    `id`		int   			AUTO_INCREMENT	COMMENT 'id',
    `zmac`		VARCHAR(32)		COMMENT 'zigbee mac',
    `name`		VARCHAR(100)	COMMENT '����',
    `state`		int   	NOT NULL   DEFAULT '0' 	COMMENT '����״̬��1��0��',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT 'ˮ�ñ�';

DROP TABLE IF EXISTS `pump_pressure`;
CREATE TABLE `pump_pressure` (
    `id`		int   			AUTO_INCREMENT	COMMENT 'id',
    `gmac` 		varchar(32) 	DEFAULT NULL 	COMMENT 'gprs mac',
    `pressure`	int		DEFAULT 0 	COMMENT 'ѹ��ֵ',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT 'ˮ��ѹ����';

DROP TABLE IF EXISTS `task_schedule`;
CREATE TABLE `task_schedule` (
    `id`		int   			AUTO_INCREMENT	COMMENT 'id',
    `task_type` 		varchar(32) 	DEFAULT NULL 	COMMENT '�������ͣ����ֲ�ͬ����',
    `task_status`	varchar(32)		DEFAULT NULL 	COMMENT '����״̬:��ʼ��init ����run ����finish',
    `task_desc`	  varchar(256)		DEFAULT NULL 	COMMENT '��������',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '��ʱ��������';

#############################
DROP TABLE IF EXISTS `Light`;
CREATE TABLE `Light` (
    `id`		int   	AUTO_INCREMENT	COMMENT 'id',
    `gid`		int		COMMENT 'gprs id',
    `name`		VARCHAR(255)		COMMENT '����i����',
	`gorder`	int		COMMENT '��gprs�е�˳��',
    `state`		int   	NOT NULL   DEFAULT '0' 	COMMENT '����״̬��1��0��',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '�ƹ��';

insert into light (gid,name,state,gorder) value (1,'һ�ſ���',1,0);
insert into light (gid,name,state,gorder) value (1,'���ſ���',0,1);
insert into light (gid,name,state,gorder) value (1,'���ſ���',1,2);
insert into light (gid,name,state,gorder) value (2,'һ�ſ���',1,0);
insert into light (gid,name,state,gorder) value (2,'���ſ���',0,1);
insert into light (gid,name,state,gorder) value (2,'���ſ���',1,2);


DROP TABLE IF EXISTS `Water_valve`;
CREATE TABLE `Water_valve` (
    `id`		int   	AUTO_INCREMENT	COMMENT 'id',
    `gid`		int		COMMENT 'gprs id',
    `name`		VARCHAR(255)		COMMENT '����i����',
	`gorder`	int		COMMENT '��gprs�е�˳��',
    `state`		int   	NOT NULL   DEFAULT '0' 	COMMENT '����״̬��1��0��',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT 'ˮ����';

insert into Water_valve (gid,name,state,gorder) value (1,'һ��ˮ��',1,0);
insert into Water_valve (gid,name,state,gorder) value (1,'����ˮ��',0,1);
insert into Water_valve (gid,name,state,gorder) value (1,'����ˮ��',1,2);
insert into Water_valve (gid,name,state,gorder) value (2,'һ��ˮ��',1,0);
insert into Water_valve (gid,name,state,gorder) value (2,'����ˮ��',0,1);
insert into Water_valve (gid,name,state,gorder) value (2,'����ˮ��',1,2);

DROP TABLE IF EXISTS `Electric_machine`;
CREATE TABLE `Electric_machine` (
    `id`		int   	AUTO_INCREMENT	COMMENT 'id',
    `gid`		int		COMMENT 'gprs id',
    `name`		VARCHAR(255)		COMMENT '����i����',
	`gorder`	int		COMMENT '��gprs�е�˳��',
    `state`		int   	NOT NULL   DEFAULT '0' 	COMMENT '����״̬��1��ת0��-1��ת',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '���һ�θ���ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '�����';

DROP TABLE IF EXISTS `Historydata`;
CREATE TABLE `Historydata` (
    `id`			int   			AUTO_INCREMENT	COMMENT 'id',
    `gid`			int 		COMMENT '��Ӧgprs id',
    `illumination`	int   	DEFAULT '0' 	COMMENT '����',
	`temperature` 	int		DEFAULT '0' 	COMMENT '�¶�',
	`humidity` 		int		DEFAULT '0' 	COMMENT 'ʪ��',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '����ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '��������ʷ����';

DROP TABLE IF EXISTS `Schedule`;
CREATE TABLE `Schedule` (
    `id`			int   			AUTO_INCREMENT	COMMENT 'id',
    `gid`			int 			COMMENT '��Ӧgprs id',
    `job_name`			VARCHAR(64) 	COMMENT '��������',
    `stype`			VARCHAR(32)   	DEFAULT null 	COMMENT '���ͣ�illumination��temperature��humidity',
	`start_time` 	datetime		DEFAULT null 	COMMENT '��ʼ����',
	`repeat_day` 	int		DEFAULT '0' 	COMMENT 'ÿ�������ظ�',
	`operation`		varchar(64)	COMMENT '�������',
	`update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '����ʱ��',
    PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COMMENT '��ʱ����';

insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,20,42,'2015-7-1 00:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,75,21,30,'2015-7-1 01:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,78,22,35,'2015-7-1 02:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,72,23,44,'2015-7-1 03:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,24,40,'2015-7-1 04:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,74,24,50,'2015-7-1 05:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,68,24,60,'2015-7-1 06:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,23,70,'2015-7-1 07:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,24,80,'2015-7-1 08:00:01');

insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,20,42,'2015-7-2 00:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,75,21,30,'2015-7-2 01:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,78,22,35,'2015-7-2 02:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,72,23,44,'2015-7-2 03:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,24,40,'2015-7-2 04:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,74,24,50,'2015-7-2 05:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,68,24,60,'2015-7-2 06:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,23,70,'2015-7-2 07:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,24,80,'2015-7-2 08:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,20,42,'2015-7-2 09:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,75,21,30,'2015-7-2 10:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,78,22,35,'2015-7-2 11:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,72,23,44,'2015-7-2 12:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,24,40,'2015-7-2 13:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,74,24,50,'2015-7-2 14:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,68,24,60,'2015-7-2 15:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,23,70,'2015-7-2 16:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,79,27,80,'2015-7-2 17:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,75,28,30,'2015-7-2 18:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,78,22,35,'2015-7-2 19:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,72,23,44,'2015-7-2 20:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,70,21,40,'2015-7-2 21:00:01');
insert into Historydata (gid,illumination,temperature,humidity,update_time) values(1,74,18,50,'2015-7-2 22:00:01');
