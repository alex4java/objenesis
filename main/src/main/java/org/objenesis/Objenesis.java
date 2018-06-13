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

import org.objenesis.instantiator.ObjectInstantiator;

/**
 * Objenesis的通用接口
 *
 * @author Henri Tremblay
 */
public interface Objenesis {

   /**
    * 不调用任何构造函数创建对象
    *
    * @param <T> 实例化类型
    * @param clazz 要实例化的类
    * @return 实例化后的对象
    */
   <T> T newInstance(Class<T> clazz);

   /**
    * 根据提供的类选择最佳的实例化器，如果你需要从同一个类创建很多实例，使用newInstance
    *
    * @param <T> 实例化类型
    * @param clazz 要实例化的类
    * @return 类的专有实例化器
    */
   <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> clazz);
}
