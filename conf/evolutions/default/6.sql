#
# Copyright 2016 LinkedIn Corp.
#
# Licensed under the Apache License, Version 2.0 (the "License"); you may not
# use this file except in compliance with the License. You may obtain a copy of
# the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
# License for the specific language governing permissions and limitations under
# the License.
#

# --- !Ups

CREATE TABLE backfill_info (
  app_type         VARCHAR(20)   NOT NULL              COMMENT 'Application/Job Type e.g, Pig, Hive, Spark, HadoopJava',
  backfill_ts      BIGINT        UNSIGNED NOT NULL     COMMENT 'The timestamp at which backfill should start from',

  PRIMARY KEY (app_type)
);

# --- !Downs

DROP TABLE backfill_info;
