# [合同管理系统](http://luoma.fun:10086)



## 简介：

本项目是一个采用Vue框架和Spring boot编写的合同管理系统，用户可使用本系统进行合同起草、定稿、会签、签订等操作。



## 快速开始：

```bash
git clone https://github.com/invisible121/CMS.git
```

### 服务端：

[**API文档**](https://documenter.getpostman.com/view/9959634/SWLh5SNo) (`我会逐渐完善`)

- #### 运行jar包：

```bash
wget https://github.com/invisible121/CMS/releases/download/1.0/server.jar
java -jar server.jar
```

<u>**注：我可能会删数据库。**</u>



- #### 源码编译：

**创建数据库**：

```mysql
mysql > CREATE DATABASE {your_database};
mysql > USE DATABASE {your_database};
mysql > source ${PROJECT_ROOT}/sql/CMS.sql;
```

**修改配置**：

```bash
cd ${PROJECT_ROOT}/server/src/main/resources
vim application.yml
```

**修改第6行到第8行并保存。**

```yml
url: jdbc:mysql://your_server_IP:3306/your_Database
    username: your_Database_userName
    password: your_Database_password
```

**运行**：

```bash
cd ${PROJECT_ROOT}/server
mvn spring-boot:run
```

**编译并运行：**

```bash
cd ${PROJECT_ROOT}/server
mvn package
java -jar ${PROJECT_ROOT}/server/target/server-0.0.1-SNAPSHOT.jar
```

### [客户端](http://luoma.fun:10086)：

```bash
cd client/src
vim main.js
```

**修改第16行并保存。**

```javascript
Vue.prototype.$server = "http://your_server_IP";
```

**运行：**

```bash
npm install
npm run serve
```

## [后台操作监控系统](http://luoma.fun:12306)：

**采用SkyWalking开源监控系统开发，用户可实时监控服务端信息。**