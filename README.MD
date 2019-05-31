# DreamFactory4s
Dreamfactory sdk for scala.
## Dependency
```
libraryDependencies += "io.github.tobetwo" % "dreamfactory4s" % "1.0.0"
```
## Configuration
create file `resources/dreamfactory.properties`
```
api.url=http://host:port/api/v2
api.key=DF api key
api.key.name=X-DreamFactory-API-Key
```
## Usage
```
import io.github.tobetwo.dreamfactory4s._
import io.github.tobetwo.dreamfactory4s.implicits._

DF.mysql table "t_user" getEntity "id"   //get by id
DF ~ "serviceName" ~ "resourceName" ~ parameterMap[String, String] //get/post/getJson/getString...

DF.mysql
  .table("t_user")
  .select("id", "password")
  .where($"id" < $"createTime" & $"id" <= 1)
  //.count
  //.exists
  //.list    //分页默认10条一组
  //.listAll
  //.getEntity
  
DF.mysql table "v_user_all" where($"id" === 1 | $"roleName" ~ "test") limit 10 list // "id_ = 1 or role_name_ like '%test%'"

DF.mysql table "xxx" where($"id" === "123") save(Map("pwd" -> "newPassword"))

DF.cache get "k"
DF.cache set("k", "v", ttl = 10)
DF.cache delete "k"
```