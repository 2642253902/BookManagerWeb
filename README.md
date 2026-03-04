# 图书管理系统 (BookManagerWeb) 项目介绍

## 📋 项目概述

**BookManagerWeb** 是一个基于 Java Web 的图书管理系统，用于管理图书馆的图书资源、学生信息以及借还记录。该项目采用传统的 **Servlet + MyBatis** 架构，提供完整的图书管理功能。

### 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 11 | 编程语言 |
| Jakarta Servlet | 6.0 | Web框架 |
| MyBatis | 3.5.7 | ORM框架 |
| MySQL | 8.0.27 | 数据库 |
| Thymeleaf | 3.1.1.RELEASE | 模板引擎 |
| Lombok | 1.18.22 | 代码生成 |
| Maven | - | 项目构建工具 |

---

## 🏗️ 项目架构

### 分层设计

项目采用**三层架构设计**：

```
┌─────────────────────────────────────┐
│    Servlet 层（表现层/控制层）      │
│  - LoginServlet, LogoutServlet      │
│  - BookServlet, StudentServlet      │
│  - AddBookServlet, AddBorrowServlet │
│  - DeleteBookServlet, ReturnServlet │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│    Service 层（业务逻辑层）         │
│  - BookService                      │
│  - StudentService                   │
│  - UserService                      │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│    DAO 层（数据访问层）             │
│  - BookMapper                       │
│  - StudentMapper                    │
│  - UserMapper                       │
└────────────┬────────────────────────┘
             │
┌────────────▼────────────────────────┐
│    MySQL 数据库                     │
│  - book_manage 数据库               │
└─────────────────────────────────────┘
```

---

## 📦 项目结构详解

### 核心目录树

```
BookManagerWeb/
├── src/main/java/com/book/
│   ├── dao/                    # 数据访问层（Mapper接口）
│   │   ├── BookMapper.java     # 图书数据操作
│   │   ├── StudentMapper.java  # 学生数据操作
│   │   └── UserMapper.java     # 用户数据操作
│   │
│   ├── entity/                 # 数据模型层
│   │   ├── Book.java           # 图书实体
│   │   ├── Student.java        # 学生实体
│   │   ├── User.java           # 用户实体
│   │   └── Borrow.java         # 借还记录实体
│   │
│   ├── service/                # 业务逻辑层
│   │   ├── BookService.java    # 图书服务接口
│   │   ├── StudentService.java # 学生服务接口
│   │   ├── UserService.java    # 用户服务接口
│   │   └── impl/               # 服务实现类
│   │       ├── BookServiceImpl.java
│   │       ├── StudentServiceImpl.java
│   │       └── UserServiceImpl.java
│   │
│   ├── servlet/                # 控制层
│   │   ├── auth/               # 认证相关
│   │   │   ├── LoginServlet.java   # 登录处理
│   │   │   └── LogoutServlet.java  # 登出处理
│   │   ├── manage/             # 管理相关
│   │   │   ├── AddBookServlet.java
│   │   │   ├── AddBorrowServlet.java
│   │   │   ├── DeleteBookServlet.java
│   │   │   └── ReturnServlet.java
│   │   └── pages/              # 页面相关
│   │       ├── BookServlet.java    # 图书页面
│   │       ├── StudentServlet.java # 学生页面
│   │       └── IndexServlet.java   # 主页
│   │
│   ├── filter/                 # 过滤器
│   │   └── MainFilter.java     # 主过滤器
│   │
│   └── utils/                  # 工具类
│       ├── MybatisUtil.java    # MyBatis工具类
│       └── ThymeleafUtil.java  # Thymeleaf工具类
│
├── src/main/resources/         # 资源文件
│   ├── mybatis-config.xml      # MyBatis配置
│   ├── index.html              # 主页
│   ├── login.html              # 登录页
│   ├── books.html              # 图书管理页
│   ├── students.html           # 学生管理页
│   ├── add-book.html           # 添加图书页
│   ├── add-borrow.html         # 借书页
│   └── header.html             # 页面头部
│
├── src/main/webapp/            # Web资源
│   ├── static/
│   │   ├── css/                # 样式文件
│   │   ├── js/                 # JavaScript脚本
│   │   ├── font/               # 字体资源
│   │   └── image/              # 图片资源
│   └── WEB-INF/
│       └── web.xml             # Web配置文件
│
├── db/
│   └── book_manage.sql         # 数据库初始化脚本
├── pom.xml                     # Maven项目配置
└── README.md                   # 项目说明
```

---

## 🗄️ 数据库设计

项目使用 MySQL 数据库 `book_manage`，包含 4 张核心表：

### 1. **admin 表** - 管理员表
```sql
CREATE TABLE admin (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255),           -- 账号
  nickname VARCHAR(255),           -- 昵称
  password VARCHAR(255)            -- 密码
);
-- 默认数据：用户名 admin，密码 123456
```

### 2. **book 表** - 图书表
```sql
CREATE TABLE book (
  bid INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(20),               -- 图书标题
  desc VARCHAR(255),               -- 图书描述
  price DECIMAL(10, 2)             -- 价格
);
-- 包含的示例数据：三体、活着、百年孤独等经典著作
```

### 3. **student 表** - 学生表
```sql
CREATE TABLE student (
  sid INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(10),                -- 学生姓名
  sex ENUM('男','女'),             -- 性别
  age INT                          -- 年龄
);
-- 包含8条学生记录
```

### 4. **borrow 表** - 借还记录表
```sql
CREATE TABLE borrow (
  id INT PRIMARY KEY AUTO_INCREMENT,
  sid INT,                         -- 学生ID（外键）
  bid INT,                         -- 图书ID（外键）
  time DATETIME                    -- 借出时间
);
-- 记录学生借书的信息
```

---

## 🔑 核心功能模块

### 1. 认证模块 (Authentication)
- **LoginServlet** - 处理用户登录
  - 验证用户名和密码
  - 支持记住密码功能（Cookie）
  - 登录成功后重定向到首页或指定页面

- **LogoutServlet** - 处理用户登出
  - 清除会话信息
  - 重定向到登录页面

### 2. 图书管理模块 (Book Management)
- **BookServlet** - 显示图书列表
  - 获取并展示所有图书信息
  - 支持図书详情查看

- **AddBookServlet** - 添加新图书
  - 处理新图书的添加请求
  - 验证输入数据
  - 保存到数据库

- **DeleteBookServlet** - 删除图书
  - 删除指定图书记录
  - 级联处理借还记录

### 3. 学生管理模块 (Student Management)
- **StudentServlet** - 显示学生列表
  - 获取所有学生信息
  - 显示学生基本信息

### 4. 借还管理模块 (Borrow Management)
- **AddBorrowServlet** - 记录借书
  - 学生借书登记
  - 保存借出时间

- **ReturnServlet** - 记录还书
  - 处理学生还书
  - 删除对应的借还记录

### 5. 页面导航模块
- **IndexServlet** - 显示系统首页
  - 展示系统统计信息
  - 提供导航菜单

---

## 🛠️ 关键技术实现

### MyBatis ORM框架
- **配置文件**：`src/main/resources/mybatis-config.xml`
- **连接信息**：
  ```xml
  URL: jdbc:mysql://localhost:3306/book_manage
  User: root
  Password: root
  Driver: com.mysql.cj.jdbc.Driver
  ```
- **Mapper位置**：`com.book.dao` 包下的所有接口

### Thymeleaf模板引擎
- 用于服务端渲染HTML模板
- 模板文件存放在 `src/main/resources/` 目录
- 工具类：`ThymeleafUtil` 提供模板处理功能

### Servlet过滤器
- **MainFilter** - 主过滤器
  - 可用于请求拦截、权限验证、字符编码等

### Lombok增强
- 使用 `@Data` 注解快速生成 getter/setter 方法
- 减少代码冗余

---

## 🚀 快速开始

### 前置要求
- JDK 11+
- Maven 3.6+
- MySQL 5.7+

### 1. 数据库初始化
```bash
# 使用提供的SQL脚本初始化数据库
mysql -u root -p < db/book_manage.sql
```

### 2. 修改数据库连接配置
编辑 `src/main/resources/mybatis-config.xml`，修改数据库连接信息（如果需要）：
```xml
<property name="url" value="jdbc:mysql://localhost:3306/book_manage"/>
<property name="username" value="root"/>
<property name="password" value="你的MySQL密码"/>
```

### 3. 构建项目
```bash
# 使用Maven编译项目
mvn clean compile

# 打包为WAR
mvn clean package
```

### 4. 部署运行
- 将生成的 `BookManagerWeb-1.0-SNAPSHOT.war` 放到 Tomcat 的 `webapps` 目录
- 启动 Tomcat 服务器
- 访问 `http://localhost:8080/BookManagerWeb/login`

### 5. 默认登录凭证
- 账号：`admin`
- 密码：`123456`

---

## 📝 API端点概览

| HTTP方法 | 端点 | 描述 |
|---------|------|------|
| GET | `/login` | 显示登录页面 |
| POST | `/login` | 处理登录请求 |
| GET | `/logout` | 登出用户 |
| GET | `/index` | 显示首页 |
| GET | `/books` | 显示图书列表 |
| POST | `/addBook` | 添加新图书 |
| POST | `/deleteBook` | 删除图书 |
| GET | `/students` | 显示学生列表 |
| POST | `/addBorrow` | 记录借书 |
| POST | `/return` | 记录还书 |

---

## 🎯 业务流程

### 借书流程
```
用户登录 → 查看图书列表 → 选择图书 → 选择学生 → 记录借书
↓
添加借还记录到数据库
```

### 还书流程
```
用户登录 → 查看借还记录 → 选择要还的图书 → 处理还书
↓
删除借还记录 / 更新还书时间
```

### 图书管理流程
```
用户登录 → 进入管理页面 → 添加/删除图书
↓
更新图书数据库
```

---

## 📂 关键配置文件

### pom.xml
Maven项目配置，定义依赖和构建参数：
- Java编译版本：11
- 编码方式：UTF-8
- 主要依赖：MyBatis、MySQL JDBC、Lombok、Thymeleaf、Jakarta Servlet

### web.xml
Web应用配置文件，包含Servlet映射和过滤器配置（当前为最简配置）

### mybatis-config.xml
MyBatis ORM框架配置，定义：
- 数据库连接池配置
- 事务管理器配置
- Mapper扫描路径

---

## 🔒 安全特性

- 用户认证：通过 LoginServlet 验证用户身份
- 会话管理：使用 Cookie 存储用户信息（支持自动登录）
- 过滤器保护：MainFilter 可用于权限验证

---

## 📊 前端技术

项目使用以下前端库：
- **Bootstrap** - 响应式UI框架
- **jQuery** - JavaScript库
- **DataTables** - 数据表格插件
- **ApexCharts** - 图表库
- **CKEditor** - 富文本编辑器
- **jQuery Tagging** - 标签输入

---

## 📌 项目扩展建议

1. **添加用户权限控制** - 实现不同用户角色的权限管理
2. **优化图书查询** - 添加搜索、分类、排序功能
3. **借阅期限管理** - 添加借书有效期和逾期提醒
4. **数据统计分析** - 实现图书借阅统计、热门图书排行
5. **API重构** - 将Servlet改造为RESTful API
6. **前端现代化** - 使用Vue.js或React等前端框架
7. **认证增强** - 实现JWT或OAuth 2.0认证

---

## 📞 项目信息

- **项目名称**：BookManagerWeb
- **版本**：1.0-SNAPSHOT
- **包名**：com.pan.BookManagerWeb
- **打包方式**：WAR（Web应用归档）

---
