# DreamFactory4s
Dreamfactory sdk for java/scala.
## Dependency
maven:
```
<dependency>
    <groupId>io.github.tobetwo</groupId>
    <artifactId>dreamfactory4s</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```
sbt:
```
libraryDependencies += "io.github.tobetwo" % "dreamfactory4s" % "1.0.0-SNAPSHOT"
```
## Configuration
create file `resources/dreamfactory.properties`
```
df.api.url=http://host:port/api/v2
df.api.key=DF api key
```
## Usage
java:
```
import io.github.tobetwo.dreamfactory4j.*;
获取一条数据
Map data = DF.mysql()
  .table("t_user")
  .select("id", "name")
  .where(Col.name("id").eq(-1))
  .where(Col.name("createTime").ge("some time")) // great equal
  .getEntity().data4j();
//data: Map(id -> xxx, name -> xxx)

获取列表
DF.mysql()
  .table("t_user")
  .where(Col.name("id").le(10).and(Col.name("createTime").ge("2018")))
  .list()
  .data4j()
//List[Map(id -> xxx, ...), Map(id -> xxx)] 

写操作
DF.mysql()
  .table("xxx")
  .where(xxx) 
  //.save(Map(xxx))
  //.delete()
```

scala:
```
import io.github.tobetwo.dreamfactory4s._
import io.github.tobetwo.dreamfactory4s.implicits._

DF.mysql table "t_user" getEntity "id"   //get by id
DF service "xxxApi" resource "xxx" query("", "") //get/post/put/patch/delete

DF.mysql 

DF.mysql
  .table("t_user")
  .select("id", "password")
  .where($"id" < $"createTime" & $"id" <= 1)
  //.count
  //.exists
  //.list    //分页默认10条一组
  //.listAll
  //.getEntity
  
  
DF.mysql table "xxx" where($"id" === "123") save(Map("pwd" -> "newPassword"))

```