### springboot-shiro-thymeleaf
springboot shiro thymeleaf mysql, this is just a demo project

#### 如何运行？
- 1.通过脚本 `springboot-shiro.sql`导入数据库、表、数据.
- 2.在`application.properties`文件中修改数据库账号.
- 3.进入项目根目录执行maven命令`mvn package -Dmaven.test.skip=true`.
- 4.进入`target`目录执行`java -jar springboot-shiro-thymeleaf.jar`
- 5.打开浏览器访问：`http://localhost:8080/home`