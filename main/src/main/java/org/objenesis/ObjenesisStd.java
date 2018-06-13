/**
 * Copyright 2006-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.objenesis;

import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * std：Standard
 * 使用StdInstantiatorStrategy的标准实现方式
 *
 * @author Henri Tremblay
 */
public class ObjenesisStd extends ObjenesisBase {

   /**
    * 固定使用StdInstantiatorStrategy策略
    */
   public ObjenesisStd() {
      super(new StdInstantiatorStrategy());
   }

   /**
    * 固定使用StdInstantiatorStrategy策略，需要用户提供是否使用缓存
    *
    * @param useCache If {@link org.objenesis.instantiator.ObjectInstantiator}s should be cached
    */
   public ObjenesisStd(boolean useCache) {
      super(new StdInstantiatorStrategy(), useCache);
   }
}
