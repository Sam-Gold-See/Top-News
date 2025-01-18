# Top-News

## 开发过程

## 前端

基于尚硅谷微头条项目提供的前端代码软件包

### 后端

基于 Tomcat 11.0.2 充当 Web 服务器，使用 MVC 架构模式，即后端文件层层调用

前端 => Controller => Service => DAO => Database

Controller 负责接收前端请求，并调用 Service 层的业务逻辑，Service 层负责处理业务逻辑，并调用 DAO 层的数据库操作，DAO 层负责与数据库进行交互，并返回结果给 Controller 层。

#### Controller 层

`controller/BaseController` 包用于封装通用 **service** 方法，根据 URI 请求路径，调用同名相应的 Servlet 相关方法

`controller/NewsHeadLineController`, `controller/NewsTypeController`, `controller/NewsUserController`, 和 `controller/PortalController` 包分别用于处理新闻头条、新闻类型、新闻用户和门户相关的数据请求，接收前端请求，调用 Service 层的业务逻辑，并返回结果给前端。

#### Service 层

`service/NewsHeadLineService`, `service/NewsTypeService`, 和 `service/NewsUserService` 包是用于定义业务逻辑方法的接口，`service/impl/NewsHeadLineServiceImpl`, `service/impl/NewsTypeServiceImpl`, 和 `service/impl/NewsUserServiceImpl` 包是用于实现业务逻辑的具体实现类。用于根据请求类型，调用 DAO 层的相应方法，再根据业务逻辑处理结果

#### DAO 层

`dao/BaseDao` 包用于封装通用 **CRUD** 方法，根据实体类名，调用同名相应的 SQL 语句，并返回结果

`dao/NewsHeadlineDao`, `dao/NewsTypeDao`, 和 `dao/NewsUserDao` 包是用于定义数据库操作的接口，`dao/impl/NewsHeadLineDaoImpl`, `dao/impl/NewsTypeDaoImpl`, 和 `dao/impl/NewsUserDaoImpl` 包是用于实现数据库操作的具体实现类。用于根据实体类名，调用相应的 SQL 语句，并返回结果

#### 其他模块

- `pojo/NewsHeadLine`, `pojo/NewsType`, 和 `pojo/NewsUser` 包用于封装数据库表的实体类

- `common/Result` 包用于封装全局统一的 JSON 格式处理器，使用枚举类型

- `utils/JDBCUtil` 包用于封装 MySQL 数据库的连接池，提供获得连接和归还连接的方法

- `utils/MD5Util` 包用于封装 MD5 加密算法，对于密码转换为 MD5 值

- `utils/WebUtil` 包用于封装 JSON 串转换到 Object 对象和将 Result 对象转换为 JSON 串并放入响应对象的方法

- `filter/LoginFilter` 包用于实现登录拦截器，判断用户是否登录，若未登录则重定向到登录页面（实际上具体实现在前端代码，后端主要是由`filter/CrossFilter` 包实现跨域请求的处理
