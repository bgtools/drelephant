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

/**
 * A runnable/task which is to be executed by {@link PriorityBasedThreadPoolExecutor} may extend this class for
 * the executor to consider priority. If the task does not implement this interface, it will be executed considering
 * NORMAL priority.
 */
public abstract class RunnableWithPriority implements Runnable {
  /**
   * Helper method to get {@link RunnableWithPriority} implementation for a given Runnable with the given priority.
   *
   * @param runnable Runnable to be wrapped and executed.
   * @param priority Associated priority for the runnable.
   * @return a wrapper {@link RunnableWithPriority} object.
   */
  public static RunnableWithPriority get(final Runnable runnable, final Priority priority) {
    return new RunnableWithPriority() {
      @Override
      public void run() {
        runnable.run();
      }

      @Override
      public Priority getPriority() {
        return priority;
      }
    };
  }

  /**
   * Helper method to get {@link RunnableWithPriority} implementation for a given Runnable with NORMAL priority.
   *
   * @param runnable Runnable to be wrapped and executed.
   * @return a wrapper {@link RunnableWithPriority} object.
   */
  public static RunnableWithPriority get(Runnable runnable) {
    return get(runnable, Priority.NORMAL);
  }

  /**
   * Get priority of the runnable.
   * @return a {@link Priority} object.
   */
  public abstract Priority getPriority();
}