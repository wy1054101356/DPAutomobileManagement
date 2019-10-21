----------------------------------东浦实训--------------------------------------------
----------------------成员 李世东 王洋 岑溶 张泽超 曹洪-------------------------------

---------- 用户表----------------
drop table TB_USER;

create table TB_USER(
  userId     varchar2(32) ,   -- 用户ID
  userPwd    varchar2(33) ,   -- 用户密码(MD5 加密 密码是 admin)
  userName   varchar2(32)     -- 用户姓名
);


insert into TB_USER values('lishidong','21232f297a57a5a743894a0e4a801fc3','李世东');
insert into TB_USER values('cenrong','21232f297a57a5a743894a0e4a801fc3','岑溶');
insert into TB_USER values('wangyang','21232f297a57a5a743894a0e4a801fc3','王洋');
insert into TB_USER values('zhangzechao','21232f297a57a5a743894a0e4a801fc3','张泽超');
insert into TB_USER values('caohong','21232f297a57a5a743894a0e4a801fc3','曹洪');

select * from TB_USER;

-------------供应商表------------------------------

create table TB_SUP(
  supId      varchar2(2) primary key , -- 供应商编号 10-99 主键
  supName    varchar2(128)             -- 供应商名称
)

insert into TB_SUP values('61','四七螺母供应商');
insert into TB_SUP values('55','汽院电气供应商');
insert into TB_SUP values('66','二汽部件供应商');
insert into TB_SUP values('77','东风轮胎供应商');
insert into TB_SUP values('88','湖北塑料供应商');
insert into TB_SUP values('99','十堰螺丝供应商');

select * from TB_SUP;

------------------产品结构表-------------------------


create table TB_ITMSTR(
  itmId      varchar2(32) ,            -- 项目编号
  itmPid     varchar2(32),             -- 父项目编号 根项目为NULL
  itmName    varchar2(128) ,           -- 项目名称
  seq        number ,                  -- 顺序号
  itmCount   number ,                  -- 使用数量
  supId      varchar2(2) ,             -- 供应商编号 外键
  constraint TB_itm_id primary key (itmId,itmPid)
  -- constraint TB_sup_id foreign key (supId) references TB_SUP(supId),
);

delete tb_itmstr;
truncate table tb_itmstr;
drop table TB_ITMSTR;

insert into TB_ITMSTR values('ITMSTR0001','NULL'      ,'整车整车',1  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0002','ITMSTR0001','发动机'  ,2  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0003','ITMSTR0001','底盘'    ,3  ,1  ,'99');
insert into TB_ITMSTR values('ITMSTR0004','ITMSTR0001','车身'    ,4  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0005','ITMSTR0001','电气设备',5  ,3  ,'66');
insert into TB_ITMSTR values('ITMSTR0006','ITMSTR0002','水箱'    ,6  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0007','ITMSTR0002','水泵'    ,7  ,1  ,'55');
insert into TB_ITMSTR values('ITMSTR0008','ITMSTR0002','散热器'  ,8  ,5  ,'55');
insert into TB_ITMSTR values('ITMSTR0009','ITMSTR0002','风扇'    ,9  ,2  ,'88');
insert into TB_ITMSTR values('ITMSTR0010','ITMSTR0002','节温器'  ,10 ,2  ,'88');
insert into TB_ITMSTR values('ITMSTR0011','ITMSTR0003','离合器'  ,11 ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0012','ITMSTR0003','变速器'  ,12 ,2  ,'55');
insert into TB_ITMSTR values('ITMSTR0013','ITMSTR0003','万向节'  ,13 ,3  ,'66');
insert into TB_ITMSTR values('ITMSTR0014','ITMSTR0004','车身壳体',14 ,1  ,'88');
insert into TB_ITMSTR values('ITMSTR0015','ITMSTR0004','车门'    ,15 ,4  ,'66');
insert into TB_ITMSTR values('ITMSTR0016','ITMSTR0004','车窗'    ,16 ,5  ,'66');
insert into TB_ITMSTR values('ITMSTR0017','ITMSTR0015','玻璃制品',17 ,8  ,'66');
insert into TB_ITMSTR values('ITMSTR0018','ITMSTR0015','塑料制品',18 ,9  ,'88');
insert into TB_ITMSTR values('ITMSTR0019','ITMSTR0015','橡胶制品',19 ,9  ,'77');
insert into TB_ITMSTR values('ITMSTR0020','ITMSTR0005','蓄电池'  ,20 ,1  ,'55');
insert into TB_ITMSTR values('ITMSTR0021','ITMSTR0005','起动机'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0022','ITMSTR0003','盘身'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0023','ITMSTR0003','盘架'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0031','ITMSTR0003','盘底'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0032','ITMSTR0031','底座'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0033','ITMSTR0031','底座支架'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0034','ITMSTR0031','底座钢板'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0035','ITMSTR0034','螺钉'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0041','ITMSTR0009','外框'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0042','ITMSTR0009','风骨'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0043','ITMSTR0009','电路板'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0046','ITMSTR0043','电阻'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0047','ITMSTR0043','微型电源'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0051','ITMSTR0005','电池外壳'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0052','ITMSTR0005','化学物质'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0053','ITMSTR0005','抗氧化设备'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0035','ITMSTR0051','螺钉'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0058','ITMSTR0052','氢硫酸'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0059','ITMSTR0052','二氧化锡'  ,21 ,2  ,'66');

select * from TB_ITMSTR where itmPid ='2500C5-Z55';
select distinct  supId from TB_ITMSTR ;



