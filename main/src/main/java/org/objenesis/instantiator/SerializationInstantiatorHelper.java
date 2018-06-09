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
package org.objenesis.instantiator;

import java.io.Serializable;

/**
 * Helper for common serialization-compatible instantiation functions
 *
 * @author Leonardo Mesquita
 */
public class SerializationInstantiatorHelper {

   /**
    * 返回指定类的第一个非序列化的父类，根据Java对象序列化规范，
    * 从流中读取的对象通过从对象层次结构中的第一个不可序列化的超类调用可访问的无参数构造函数进行初始化，
    * 从而允许正确初始化不可序列化字段的状态。
    *
    * @param <T> Type to instantiate
    * @param type Serializable class for which the first non-serializable superclass is to be found
    * @return The first non-serializable superclass of 'type'.
    * @see java.io.Serializable
    */
   public static <T> Class<? super T> getNonSerializableSuperClass(Class<T> type) {
      Class<? super T> result = type;
      while(Serializable.class.isAssignableFrom(result)) {
         result = result.getSuperclass();
         if(result == null) {
            throw new Error("Bad class hierarchy: No non-serializable parents");
         }
      }
      return result;

   }
}
