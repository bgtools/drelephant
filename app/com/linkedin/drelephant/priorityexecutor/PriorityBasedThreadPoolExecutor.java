/*
 * Copyright 2016 LinkedIn Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.linkedin.drelephant.priorityexecutor;

import java.util.Comparator;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Priority based thread pool executor which takes priority of the task to be executed into account.
 */
public class PriorityBasedThreadPoolExecutor extends ThreadPoolExecutor {
  public PriorityBasedThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
      TimeUnit unit, ThreadFactory threadFactory) {
    super(corePoolSize, maximumPoolSize, keepAliveTime, unit,
        new PriorityBlockingQueue<Runnable>(11, new PriorityTaskComparator()), threadFactory);
  }

  @Override
  protected <T> RunnableFuture<T> newTaskFor(final Runnable runnable, final T value) {
    if (runnable instanceof RunnableWithPriority) {
      return new PriorityFutureTask<T>(((RunnableWithPriority)runnable).getPriority(), runnable, value);
    }
    return new PriorityFutureTask<T>(Priority.NORMAL, runnable, value);
  }

  private static final class PriorityFutureTask<T> extends FutureTask<T>
      implements Comparable<PriorityFutureTask<T>> {
    private final Priority priority;

    public PriorityFutureTask(final Priority priority, final Runnable runnable,
        final T result) {
      super(runnable, result);
      this.priority = priority;
    }

    @Override
    public int compareTo(final PriorityFutureTask<T> task) {
      long priorityDiff = task.priority.getPriority() - this.priority.getPriority();
      return ((priorityDiff == 0) ? 0 : (priorityDiff < 0) ? -1 : 1);
    }
  }

  private static class PriorityTaskComparator implements Comparator<Runnable> {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public int compare(final Runnable left, final Runnable right) {
      if (left instanceof PriorityFutureTask && right instanceof PriorityFutureTask) {
        return ((PriorityFutureTask) left).compareTo((PriorityFutureTask) right);
      }
      return 0;
    }
  }
}