package org.example;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

public class StringConcatBenchmark {
  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @Fork(value = 1, warmups = 2)
  public String run(ExecutionPlan plan) {
    String result = "";
    for (int i = 0; i < plan.iterations; i++) {
      result += "-";
    }
    return result;
  }

}
