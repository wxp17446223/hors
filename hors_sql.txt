create table account
(
    account_id int auto_increment comment '管理员id'
        primary key,
    account    varchar(32)   null comment '登录账号',
    password   varchar(32)   null comment '登录密码',
    role_id    int default 8 null
)
    comment '管理员表';

create table `account-role-mapping`
(
    account_id int not null comment '管理员id'
        primary key,
    role_id    int not null comment '身份ID'
)
    comment '管理员-身份联系表';

create table departments
(
    depart_id   int auto_increment
        primary key,
    depart_name varchar(125)   null comment '科室名字',
    introduce   mediumtext     null comment '科室介绍',
    date        date           null comment '成立日期',
    d_id        int default -1 null comment '父科室ID默认-1 为最高级科室',
    phone       varchar(15)    null comment '科室电话'
)
    comment '科室表';

create table doctor
(
    doctor_id       int auto_increment comment '医生ID'
        primary key,
    job_number      int           null comment '医生工号',
    depart_id       int           null comment '所在科室ID',
    name            varchar(125)  null comment '医生姓名',
    sex             int default 0 null comment '性别',
    picture         varchar(125)  null comment '医生照片',
    birth           date          null comment '出生日期',
    position        varchar(12)   null comment '医生职位',
    work_experience varchar(568)  null comment '工作经验',
    phone           varchar(32)   null comment '联系方式',
    adept           varchar(568)  null comment '擅长',
    introduce       varchar(1024) null comment '医生介绍',
    constraint doctor_job_number_uindex
        unique (job_number)
)
    comment '医生表';

create table feedback
(
    back_id    int auto_increment comment '反馈ID'
        primary key,
    user_id    int          null comment '患者ID',
    doctor_id  int          null comment '医生ID',
    content    varchar(255) null comment '反馈内容',
    back_time  date         null comment '反馈时间',
    reply      varchar(255) null comment '回复内容',
    reply_time date         null comment '回复时间'
);

create table news
(
    new_id    int auto_increment comment '新闻id'
        primary key,
    title     varchar(56)   null comment '新闻标题',
    content   mediumtext    null comment '新闻内容',
    news_date date          null comment '新闻日期',
    source    varchar(32)   null comment '新闻来源',
    count     int default 0 null comment '浏览量'
)
    comment '新闻信息表';

create table `order`
(
    order_id   int auto_increment comment '预约id'
        primary key,
    user_id    int           null comment '预约用户id',
    t_id       int           null comment '预约了的时段id',
    addtime    date          null comment '下单时间',
    user_phone varchar(32)   null comment '联系电话',
    status     int default 0 null comment '处理状态：0处理中 1预约成功',
    reply      varchar(32)   null comment '医生回复',
    cost       varchar(32)   null comment '预约费用',
    doctor_id  int           null comment '预约的医生ID'
)
    comment '预约表:记录用户预约的情况';

create table p_account
(
    id       int auto_increment comment '管理员id'
        primary key,
    account  varchar(25)  null comment '账号',
    password char(32)     null comment '密码',
    head     varchar(128) null comment '头像url',
    constraint p_account_account_uindex
        unique (account)
)
    comment '管理员表';

create table p_account_role
(
    account_id int null comment '管理员id',
    role_id    int null comment '角色id'
)
    comment '账号角色映射表';

create table p_resource
(
    id        int auto_increment comment '资源id'
        primary key,
    name      varchar(25)    null comment '资源名称',
    url       varchar(1024)  null comment '资源路径',
    pid       int default -1 not null comment '父资源id',
    authority varchar(256)   not null comment '资源权限',
    type      int default 0  null comment '1:菜单 0:功能',
    code      varchar(10)    null comment '资源选项'
)
    comment '资源表';

create table p_role
(
    id   int auto_increment comment '角色标识符'
        primary key,
    name varchar(128) null comment '角色名称',
    code varchar(512) null comment '角色编码'
)
    comment '角色信息表';

create table p_role_resource
(
    role_id     int null comment '角色标识符',
    resource_id int null comment '资源标识符'
)
    comment '角色资源映射表';

create table resource
(
    resource_id int auto_increment
        primary key,
    name        varchar(32)   null comment '资源名称',
    url         varchar(32)   null comment '资源路径',
    a_id        int           null comment '父资源id，如果id资源为-1，则为顶级节点(没有父节点)',
    authority   varchar(10)   null comment 'c d u r增删改查权限',
    type        int default 1 null comment '0功能 1菜单'
)
    comment '资源表';

create table role
(
    role_id   int auto_increment
        primary key,
    role_name varchar(32) null comment '身份名称',
    number    varchar(32) null comment '身份编号'
)
    comment '身份表';

create table `role-resource-mapping`
(
    role_id     int null comment '身份id',
    resource_id int null comment '资源id'
)
    comment '身份-资源联系表';

create table timeline
(
    t_id       int auto_increment comment '排班ID'
        primary key,
    doctor_id  int           null comment '医生ID',
    date       date          null comment '日期',
    quota      int           null comment '允许预约的人数',
    start_time varchar(12)   not null comment '时段开始',
    end_time   varchar(12)   null comment '时段结束',
    status     int default 1 not null comment '该时段是否允许预约
0 否 1允许'
)
    comment '医生排班表';

create table userinfo
(
    user_id       int auto_increment comment '用户id'
        primary key,
    account_id    int          null comment '关联账号ID',
    name          varchar(32)  null comment '患者姓名',
    identity_id   varchar(18)  null comment '身份证号码',
    sex           int          null comment '性别',
    birth         date         null comment '出生日期',
    picture       varchar(256) null comment '用户照片',
    phone         varchar(15)  null comment '联系电话',
    email         varchar(32)  null comment '邮箱',
    address       varchar(255) null comment '家庭住址',
    register_time date         null comment '注册时间'
)
    comment '用户表';

