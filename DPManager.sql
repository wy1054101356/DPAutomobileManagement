----------------------------------����ʵѵ--------------------------------------------
----------------------��Ա ������ ���� ��� ���� �ܺ�-------------------------------

---------- �û���----------------
drop table TB_USER;

create table TB_USER(
  userId     varchar2(32) ,   -- �û�ID
  userPwd    varchar2(33) ,   -- �û�����(MD5 ���� ������ admin)
  userName   varchar2(32)     -- �û�����
);


insert into TB_USER values('lishidong','21232f297a57a5a743894a0e4a801fc3','������');
insert into TB_USER values('cenrong','21232f297a57a5a743894a0e4a801fc3','���');
insert into TB_USER values('wangyang','21232f297a57a5a743894a0e4a801fc3','����');
insert into TB_USER values('zhangzechao','21232f297a57a5a743894a0e4a801fc3','����');
insert into TB_USER values('caohong','21232f297a57a5a743894a0e4a801fc3','�ܺ�');

select * from TB_USER;

-------------��Ӧ�̱�------------------------------

create table TB_SUP(
  supId      varchar2(2) primary key , -- ��Ӧ�̱�� 10-99 ����
  supName    varchar2(128)             -- ��Ӧ������
)

insert into TB_SUP values('61','������ĸ��Ӧ��');
insert into TB_SUP values('55','��Ժ������Ӧ��');
insert into TB_SUP values('66','����������Ӧ��');
insert into TB_SUP values('77','������̥��Ӧ��');
insert into TB_SUP values('88','�������Ϲ�Ӧ��');
insert into TB_SUP values('99','ʮ����˿��Ӧ��');

select * from TB_SUP;

------------------��Ʒ�ṹ��-------------------------


create table TB_ITMSTR(
  itmId      varchar2(32) ,            -- ��Ŀ���
  itmPid     varchar2(32),             -- ����Ŀ��� ����ĿΪNULL
  itmName    varchar2(128) ,           -- ��Ŀ����
  seq        number ,                  -- ˳���
  itmCount   number ,                  -- ʹ������
  supId      varchar2(2) ,             -- ��Ӧ�̱�� ���
  constraint TB_itm_id primary key (itmId,itmPid)
  -- constraint TB_sup_id foreign key (supId) references TB_SUP(supId),
);

delete tb_itmstr;
truncate table tb_itmstr;
drop table TB_ITMSTR;

insert into TB_ITMSTR values('ITMSTR0001','NULL'      ,'��������',1  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0002','ITMSTR0001','������'  ,2  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0003','ITMSTR0001','����'    ,3  ,1  ,'99');
insert into TB_ITMSTR values('ITMSTR0004','ITMSTR0001','����'    ,4  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0005','ITMSTR0001','�����豸',5  ,3  ,'66');
insert into TB_ITMSTR values('ITMSTR0006','ITMSTR0002','ˮ��'    ,6  ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0007','ITMSTR0002','ˮ��'    ,7  ,1  ,'55');
insert into TB_ITMSTR values('ITMSTR0008','ITMSTR0002','ɢ����'  ,8  ,5  ,'55');
insert into TB_ITMSTR values('ITMSTR0009','ITMSTR0002','����'    ,9  ,2  ,'88');
insert into TB_ITMSTR values('ITMSTR0010','ITMSTR0002','������'  ,10 ,2  ,'88');
insert into TB_ITMSTR values('ITMSTR0011','ITMSTR0003','�����'  ,11 ,1  ,'66');
insert into TB_ITMSTR values('ITMSTR0012','ITMSTR0003','������'  ,12 ,2  ,'55');
insert into TB_ITMSTR values('ITMSTR0013','ITMSTR0003','�����'  ,13 ,3  ,'66');
insert into TB_ITMSTR values('ITMSTR0014','ITMSTR0004','�������',14 ,1  ,'88');
insert into TB_ITMSTR values('ITMSTR0015','ITMSTR0004','����'    ,15 ,4  ,'66');
insert into TB_ITMSTR values('ITMSTR0016','ITMSTR0004','����'    ,16 ,5  ,'66');
insert into TB_ITMSTR values('ITMSTR0017','ITMSTR0015','������Ʒ',17 ,8  ,'66');
insert into TB_ITMSTR values('ITMSTR0018','ITMSTR0015','������Ʒ',18 ,9  ,'88');
insert into TB_ITMSTR values('ITMSTR0019','ITMSTR0015','����Ʒ',19 ,9  ,'77');
insert into TB_ITMSTR values('ITMSTR0020','ITMSTR0005','����'  ,20 ,1  ,'55');
insert into TB_ITMSTR values('ITMSTR0021','ITMSTR0005','�𶯻�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0022','ITMSTR0003','����'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0023','ITMSTR0003','�̼�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0031','ITMSTR0003','�̵�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0032','ITMSTR0031','����'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0033','ITMSTR0031','����֧��'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0034','ITMSTR0031','�����ְ�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0035','ITMSTR0034','�ݶ�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0041','ITMSTR0009','���'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0042','ITMSTR0009','���'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0043','ITMSTR0009','��·��'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0046','ITMSTR0043','����'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0047','ITMSTR0043','΢�͵�Դ'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0051','ITMSTR0005','������'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0052','ITMSTR0005','��ѧ����'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0053','ITMSTR0005','�������豸'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0035','ITMSTR0051','�ݶ�'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0058','ITMSTR0052','������'  ,21 ,2  ,'66');
insert into TB_ITMSTR values('ITMSTR0059','ITMSTR0052','��������'  ,21 ,2  ,'66');

select * from TB_ITMSTR where itmPid ='2500C5-Z55';
select distinct  supId from TB_ITMSTR ;



