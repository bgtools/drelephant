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

public enum Priority {
  MIN_PRIORITY(Integer.MIN_VALUE),
  LOW(-100),
  NORMAL(0),
  HIGH(100),
  MAX_PRIORITY(Integer.MAX_VALUE);

  private int intPriority;

  Priority(int priority) {
    intPriority = priority;
  }

  /**
   * Get integer priority.
   * @return priority in integer.
   */
  public int getPriority() {
    return intPriority;
  }
}
