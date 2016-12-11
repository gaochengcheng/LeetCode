# Spark Programming Guide

[TOC]

## Overview

每个应用程序由运行主函数的驱动程序和在集群上执行的各种并行操作组成。Spark提供的一个重要抽象概念是RDD（弹性分布式数据集）。它是跨集群节点分区的可以并行操作的元素的集合。

创建RDD的三种方式；

1. 通过保存在HDFS上面的文件组成。
2. 一个Scala集合在驱动程序中，通过转换它的方式。

Spark提供的第二个抽象是可以用在并行操作中的共享变量。当Spar在不同节点上并行地运行一系列函数的时候，将会函数中用到的变量复制一份到每个task中。变量可以在不同task之间传递，或者在task和driver program之间传递。

Spark支持两种共享变量：1.广播变量。2.累积变量。

### 1.2.6集群管理器

spark可以在各种集群管理器上运行，包括Hadoop YARN，Apache Mesos，以及一个Spark自带的一个简易调度器，叫做独立调度器。

## 1.6Spark的存储层次

Spark 不仅可以将任何 Hadoop 分布式文件系统（HDFS）上的文件读取为分布式数据集，也可以支持其他支持 Hadoop 接口的系统，比如本地文件、亚马逊 S3、Cassandra、Hive、HBase 等。我们需要弄清楚的是，Hadoop 并非 Spark 的必要条件，Spark 支持任何实现了 Hadoop 接口的存储系统。Spark 支持的 Hadoop 输入格式包括文本文件、SequenceFile、Avro、Parquet 等。


第二章
===

​	Spark 本身是用 Scala 写的，运行在 Java 虚拟机（JVM）上。要在你的电脑或集群上运行Spark，你要做的准备工作只是安装 Java 6 或者更新的版本。如果你希望使用 Python 接口，你还需要一个 Python 解释器（2.6 以上版本）。Spark 尚不支持 Python 3 2 。

## 2.1下载Spark

spark目录介绍

core、streaming、python目录：包含Spark项目主要组件的源代码。

## 2.2Spark中python和Scala的shell

​	和其他 shell 工具不一样的是，在其他 shell 工具中你只能使用单机的硬盘和内存来操作数据，而 Spark shell 可用来与分布式存储在许多机器的内存或者硬盘上的数据进行交互，并且处理过程的分发由 Spark 自动控制完成。

​	Spark 提供 Python 以及Scala 的增强版 shell，支持与集群的连接。

​	退出任意一个shell的方法是按：`ctrl`+`D`.

## 2.3Spark核心概念简介

​	从上层来看，每个 Spark 应用都由一个驱动器程序（driver program）来发起集群上的各种并行操作。驱动器程序包含应用的 main 函数，并且定义了集群上的分布式数据集，还对这些分布式数据集应用了相关操作。在前面的例子里，实际的驱动器程序就是 Spark shell 本身，你只需要输入想要运行的操作就可以了。

​	其实 Spark API 最神奇的地方就在于像 filter 这样基于函数的操作也会在集群上并行执行。也就是说，Spark 会自动将
函数（比如 line.contains("Python") ）发到各个执行器节点上。这样，你就可以在单一的驱动器程序中编程，并且让代码自动运行在多个节点上。

​	向Spark中传递函数是可以的，但是在这种情况下，我们必须把函数定义为实现了 Function 接口的类。例如：

```java
JavaRDD<String> pythonLines = lines.filter(
	new Function<String, Boolean>() {
	Boolean call(String line) { return line.contains("Python"); }
	}
);
```



## 2.4独立应用

​	除了交互式运行之外，Spark 也可以在 Java、Scala 或 Python 的独立程序中被连接使用。这与在 shell 中使用的主要区别在于你需要自行初始化 SparkContext。接下来，使用的 API 就一样了。

- 将Spark和自己的独立应用进行连接
- 初始化SparkContext
- ​

### 2.4.1初始化SparkContext

​	一旦完成了应用与 Spark 的连接，接下来就需要在你的程序中导入 Spark 包并且创建SparkContext。你可以通过先创建一个 SparkConf 对象来配置你的应用，然后基于这个SparkConf 创建一个 SparkContext 对象。


第三章  RDD编程
===



​	RDD 其实就是分布式的元素集合。在 Spark 中，对数据的所有操作不外乎创建 RDD、转化已有 RDD 以及调用 RDD 操作进行求值。而在这一切背后，Spark 会自动将RDD 中的数据分发到集群上，并将操作并行化执行。

​	什么叫分布式的元素集合呢？一个RDD被分为多个区，这些个区放在集群中不同的节点上。就是一个RDD是一个元素集合，但它本身是分布式存储的。

用户通过两种方式创建RDD：1.外部读取数据集。2在驱动器程序里分发驱动器程序中的对象集合（比如list和set）。

创建出RDD之后，有两种操作。1.transformation操作。2.Action操作。transformation操作是将一个RDD转换为另外一个RDD，Action操作会针对RDD计算出一个结果，并把结果返回到驱动器程序中，或把结果存储到外部存储系统（如 HDFS）中。

## 向Spark传递函数

​	在Spark中传递函数，是通过传递一个函数的对象来实现的。这个对象是被实现的接口的对象。

​	 ![标准java函数接口](pics_Spark\/标准java函数接口.PNG)

​	Function<T,R>中，T代表输入参数的类型，R代表返回值的类型。

## 转化操作

transformation操作，比如说filter（）操作。

最常用的两个转化操作：map（）和filter（）。转化操作map() 接收一个函数，把这个函数用于 RDD 中的每个元素，将函数的返回结果作为结果RDD 中对应元素的值。

## 行动操作

action操作，

` take() `：获取RDD中指定个数的元素，元素以数组的形式返回。

`collect()`：获取整个RDD中的数据。只有当你的整个数据集能在单台机器的内存中放得下时，才能使用 collect() ，因此， collect() 不能用在大规模数据集上。

在大多数情况下，RDD 不能通过 collect() 收集到驱动器进程中，因为它们一般都很大。此时，我们通常要把数据写到诸如 HDFS 或 Amazon S3 这样的分布式的存储系统中。你可
以使用 saveAsTextFile() 、 saveAsSequenceFile() ，或者任意的其他行动操作来把 RDD 的数据内容以各种自带的格式保存起来。

## 向Spark传递函数

- java

  在java中，函数需要作为实现了Spark中Function包中任意接口的对象来传递。具体做法，可以把我们的函数类定义为**匿名内部类**，或者**具名类**。

在java中使用匿名内部类进行函数传递

```java
RDD<String> errors = lines.filter(new Function<String, Boolean>() {  //创建匿名类的对象，达到传递函数的目的。
	public Boolean call(String x) { return x.contains("error"); }  //匿名内部类，实现了Function函数中的接口。
});	
```

在java中使用具名类进行函数传递

```java
class ContainsError implements Function<String , Boolean>(){
  public Boolean call(String x){
    return x.contains("error");
  }
}
RDD<String> errors = lines.filter(new ContainersError());    // 具名类对象，该类实现了Function中的接口。
```

在使用具名类进行函数传递的时候，可以添加构造函数：

```java
class Contains implements Function<String, Boolean>() {
	private String query;
	public Contains(String query) { this.query = query; }
	public Boolean call(String x) { return x.contains(query); }
}

RDD<String> errors = lines.filter(new Contains("error"));
```

## 常见的转化操作和行动操作

​	RDD分为一般数据类型和特定数据类型。

### 基本RDD

​	基本RDD最常用的转化操作是map（）和filter（）。

​	对一个数据为{1, 2, 3, 3}的RDD进行基本的RDD转化操作：

 ![基本RDD操作](pics_Spark\/基本RDD操作.PNG)

​	对数据分别为{1, 2, 3}和{3, 4, 5}的RDD进行针对两个RDD的转化操作:

 ![2个RDD进行操作](pics_Spark\/2个RDD进行操作.PNG)

### 行动操作

​	对一个数据为{1, 2, 3, 3}的RDD进行基本的RDD行动操作：

 ![RDD行动操作](pics_Spark\/RDD行动操作.PNG)

## 在不同RDD类型之间进行转换

由一般RDD转换为特殊类型RDD

| 函数名                            | 等价函数                                  | 用途                                  |
| ------------------------------ | ------------------------------------- | ----------------------------------- |
| `DoubleFlatMapFunction<T>`     | `Function<T, Iterable<Double>>`       | 用于 flatMapToDouble ，以生成 DoubleRDD   |
| `DoubleFunction<T>`            | `Function<T, Double>`                 | 用于 mapToDouble ，以生成DoubleRDD        |
| `PairFlatMapFunction<T, K, V>` | `Function<T, Iterable<Tuple2<K, V>>>` | 用于 flatMapToPair ，以生成 PairRDD<K, V> |
| `PairFunction<T, K, V>`        | `Function<T, Tuple2<K, V>>`           | 用 于 mapToPair ， 以 生 成PairRDD<K, V>  |




第四章  键值对操作
===

​	键值对 RDD （pair RDD）是 Spark 中许多操作所需要的常见数据类型。

## 创建pair RDD

​	两种方式：

​	第一种：存储格式为键值对的格式，那么在读取数据的时候会直接返回由其键值对数据组成的pair RDD。

​	第二种：调用map（）函数，把一个普通的RDD 转换为pair RDD，给map（）函数传递的函数需要返回键值对。

第五章  数据读取与保存
===

## 文件格式

​	Spark支持很多文件格式的读取和保存。并且其方式都非常简单。

- 文本文件的读取
- JSON形式的读取（半结构化数据）
- 逗号分割值形式的文件（CSV格式）
- SequenceFile（由没有相对关系的键值对文件组成的常用Hadoop格式）
- 对象文件
- Hadoop输入输出格式

## 文件系统

​	Spark支持读写很多种文件系统，可以使用任何我们想要的文件格式。

- 本地/“常规”文件系统
- HDFS
- Spark SQL中的结构化数据
  - 通过Spark SQL读取Hive中的数据，Hive是Hadoop上的一种常见的结构化数据源。
  - 通过Spark SQL读取JSON中的数据。
- 数据库
  - Spark可以使用通过JDBC方式连接的数据库。
  - Cassandra
  - HBase
  - Elasticsearch
  - ​

​	

