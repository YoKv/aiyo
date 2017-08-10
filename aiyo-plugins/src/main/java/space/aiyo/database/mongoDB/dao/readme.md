## 说明文档

> 持久层封装 


    下面是支持的查询类型，每三条数据分别对应：（方法后缀，方法例子，mongodb原生查询语句）
    GreaterThan(大于)
    findByAgeGreaterThan(int age)
    {"age" : {"$gt" : age}}

    LessThan（小于）
    findByAgeLessThan(int age)
    {"age" : {"$lt" : age}}

    Between（在...之间）
    findByAgeBetween(int from, int to)
    {"age" : {"$gt" : from, "$lt" : to}}

    IsNotNull, NotNull（是否非空）
    findByFirstnameNotNull()
    {"age" : {"$ne" : null}}

    IsNull, Null（是否为空）
    findByFirstnameNull()
    {"age" : null}

    Like（模糊查询）
    findByFirstnameLike(String name)
    {"age" : age} ( age as regex)

            (No keyword) findByFirstname(String name)
    {"age" : name}

    Not（不包含）
    findByFirstnameNot(String name)
    {"age" : {"$ne" : name}}

    Near（查询地理位置相近的）
    findByLocationNear(Point point)
    {"location" : {"$near" : [x,y]}}

    Within（在地理位置范围内的）
    findByLocationWithin(Circle circle)
    {"location" : {"$within" : {"$center" : [ [x, y], distance]}}}

    Within（在地理位置范围内的）
    findByLocationWithin(Box box)
    {"location" : {"$within" : {"$box" : [ [x1, y1], x2, y2]}}}
    尽管以上查询功能已经很丰富，但如果还不能满足使用情况的话可以用一下方法---基于mongodb原本查询语句的查询方式。
    例：在原接口中加入
    @Query("{ 'name':{'$regex':?2,'$options':'i'}, sales': {'$gte':?1,'$lte':?2}}")
    public Page<Product> findByNameAndAgeRange(String name,double ageFrom,double ageTo,Pageable page);
    注释Query里面的就是mongodb原来的查询语法，我们可以定义传进来的查询参数，通过坐标定义方法的参数。

    还可以在后面指定要返回的数据字段，如上面的例子修改如下，则只通过person表里面的name和age字段构建person对象。

    @Query(value="{ 'name':{'$regex':?2,'$options':'i'}, sales':{'$gte':?1,'$lte':?2}}",fields="{ 'name' : 1, 'age' : 1}")
    public Page<Product> findByNameAndAgeRange(String name,double ageFrom,double ageTo,Pageable page);