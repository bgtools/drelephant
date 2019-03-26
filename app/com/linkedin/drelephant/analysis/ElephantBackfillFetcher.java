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

package com.linkedin.drelephant.analysis;

import java.util.List;

/**
 * Interface to be implemented by fetchers to support backfill.
 */
public interface ElephantBackfillFetcher {
  /**
   * Fetches a list of jobs to be backfilled for analysis by Dr. Elephant. It is recommended that the backfill fetcher
   * sets the app type for each job by calling {@link AnalyticJob#setAppType(ApplicationType)}.
   *
   * @param startTime Start time from when jobs have to be backfilled.
   * @param endTime End time upto which jobs can be backfilled.
   * @return list of jobs to be backfilled for analysis.
   * @throws Exception
   */
  List<AnalyticJob> fetchJobsForBackfill(long startTime, long endTime) throws Exception;
}