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
import org.objenesis.strategy.InstantiatorStrategy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展基类，需要提供策略
 *
 * @author Henri Tremblay
 */
public class ObjenesisBase implements Objenesis {

   /** Strategy used by this Objenesi implementation to create classes */
   protected final InstantiatorStrategy strategy;

   /** 策略缓存. Key = Class, Value = InstantiatorStrategy */
   protected ConcurrentHashMap<String, ObjectInstantiator<?>> cache;

   /**
    * 提供一个实例化策略类，并使用缓存
    *
    * @param strategy Strategy to use
    */
   public ObjenesisBase(InstantiatorStrategy strategy) {
      this(strategy, true);
   }

   /**
    * 使用传入的策略和是否使用缓存
    *
    * @param strategy 使用的策略
    * @param useCache ObjectInstantiator是否允许被缓存
    */
   public ObjenesisBase(InstantiatorStrategy strategy, boolean useCache) {
      if(strategy == null) {
         throw new IllegalArgumentException("A strategy can't be null");
      }
      this.strategy = strategy;
      this.cache = useCache ? new ConcurrentHashMap<String, ObjectInstantiator<?>>() : null;
   }

   @Override
   public String toString() {
      return getClass().getName() + " using " + strategy.getClass().getName()
         + (cache == null ? " without" : " with") + " caching";
   }

   /**
    * 不调用任何构造函数创建一个新对象
    *
    * @param clazz Class to instantiate
    * @return New instance of clazz
    */
   public <T> T newInstance(Class<T> clazz) {
      return getInstantiatorOf(clazz).newInstance();
   }

   /**
    * 通过策略类来获取实例化类并放入缓存
    *
    * @param clazz Class to instantiate
    * @return Instantiator dedicated to the class
    */
   @SuppressWarnings("unchecked")
   public <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> clazz) {
      if(clazz.isPrimitive()) {
         // 基本类型
         throw new IllegalArgumentException("Primitive types can't be instantiated in Java");
      }
      if(cache == null) {
         return strategy.newInstantiatorOf(clazz);
      }
      ObjectInstantiator<?> instantiator = cache.get(clazz.getName());
      if(instantiator == null) {
         ObjectInstantiator<?> newInstantiator = strategy.newInstantiatorOf(clazz);
         instantiator = cache.putIfAbsent(clazz.getName(), newInstantiator);
         if(instantiator == null) {
            instantiator = newInstantiator;
         }
      }
      return (ObjectInstantiator<T>) instantiator;
   }
}
