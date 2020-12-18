package org.example.JMH;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

/**
 * @Author: Derek
 * @DateTime: 2020/9/15 8:37
 * @Description: test org.example.JMH
 */
public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3)//预热，由于JVM中对于特定代码会存在优化（本地化），预热对于测试结果很重要
    @Fork(5)//起多少个线程执行
    @BenchmarkMode(Mode.Throughput)//基准测试的模式 Throughput:吞吐量
    @Measurement(iterations = 1, time = 3)//测试哪一段代码
    public void testForEach() {
        PS.foreach();
    }

    @Benchmark
    @Warmup(iterations = 1, time = 3)//预热，由于JVM中对于特定代码会存在优化（本地化），预热对于测试结果很重要
    @Fork(5)//起多少个线程执行
    @BenchmarkMode(Mode.Throughput)//基准测试的模式 Throughput:吞吐量
    @Measurement(iterations = 1, time = 3)//测试哪一段代码
    public void testParallel() {
        PS.parallel();
    }
}
