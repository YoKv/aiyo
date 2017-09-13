#语法
net://www.runoob.com/mongodb/mongodb-tutorial.html

##语句

|操作	|格式	|范例	|RDBMS中的类似语句|
| -------- | ------- | -------- | ------- |
|等于	|{<key>:<value>}	|db.col.find({"by":"菜鸟教程"}).pretty()	|where by = '菜鸟教程'|
|小于	|{<key>:{$lt:<value>}}|	db.col.find({"likes":{$lt:50}}).pretty()|	where likes < 50|
|小于或等于|	{<key>:{$lte:<value>}}	|db.col.find({"likes":{$lte:50}}).pretty()|	where likes <= 50|
|大于|	{<key>:{$gt:<value>}}	|db.col.find({"likes":{$gt:50}}).pretty()	|where likes > 50|
|大于或等于|	{<key>:{$gte:<value>}}	|db.col.find({"likes":{$gte:50}}).pretty()	|where likes >= 50|
|不等于|	{<key>:{$ne:<value>}}|	db.col.find({"likes":{$ne:50}}).pretty()	|where likes != 50|


$gt -------- greater than

$gte --------- gt equal

$lt -------- less than

$lte --------- lt equal

$ne ----------- not equal

### and
>   db.col.find({"by":"菜鸟教程", "title":"MongoDB 教程"}).pretty()
### or
>   db.col.find({$or:[{"by":"菜鸟教程"},{"title": "MongoDB 教程"}]}).pretty()

### $type
>   db.col.find({"title" : {$type : 2}})
对应String

|类型|	数字	|备注|
| -------- | ------- | -------- |
|Double	|1	 ||
|String	|2	 ||
|Object	|3	 ||
|Array	|4	 ||
|Binary data	|5	 ||
|Undefined	|6	|已废弃。|
|Object id	|7	 ||
|Boolean|	8	 ||
|Date	|9	 ||
|Null	|10	 ||
|Regular Expression	|11	 ||
|JavaScript	|13	 ||
|Symbol|	14	 ||
|JavaScript (with scope)|	15	 ||
|32-bit integer|	16	 ||
|Timestamp|	17	 ||
|64-bit integer|	18	 ||
|Min key|	255	|Query with -1.|
|Max key|	127	 ||


### limit
>   db.COLLECTION_NAME.find().limit(NUMBER)

### skip 跳过
>   db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)

### sort     1 为升序排列，而-1是用于降序排列
>   db.COLLECTION_NAME.find().sort({KEY:1})

### 索引
>   db.COLLECTION_NAME.ensureIndex({KEY:1})
语法中 Key 值为你要创建的索引字段，1为指定按升序创建索引，如果你想按降序来创建索引指定为-1即可。

>   db.col.ensureIndex({"title":1,"description":-1})

####    参数
|Parameter	|Type	|Description|
| -------- | ------- | -------- |
|background	|Boolean	|建索引过程会阻塞其它数据库操作，background可指定以后台方式创建索引，即增加 "background" 可选参数。 "background" 默认值为false。|
|unique|	Boolean|	建立的索引是否唯一。指定为true创建唯一索引。默认值为false.|
|name	|string	|索引的名称。如果未指定，MongoDB的通过连接索引的字段名和排序顺序生成一个索引名称。|
|dropDups|	Boolean|	在建立唯一索引时是否删除重复记录,指定 true 创建唯一索引。默认值为 false.|
|sparse|	Boolean|	对文档中不存在的字段数据不启用索引；这个参数需要特别注意，如果设置为true的话，在索引字段中不会查询出不包含对应字段的文档.。默认值为 false.|
|expireAfterSeconds|	integer	|指定一个以秒为单位的数值，完成 TTL设定，设定集合的生存时间。|
|v	|index version	|索引的版本号。默认的索引版本取决于mongod创建索引时运行的版本。|
|weights	|document	|索引权重值，数值在 1 到 99,999 之间，表示该索引相对于其他索引字段的得分权重。|
|default_language	|string	|对于文本索引，该参数决定了停用词及词干和词器的规则的列表。 默认为英语|
|language_override|	string	|对于文本索引，该参数指定了包含在文档中的字段名，语言覆盖默认的language，默认值为 language.|

####    在后台创建索引：
>   db.values.ensureIndex({open: 1, close: 1}, {background: true})


###  聚合

|表达式	|描述|	实例|
| -------- | ------- | -------- |
|$sum	|计算总和。	|db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$sum : "$likes"}}}])|
|$avg	|计算平均值	|db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$avg : "$likes"}}}])|
|$min	|获取集合中所有文档对应值得最小值。	|db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$min : "$likes"}}}])|
|$max	|获取集合中所有文档对应值得最大值。	|db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$max : "$likes"}}}])|
|$push	|在结果文档中插入值到一个数组中。	|db.mycol.aggregate([{$group : {_id : "$by_user", url : {$push: "$url"}}}])|
|$addToSet	|在结果文档中插入值到一个数组中，但不创建副本。	|db.mycol.aggregate([{$group : {_id : "$by_user", url : {$addToSet : "$url"}}}])|
|$first|	根据资源文档的排序获取第一个文档数据。|	db.mycol.aggregate([{$group : {_id : "$by_user", first_url : {$first : "$url"}}}])|
|$last	|根据资源文档的排序获取最后一个文档数据	|db.mycol.aggregate([{$group : {_id : "$by_user", last_url : {$last : "$url"}}}])|


###管道的概念
>   管道在Unix和Linux中一般用于将当前命令的输出结果作为下一个命令的参数。
MongoDB的聚合管道将MongoDB文档在一个管道处理完毕后将结果传递给下一个管道处理。管道操作是可以重复的。
表达式：处理输入文档并输出。表达式是无状态的，只能用于计算当前聚合管道的文档，不能处理其它的文档。
这里我们介绍一下聚合框架中常用的几个操作：

-   $project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。
-   $match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
-   $limit：用来限制MongoDB聚合管道返回的文档数。
-   $skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。
-   $unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
-   $group：将集合中的文档分组，可用于统计结果。
-   $sort：将输入文档排序后输出。
-   $geoNear：输出接近某一地理位置的有序文档。


####管道操作符实例

>   db.article.aggregate(
    { $project : {
        title : 1 ,
        author : 1 ,
    }}
 );

这样的话结果中就只还有_id,tilte和author三个字段了，默认情况下_id字段是被包含的，如果要想不包含_id话可以这样:

>   db.article.aggregate(
    { $project : {
        _id : 0 ,
        title : 1 ,
        author : 1
    }});

>   db.articles.aggregate( [
                        { $match : { score : { $gt : 70, $lte : 90 } } },
                        { $group: { _id: null, count: { $sum: 1 } } }
                       ] );

$match用于获取分数大于70小于或等于90记录，然后将符合条件的记录送到下一阶段$group管道操作符进行处理。





