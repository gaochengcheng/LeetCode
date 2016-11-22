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

# 第二章

## 2.1下载Spark

spark目录介绍

core、streaming、python目录：包含Spark项目主要组件的源代码。

## 2.2Spark中python和Scala的shell

和其他 shell 工具不一样的是，在其他 shell 工具中你只能使用单机的硬盘和内存来操作数据，而 Spark shell 可用来与分布式存储在许多机器的内存或者硬盘上的数据进行交互，并且处理过程的分发由 Spark 自动控制完成。

Spark 提供 Python 以及Scala 的增强版 shell，支持与集群的连接。

退出任意一个shell的方法是按：`ctrl`+`D`.

## 2.3Spark核心概念简介

从上层来看，每个 Spark 应用都由一个驱动器程序（driver program）来发起集群上的各种并行操作。驱动器程序包含应用的 main 函数，并且定义了集群上的分布式数据集，还对这些分布式数据集应用了相关操作。在前面的例子里，实际的驱动器程序就是 Spark shell 本身，你只需要输入想要运行的操作就可以了。

其实 Spark API 最神奇的地方就在于像 filter 这样基于函数的操作也会在集群上并行执行。也就是说，Spark 会自动将
函数（比如 line.contains("Python") ）发到各个执行器节点上。这样，你就可以在单一的驱动器程序中编程，并且让代码自动运行在多个节点上。

## 独立应用

