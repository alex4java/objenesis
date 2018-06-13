/**
 * Copyright 2006-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.objenesis.instantiator.sun;

import java.io.NotSerializableException;
import java.lang.reflect.Constructor;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.SerializationInstantiatorHelper;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/**
 * 使用 sun.reflect.ReflectionFactory 来实例化一个对象（JDK版本 >= 1.4）
 * 这个实例化器将以与序列化兼容的方式创建类 , 调用第一个不可序列化的超类'无参数构造函数.
 * 这是最佳的方式来实例化对象，但它不是堆每个平台都有效。
 *
 * @author Leonardo Mesquita
 * @see ObjectInstantiator
 */
@Instantiator(Typology.SERIALIZATION)
public class SunReflectionFactorySerializationInstantiator<T> implements ObjectInstantiator<T> {

   private final Constructor<T> mungedConstructor;

   public SunReflectionFactorySerializationInstantiator(Class<T> type) {
      // 返回指定类的第一个非序列化的父类（包括自己）
      Class<? super T> nonSerializableAncestor = SerializationInstantiatorHelper
         .getNonSerializableSuperClass(type);

      Constructor<? super T> nonSerializableAncestorConstructor;
      try {
         // 获得无参构造函数（该类必须有无参构造函数）
         nonSerializableAncestorConstructor = nonSerializableAncestor
            .getDeclaredConstructor((Class[]) null);
      } catch (NoSuchMethodException e) {
         throw new ObjenesisException(new NotSerializableException(type + " has no suitable superclass constructor"));
      }

      //获得munged构造函数对象，这个构造函数中会调用nonSerializableAncestorConstructor
      mungedConstructor = SunReflectionFactoryHelper.newConstructorForSerialization(
         type, nonSerializableAncestorConstructor);
      mungedConstructor.setAccessible(true);
   }

   public T newInstance() {
      try {
         return mungedConstructor.newInstance((Object[]) null);
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }
}
