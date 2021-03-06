/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.giraph.block_app.framework.piece.interfaces;

import org.apache.giraph.graph.Vertex;
import org.apache.giraph.writable.kryo.markers.NonKryoWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * Interface representing actions that happen on worker, for each vertex,
 * during receive phase:
 * <ul>
 * <li> to receive messages from vertices </li>
 * <li> to receive data from master through aggregators </li>
 * </ul>
 *
 * Marked to not allow seriazliation, as it should be created on the worker,
 * so should never be serialiized, disallow only for catching problems early.
 *
 * @param <I> Vertex id type
 * @param <V> Vertex value type
 * @param <E> Edge value type
 * @param <M> Message type
 */
@SuppressWarnings("rawtypes")
public interface VertexReceiver<I extends WritableComparable,
    V extends Writable, E extends Writable, M extends Writable>
    extends NonKryoWritable {
  /**
   * Must be defined by user to do computation on a single Vertex.
   *
   * @param vertex   Vertex
   * @param messages Messages that were sent to this vertex in the previous
   *                 superstep.  Each message is only guaranteed to have
   *                 a life expectancy as long as next() is not called.
   */
  void vertexReceive(Vertex<I, V, E> vertex, Iterable<M> messages);
}
