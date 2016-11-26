
Spark API 系统汇总：
==

对一个数据为{1,2,3,4,}的RDD进行**基本的RDD转化**操作

| 函数名                                      | 目的                                       | 示例                            | 结果                      |
| ---------------------------------------- | ---------------------------------------- | ----------------------------- | ----------------------- |
| $$map()$$                                | 将函数运用于RDD中的每个元素，将函数的返回值构成新的RDD返回。        | $$rdd.map(x => x+1)$$         | `{2,3,4,5,}`            |
| $$flatMap()$$                            | 将函数应用于 RDD 中的每个元素，将返回的迭代器的所有内容构成新的 RDD。通常用来切分单词。比如说针对一个文本文件而言，每一行字符串就是一个RDD元素，经过处理后，一行字符串将返回多个单词。 | $$rdd.flatMap(x => x.to(3))$$ | `{1, 2, 3, 2, 3, 3, 3}` |
| $$filter()$$                             | 返回一个由通过传给 filter()的函数的元素组成的 RDD。         | $$rdd.filter(x => x != 1)$$   | `{2, 3, 3}`             |
| $$distinct()$$                           | 去重                                       | $$rdd.distinct()$$            | `{1,2,3}`               |
| $$sample(withReplacement, fraction, [seed])$$ | 对 RDD 采样，以及是否替换                          | $$rdd.sample(false, 0.5)$$    | 非确定                     |

对一个数据为{1, 2, 3, 3}的RDD进行**基本的RDD行动**操作

| 函数名                                     | 目的                                       | 示例                                       | 结果                    |
| --------------------------------------- | ---------------------------------------- | ---------------------------------------- | --------------------- |
| $$collect()$$                           | 返回 RDD 中的所有元素                            | $$rdd.collect()$$                        | `{1, 2, 3, 3}`        |
| $$count()$$                             | RDD 中的元素个数                               | $$rdd.count()$$                          | 4                     |
| $$countByValue()$$                      | 各元素在 RDD 中出现的次数                          | $$rdd.countByValue()$$                   | `{(1, 1),(2,1),(3,2)` |
| $$take(num)$$                           | 从 RDD 中返回 num 个元素                        | $$rdd.take(2)$$                          | `{1, 2}`              |
| $$top(num)$$                            | 从 RDD 中返回最前面的 num个元素                     | $$rdd.top(2)$$                           | `{3, 3}`              |
| $$takeOrdered(num)(ordering)$$          | 从 RDD 中按照提供的顺序返回最前面的 num 个元素             | $$rdd.takeOrdered(2)(myOrdering)$$       | `{3, 3}`              |
| $$reduce(func)$$                        | 并 行 整 合 RDD 中 所 有 数 据（例如 sum ）           | $$rdd.reduce((x, y) => x + y)$$          | 9                     |
| $$fold(zero)(func)$$                    | 和 reduce() 一样，但是需要提供初始值                  | $$rdd.fold(0)((x, y) => x + y)$$         | 9                     |
| $$aggregate(zeroValue)(seqOp, combOp)$$ | 和 reduce() 相似，但是通常返回不同类型的函数，zeroValue是需要提供的初始值，seqOP函数负责在每个节点上进行本地累加，combOp函数负责将多个累加器进行两两合并。 | rdd.aggregate((0, 0))((x, y) =>(x._1 + y, x._2 + 1),(x, y) =>(x._1 + y._1, x._2 + y._2)) | `(9,4)`               |
| $$foreach(func)$$                       | 对RDD中的每个元素进行一个行动操作，但是不把结果返回到驱动器程序中。比如可以使用JSON格式发送到网络中，这时候就可以使用`forecah()`行动操作，对RDD中的每个元素进行操作，而不需要把RDD发回到本地。 | $$rdd.foreach(func)$$                    | 无                     |

在不同RDD类型间转换，Scala版本是隐式转换的，下面表格中是java版本。

| 函数名                            | 等价函数                                  | 用途                                  |
| ------------------------------ | ------------------------------------- | ----------------------------------- |
| `DoubleFlatMapFunction<T>`     | `Function<T, Iterable<Double>>`       | 用于 flatMapToDouble ，以生成 DoubleRDD   |
| `DoubleFunction<T>`            | `Function<T, Double>`                 | 用于 mapToDouble ，以生成DoubleRDD        |
| `PairFlatMapFunction<T, K, V>` | `Function<T, Iterable<Tuple2<K, V>>>` | 用于 flatMapToPair ，以生成 PairRDD<K, V> |
| `PairFunction<T, K, V>`        | `Function<T, Tuple2<K, V>>`           | 用 于 mapToPair ， 以 生 成PairRDD<K, V>  |