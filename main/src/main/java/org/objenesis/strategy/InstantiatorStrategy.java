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
package org.objenesis.strategy;

import org.objenesis.instantiator.ObjectInstantiator;

/**
 * 实例化策略
 *
 * @author Henri Tremblay
 */
public interface InstantiatorStrategy {

   /**
    * 为给定的类创建一个专用的实例化器
    *
    * @param <T> 实例化的类型
    * @param type 将要被实例化的类
    * @return 专用的实例化器
    */
   <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> type);
}
