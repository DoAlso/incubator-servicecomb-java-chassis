/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.transport.rest.vertx.accesslog;

import com.netflix.config.DynamicPropertyFactory;

public final class AccessLogConfiguration {
  private static final String BASE = "cse.accesslog.";

  private static final String ACCESSLOG_ENABLED = BASE + "enabled";

  private static final String ACCESSLOG_PATTERN = BASE + "pattern";

  public static final AccessLogConfiguration INSTANCE = new AccessLogConfiguration();

  private AccessLogConfiguration() {

  }

  public boolean getAccessLogEnabled() {
    String enabled = getProperty("false", ACCESSLOG_ENABLED);
    return Boolean.parseBoolean(enabled);
  }

  public String getAccesslogPattern() {
    String pattern = getProperty("%h - - %t %r %s %B", ACCESSLOG_PATTERN);
    return pattern;
  }

  private String getProperty(String defaultValue, String key) {
    String property = DynamicPropertyFactory.getInstance().getStringProperty(key, defaultValue).get();
    if (null == property) {
      return defaultValue;
    } else {
      return property;
    }
  }
}