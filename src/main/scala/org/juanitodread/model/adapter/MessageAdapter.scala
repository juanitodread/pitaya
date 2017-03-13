package org.juanitodread.model.adapter

/**
 * MessageAdapter trait to common adapters.
 *
 * @author juan.sandoval
 *
 * @param <C>
 * @param <P>
 */
trait MessageAdapter[C, P] {

  def toPlatform(clientMessage: C): P

  def toClient(commonMessage: P): C
}