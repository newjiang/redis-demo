## Redis的数据类型

### Redis类型

+ **string**
  + Raw -- 8个字节的长整型
  + int -- 小于等于39个字节的字符串
  + embstr -- 大于39个字节的字符串
+ **hash**
  + hashtable -- 哈希类型无法满足ziplist的条件时
  + ziplist -- 哈希类型元素个数小于hash-max-ziplist-entries配置（默认512个）\n同时所有值都小于hash-max-ziplist-value配置（默认64字节）
+ **list**
  + linkedlist
    列表类型无法满足ziplist的条件时
  + ziplist
    列表的元素个数小于list-max-ziplist-entries配置（默认512个）\n同时列表中每个元素的值都小于list-max-ziplist-value配置时（默认64字节）
+ **set**
  + hashtable -- 集合类型无法满足intset的条件时
  + intset -- 集合中的元素都是整数且元素个数小于set-max-intset-entries配置（默认512个）
+ **zset**
  + skiplist -- ziplist条件不满足时
  + ziplist -- 有序集合的元素个数小于zset-max-ziplist-entries配置（默认128个）\n同时每个元素的值都小于zset-max-ziplist-value配置（默认64字节）
    ![redisdatatype.png](./assets/redis-data-type.png)
