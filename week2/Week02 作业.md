# Week02 作业



**1.（选做）**使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。

找到Class的文件夹，从包目录开始使用java命令

```java
java -XX:+PrintGCDetails week02.GCLogAnalysis
```

便可以打印出GC日志，java8的默认GC是ParellelGC， java 11的默认GC是 G1。

**2.（选做）**使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

使用

```java
//http://localhost:8088/api/hello  ==> backend service 
java -jar gateway-server-0.0.1-SNAPSHOT.jar
```

打开网关的后端服务， 并用super benchmarker进行压测

```java
sb -u  http://localhost:8088/api/hello -c 40 -N 30
```

得到结果

```shell
Status 200:    229549

RPS: 7385.1 (requests/second)
Max: 85ms
Min: 0ms
Avg: 0.1ms

  50%   below 0ms
  60%   below 0ms
  70%   below 0ms
  80%   below 0ms
  90%   below 0ms
  95%   below 0ms
  98%   below 1ms
  99%   below 3ms
99.9%   below 10ms

```

**3.（选做）**如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

**4.（必做）**根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。

首先，垃圾回收的基本原理是引用计数和引用追踪（清除不可达引用）。Java的所有垃圾算法都是基于引用追踪的。最原始的是标记清除算法。

- 标记：遍历所有可达对象，并在本地内存中记下。
- 清除：不可达对象所占用的内存可以被内存分配器重新分配。

优化点：

- 一段时间后，内存会存在碎片化的问题，所以需要整理算法。

- 针对堆上对象的不同生命周期，将堆内存分治为年轻代和老年代。

- 年轻代里，垃圾的概率很高，因此需要更高效的算法。年轻代分为伊甸园（Eden），存活区（From，To）。采用“标记-复制”算法，把Eden和存活期`From` 里标记的存活对象复制到`TO`然后整块清空Eden和存活期`From`。 这里标记和复制可以同时进行。

- 老年代GC发生的频率比年轻代小很多。同时，因为预期老年代中的对象大部分是存活的，所以不再使用标记和复制(Mark and Copy)算法。老年代GC还通过整理，来最小化内存碎片。

  1. 通过标志位(marked bit),标记所有通过 GC roots 可达的对象

  2. 删除所有不可达对象

  3. 整理老年代空间中的内容，方法是将所有的存活对象复制，从老年代空间开始的地方依次存放。

在JVM中，根据GC的并发性，又分为了以下几种GC的具体策略：


串行GC: `-XX:+UseSerialGC`. 单线程执行所有GC工作，因为没有线程间通信而相对效率。最适用于单核处理器及小型数据集（100Mb）的情况。

并行GC: `-XX:+UseParallelGC` 并行处理minor GC。适用于中大型数据集，多核多线程的情况。并行GC会默认在major GC的时候也进行并行操作，如果想在major GC进行单线程操作， 使用 `-XX:-UseParallelOldGC`

除此以外，Java Hotspot VM还提供了两种[The Mostly Concurrent Collectors](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/concurrent.html#mostly_concurrent)，CMS GC `-XX:+UseConcMarkSweepGC` 和 G1 GC`-XX:+UseG1GC`。这种方式能确保最大的并发性，即一边执行业务逻辑，一边进行垃圾回收，适合中大型的数据集。他牺牲了一定的吞吐量却保证了足够的相应时间。

总的来说，数据小单核的话就选串行，想要最高的峰值吞吐量不在意相应时间就选并行GC，在意响应时间就选CMS或者G1。G1会优于CMS，因为G1将堆内存自动划分为多个Region（如1024个），根据实际情况自适应年轻代和老年代所占Region的个数，比较现代高效。CMS的原理是将老年代的标记清除整理算法分为几个阶段（Pipeline的思想），有一些阶段不需要STW，因此可以与业务线程并发，提高了业务的响应速率。

**5.（选做）**运行课上的例子，以及 Netty 的例子，分析相关现象。

单线程 -->  一个线程接一个连接 --> 线程池--> NIO非阻塞

**6.（必做）**写一段代码，使用 HttpClient 或 OkHttp 访问 [ http://localhost:8801 ](http://localhost:8801/)，代码提交到 GitHub。

```java
package week02.homework;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientHelper {

    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    //do Http Get Method
    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            return EntityUtils.toString(entity);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String res = getAsString(url);
        System.out.println("url:"+url);
        System.out.println("response:" + res);
    }

}
```



