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
package org.objenesis.instantiator.annotations;

/**
 * 实例化的类型枚举类
 * @author Henri Tremblay
 */
public enum Typology {
   /**
    * 标准实例化，不调用构造函数
    */
   STANDARD,

   /**
    * 使用序列化
    */
   SERIALIZATION,

   /**
    * 标记一个实例化器，其行为不像STANDARD和SERIALIZATION（例如调用一个构造函数，一直失败）
    */
   NOT_COMPLIANT,

   /**
    * 无类型指定
    */
   UNKNOWN
}
