# bookshop
atguigu-bookshop
先引入myssm

补充，后面要用到json数据，又改了dispatcherServlet，又把myssm变回代码级别了，不过打包好的myssm是完整的能处理json数据的。项目用的是压缩前的myssm。

# 1、初步设计

1. 需求分析
2. 数据库设计
   - 实体分析
     - 图书
     - 用户
     - 订单
     - 购物车项
     - 订单详情
3. 实体属性分析
   - 图书：书名、作者、价格、销量、库存、封面、状态
   - 用户：用户名、密码、邮箱
   - 订单：订单编号、订单日期、订单金额、订单数量、订单状态、用户
   - 订单项：图书、数量、所属订单
   - 购物车项：图书，数量，所属用户
4. 实体间关系：
   - 用户：购物车项 1：n     购物车里面可以有n样东西
   - 订单：订单项 1：n     一张订单买了几样东西
   - 用户：订单 1：n
   - 图书：订单项 1：1
   - 图书：购物车项 1：1



# 2、基础组件定义

1. 编写配置文件（ioc，web，jdbc）
2. 数据实体，根据实体间的关系还需要设置额外属性（如一对多的时候就要设一个list存储供前端调用）
3. dao层接口，实现类
4. service层接口，实现类
5. Controller



# 3、功能实现

1. **登录，进入首页展示图书列表，若没有登录则展示不同的页面。**

- 再次体验了一遍高内聚低耦合的意思，session里面放bookList，这一步不应该在UserController里面完成，而应将请求转发至BookController完成。

- 登录时同时生成当前用户的购物车对象



2. **添加图书到购物车：将指定图书添加到当前用户的购物车中，若已存在该图书，则将其加一，否则在购物车中新增一个购物车项。**设涉及新增和修改，完成后跳转到购物车详情页

   （1）点击具体图书的加入购物车按钮

   （2）有一个购物车类统计购物车项，购物项总数，以及总金额

   （3）CartController的addCart方法，触发service层的addOrUpdate方法，该方法接收一个CartItem对象和一个购物车对象作为参数，通过判断购物车对象中是否存在要加入的图书执行插入或更新操作

   （4）Cart对象没有与数据表对应，应在用户登陆时生成。

   （5）执行完插入或更新操作后，跳转到购物车详情页，注意用myssm框架返回的book对象中只有一个id值，其它属性值要手动完善。



3. **结账功能**

   （1）向订单表添加一条记录，表明谁在什么时间花了多少钱(currUser->cart)

   （2）向订单项添加n条记录，表明一个订单表的细节：买了什么书，花了多少钱，是那张订单里面的（currUser->cart->cartitem->orderitem),add orderitem

   （3）删除购物项表中对应订单项的记录(delete cartitem)

   （4）更新书本的库存和销量(未完成)



4. **我的订单-计算订单数量**

根据当前用户id返回订单列表并跳转页面，期间还要借助orderItem计算书本总量。



5. **用户编辑购物车**

点击加号减号，根据cartItem的id和当前的buyCount更新购物车项

期间会出现计算金额时double的精度问题，在SE阶段已经了解过了，需要用bigdecimal来处理。



6. **合法用户过滤器**

未登录的情况下，除了index、login、register以为都不能放行。

​	（1）通过session中是否有currUser来判断

​	（2）在webfilter的初始化参数中设置白名单，让非法请求的重定向通过



7. **注册页面-验证码**

- 引入kaptcha的依赖，注册kaptcha的servlet，并设置验证码图片的参数，配置验证码图片的url

- htlm中请求的img应与配置的url-pattern一致。

- 生成验证码时，验证码信息同时应保存进入Session中，将用户输入的值和session中保存的值进行校验

- 填写表单，向用户表中插入用户数据跳转到登录页面

遇到的问题：kaptcha不支持jakarta，复制kaptchaServlet的代码，将所有javax改成jakarta



8、**注册页面-判断合法用户信息以及判断用户是否存在**

- 通过ajax异步请求在输入完用户名时判断是否已存在用户
- 通过在提交时触发onclick事件判断用户信息是否合法（这里onchange比较合理）



9、**使用vue和axios优化项目**

修改购物车页面：cart.html，包括页面展示的数据，以及购物车商品数量加减的请求数据。

1. 改造dispatcherServlet让其能响应json数据。
2. 编写对应的Vue组件，通过axios发送异步请求获取页面渲染数据以及发送数据修改请求
3. 修改cart.html中数据渲染部分中使用thymeleaf的部分为使用vue绑定的数据进行渲染



前后端不分离的部分：请求-->dispatcherServlet-->Controller-->Model-->dispatcherServlet、thymeleaf使用session中的数据渲染页面

前后端分离的部分：vue+axios发起请求-->dispatcherServlet-->Controller-->Model-->dispatcherServlet返回json数据-->vue解析响应数据并渲染页面。

vue+axios，不再需要thymeleaf渲染页面，可以直接请求html页面，由vue+axios来完成页面渲染。



遇到的问题：使用FastJson时，在使用`toJSONString`方法时，它默认不为数值类型的属性添加双引号，而在axios解析响应的json字符串并将其解析成对象是，需要每个属性值上都要有双引号，否则会解析失败，正确做法，在使用`toJSONString`方法时，为其加上响应相应的参数：

```java
 String cartJson = JSON.toJSONString(cart, SerializerFeature.WriteNonStringKeyAsString);
```

还有一点需要提的是，FastJson的序列化规则如下：

1. 首先，FastJson 将检查对象是否有对应的 getter 方法。如果存在 getter 方法，则通过调用 getter 方法获取属性值。
2. 如果属性值为空（null），FastJson 将尝试调用相应的 setter 方法，并设置一个空值（null）作为属性值。
3. 如果属性值非空，则将其序列化为 JSON 字符串中的属性值。

所以如果需要某些属性有值的话，要确保能满足上面的规则。
