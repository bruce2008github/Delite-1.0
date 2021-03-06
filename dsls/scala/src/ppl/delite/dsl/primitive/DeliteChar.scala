package ppl.delite.dsl.primitive

/* DeliteChar is a DeliteDSLType primitive used for returning scalar results as a proxy.
 * DeliteChars can be used interchangeably with regular Scala Chars using implicit conversions.
 *
 * author:   Arvind Sujeeth (asujeeth@stanford.edu)
 * created:  Jul 29, 2009
 * modified: Jul 29, 2009
 * 
 * Pervasive Parallelism Laboratory (PPL)
 * Stanford University
 */


import ppl.delite.core.ops.DeliteOP_SingleTask
import ppl.delite.core.appinclude._
import ppl.delite.core.{DeliteProxyFactory, DeliteDSLType}

object DeliteChar {
  ////////////////
  // constructors

  def apply(value: Char): DeliteChar = new Impl(value)

  // The extraction method, is used for pattern matching.  Note that we don't
  // attempt to extract the value from a proxy.
  def unapply(d: DeliteChar): Option[Char] = d match {
    case x: Impl => Some(x.value)
    case _ => None
  }

  // This is the concrete type that actually holds the DSL data type's data
  class Impl extends DeliteChar {

    type DSLType = Impl

    def this(num: Char) = {
      this()
      _value = num
      cvalue = this
      isComputed = true
    }

    var _value : Char = ' '

    def value = force._value

    override def concretize {
      _value = cvalue._value
      cvalue = this
    }

    override def clone = new Impl(value)
  }

  val proxyFactory = new ProxyFactory{}

  trait ProxyFactory extends DeliteProxyFactory[DeliteChar] {
    def newProxy() = new Impl()
  }

  /*
   *  The following are the OPs that this DSL defines, the purpose of OPs is delayed execution and optimization
   */
  protected case class OP_PlusChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value + p2.value)
    }
  }
  protected case class OP_PlusInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value + p2.value)
    }
  }
  protected case class OP_PlusShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value + p2.value)
    }
  }
  protected case class OP_PlusFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteFloat](p1, p2) {
    def task = {
      DeliteFloat(p1.value + p2.value)
    }
  }
  protected case class OP_PlusLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteLong](p1, p2) {
    def task = {
      DeliteLong(p1.value + p2.value)
    }
  }
  protected case class OP_PlusDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteDouble](p1, p2) {
    def task = {
      DeliteDouble(p1.value + p2.value)
    }
  }
  protected case class OP_PlusByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value + p2.value)
    }
  }

  protected case class OP_MinusChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value - p2.value)
    }
  }
  protected case class OP_MinusInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value - p2.value)
    }
  }
  protected case class OP_MinusShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value - p2.value)
    }
  }
  protected case class OP_MinusFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteFloat](p1, p2) {
    def task = {
      DeliteFloat(p1.value - p2.value)
    }
  }
  protected case class OP_MinusLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteLong](p1, p2) {
    def task = {
      DeliteLong(p1.value - p2.value)
    }
  }
  protected case class OP_MinusDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteDouble](p1, p2) {
    def task = {
      DeliteDouble(p1.value - p2.value)
    }
  }
  protected case class OP_MinusByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value - p2.value)
    }
  }

  protected case class OP_TimesChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value * p2.value)
    }
  }
  protected case class OP_TimesInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value * p2.value)
    }
  }
  protected case class OP_TimesShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value * p2.value)
    }
  }
  protected case class OP_TimesFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteFloat](p1, p2) {
    def task = {
      DeliteFloat(p1.value * p2.value)
    }
  }
  protected case class OP_TimesLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteLong](p1, p2) {
    def task = {
      DeliteLong(p1.value * p2.value)
    }
  }
  protected case class OP_TimesDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteDouble](p1, p2) {
    def task = {
      DeliteDouble(p1.value * p2.value)
    }
  }
  protected case class OP_TimesByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value * p2.value)
    }
  }

  protected case class OP_DivChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value / p2.value)
    }
  }
  protected case class OP_DivInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value / p2.value)
    }
  }
  protected case class OP_DivShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value / p2.value)
    }
  }
  protected case class OP_DivFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteFloat](p1, p2) {
    def task = {
      DeliteFloat(p1.value / p2.value)
    }
  }
  protected case class OP_DivLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteLong](p1, p2) {
    def task = {
      DeliteLong(p1.value / p2.value)
    }
  }
  protected case class OP_DivDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteDouble](p1, p2) {
    def task = {
      DeliteDouble(p1.value / p2.value)
    }
  }
  protected case class OP_DivByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value / p2.value)
    }
  }

  protected case class OP_ModChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value % p2.value)
    }
  }
  protected case class OP_ModInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value % p2.value)
    }
  }
  protected case class OP_ModShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value % p2.value)
    }
  }
  protected case class OP_ModFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteFloat](p1, p2) {
    def task = {
      DeliteFloat(p1.value % p2.value)
    }
  }
  protected case class OP_ModLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteLong](p1, p2) {
    def task = {
      DeliteLong(p1.value % p2.value)
    }
  }
  protected case class OP_ModDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteDouble](p1, p2) {
    def task = {
      DeliteDouble(p1.value % p2.value)
    }
  }
  protected case class OP_ModByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteInt](p1, p2) {
    def task = {
      DeliteInt(p1.value % p2.value)
    }
  }

  protected case class OP_NEChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NEInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NEShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NEFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NELong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NEDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }
  protected case class OP_NEByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value != p2.value)
    }
  }

  protected case class OP_LTChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }
  protected case class OP_LTByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value < p2.value)
    }
  }

  protected case class OP_LEChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LEInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LEShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LEFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LELong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LEDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }
  protected case class OP_LEByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value <= p2.value)
    }
  }

  protected case class OP_EQChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }
  protected case class OP_EQByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value == p2.value)
    }
  }

  protected case class OP_GEChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GEInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GEShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GEFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GELong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GEDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }
  protected case class OP_GEByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value >= p2.value)
    }
  }

  protected case class OP_GTChar(p1: DeliteChar, p2: DeliteChar) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTInt(p1: DeliteChar, p2: DeliteInt) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTShort(p1: DeliteChar, p2: DeliteShort) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTFloat(p1: DeliteChar, p2: DeliteFloat) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTLong(p1: DeliteChar, p2: DeliteLong) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTDouble(p1: DeliteChar, p2: DeliteDouble) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }
  protected case class OP_GTByte(p1: DeliteChar, p2: DeliteByte) extends DeliteOP_SingleTask[DeliteBoolean](p1, p2) {
    def task = {
      DeliteBoolean(p1.value > p2.value)
    }
  }

  protected case class OP_unaryPlus(p: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p) {
    def task = {
      DeliteInt(p.value.unary_+)
    }
  }
  protected case class OP_unaryMinus(p: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p) {
    def task = {
      DeliteInt(p.value.unary_-)
    }
  }

  protected case class OP_toChar(p: DeliteChar) extends DeliteOP_SingleTask[DeliteChar](p) {
    def task = {
      DeliteChar(p.value.toChar)
    }
  }
  protected case class OP_toInt(p: DeliteChar) extends DeliteOP_SingleTask[DeliteInt](p) {
    def task = {
      DeliteInt(p.value.toInt)
    }
  }
  protected case class OP_toShort(p: DeliteChar) extends DeliteOP_SingleTask[DeliteShort](p) {
    def task = {
      DeliteShort(p.value.toShort)
    }
  }
  protected case class OP_toFloat(p: DeliteChar) extends DeliteOP_SingleTask[DeliteFloat](p) {
    def task = {
      DeliteFloat(p.value.toFloat)
    }
  }
  protected case class OP_toLong(p: DeliteChar) extends DeliteOP_SingleTask[DeliteLong](p) {
    def task = {
      DeliteLong(p.value.toLong)
    }
  }
  protected case class OP_toDouble(p: DeliteChar) extends DeliteOP_SingleTask[DeliteDouble](p) {
    def task = {
      DeliteDouble(p.value.toDouble)
    }
  }
  protected case class OP_toByte(p: DeliteChar) extends DeliteOP_SingleTask[DeliteByte](p) {
    def task = {
      DeliteByte(p.value.toByte)
    }
  }
  
}


trait DeliteChar extends DelitePrimitive[Char] with DeliteDSLType {
  import DeliteChar._

  def value : Char

  def +(p: DeliteByte)   : DeliteInt     = run(OP_PlusByte(this, p))
  def +(p: DeliteInt)    : DeliteInt     = run(OP_PlusInt(this, p))
  def +(p: DeliteShort)  : DeliteInt     = run(OP_PlusShort(this, p))
  def +(p: DeliteFloat)  : DeliteFloat   = run(OP_PlusFloat(this, p))
  def +(p: DeliteLong)   : DeliteLong    = run(OP_PlusLong(this, p))
  def +(p: DeliteChar)   : DeliteInt     = run(OP_PlusChar(this, p))
  def +(p: DeliteDouble) : DeliteDouble  = run(OP_PlusDouble(this, p))

  def -(p: DeliteByte)   : DeliteInt     = run(OP_MinusByte(this, p))
  def -(p: DeliteInt)    : DeliteInt     = run(OP_MinusInt(this, p))
  def -(p: DeliteShort)  : DeliteInt     = run(OP_MinusShort(this, p))
  def -(p: DeliteFloat)  : DeliteFloat   = run(OP_MinusFloat(this, p))
  def -(p: DeliteLong)   : DeliteLong    = run(OP_MinusLong(this, p))
  def -(p: DeliteChar)   : DeliteInt     = run(OP_MinusChar(this, p))
  def -(p: DeliteDouble) : DeliteDouble  = run(OP_MinusDouble(this, p))

  def *(p: DeliteByte)   : DeliteInt     = run(OP_TimesByte(this, p))
  def *(p: DeliteInt)    : DeliteInt     = run(OP_TimesInt(this, p))
  def *(p: DeliteShort)  : DeliteInt     = run(OP_TimesShort(this, p))
  def *(p: DeliteFloat)  : DeliteFloat   = run(OP_TimesFloat(this, p))
  def *(p: DeliteLong)   : DeliteLong    = run(OP_TimesLong(this, p))
  def *(p: DeliteChar)   : DeliteInt     = run(OP_TimesChar(this, p))
  def *(p: DeliteDouble) : DeliteDouble  = run(OP_TimesDouble(this, p))

  def /(p: DeliteByte)   : DeliteInt     = run(OP_DivByte(this, p))
  def /(p: DeliteInt)    : DeliteInt     = run(OP_DivInt(this, p))
  def /(p: DeliteShort)  : DeliteInt     = run(OP_DivShort(this, p))
  def /(p: DeliteFloat)  : DeliteFloat   = run(OP_DivFloat(this, p))
  def /(p: DeliteLong)   : DeliteLong    = run(OP_DivLong(this, p))
  def /(p: DeliteChar)   : DeliteInt     = run(OP_DivChar(this, p))
  def /(p: DeliteDouble) : DeliteDouble  = run(OP_DivDouble(this, p))

  def %(p: DeliteByte)   : DeliteInt     = run(OP_ModByte(this, p))
  def %(p: DeliteInt)    : DeliteInt     = run(OP_ModInt(this, p))
  def %(p: DeliteShort)  : DeliteInt     = run(OP_ModShort(this, p))
  def %(p: DeliteFloat)  : DeliteFloat   = run(OP_ModFloat(this, p))
  def %(p: DeliteLong)   : DeliteLong    = run(OP_ModLong(this, p))
  def %(p: DeliteChar)   : DeliteInt     = run(OP_ModChar(this, p))
  def %(p: DeliteDouble) : DeliteDouble  = run(OP_ModDouble(this, p))

  def !=(p: DeliteChar) : DeliteBoolean = run(OP_NEChar(this, p))
  def !=(p: DeliteByte)   : DeliteBoolean = run(OP_NEByte(this, p))
  def !=(p: DeliteInt)    : DeliteBoolean = run(OP_NEInt(this, p))
  def !=(p: DeliteShort)  : DeliteBoolean = run(OP_NEShort(this, p))
  def !=(p: DeliteFloat)  : DeliteBoolean = run(OP_NEFloat(this, p))
  def !=(p: DeliteLong)   : DeliteBoolean = run(OP_NELong(this, p))
  def !=(p: DeliteDouble)   : DeliteBoolean = run(OP_NEDouble(this, p))

  def <(p: DeliteChar) : DeliteBoolean = run(OP_LTChar(this, p))
  def <(p: DeliteInt)    : DeliteBoolean = run(OP_LTInt(this, p))
  def <(p: DeliteShort)  : DeliteBoolean = run(OP_LTShort(this, p))
  def <(p: DeliteFloat)  : DeliteBoolean = run(OP_LTFloat(this, p))
  def <(p: DeliteLong)   : DeliteBoolean = run(OP_LTLong(this, p))
  def <(p: DeliteDouble)   : DeliteBoolean = run(OP_LTDouble(this, p))
  def <(p: DeliteByte)   : DeliteBoolean = run(OP_LTByte(this, p))

  def <=(p: DeliteChar) : DeliteBoolean = run(OP_LEChar(this, p))
  def <=(p: DeliteInt)    : DeliteBoolean = run(OP_LEInt(this, p))
  def <=(p: DeliteShort)  : DeliteBoolean = run(OP_LEShort(this, p))
  def <=(p: DeliteFloat)  : DeliteBoolean= run(OP_LEFloat(this, p))
  def <=(p: DeliteLong)   : DeliteBoolean = run(OP_LELong(this, p))
  def <=(p: DeliteDouble)   : DeliteBoolean = run(OP_LEDouble(this, p))
  def <=(p: DeliteByte)   : DeliteBoolean = run(OP_LEByte(this, p))
  
  // equality is less straightforward because it is defined in Object. Our implicit conversions
  // don't work because there exists a matching method already, and == on the self type appears
  // forced to return a real Boolean value.

  def ==(p: Int)           : DeliteBoolean = equals(DeliteInt(p))
  def equals(p: Int)       : DeliteBoolean = equals(DeliteInt(p))
  def ==(p: DeliteInt)     : Boolean = equals(p)
  def equals(p: DeliteInt) : DeliteBoolean = run(OP_EQInt(this, p))

  def ==(p: Double)           : DeliteBoolean = equals(DeliteDouble(p))
  def equals(p: Double)       : DeliteBoolean = equals(DeliteDouble(p))
  def ==(p: DeliteDouble)     : DeliteBoolean = equals(p)
  def equals(p: DeliteDouble) : DeliteBoolean = run(OP_EQDouble(this, p))

  def ==(p: Short)           : DeliteBoolean = equals(DeliteShort(p))
  def equals(p: Short)       : DeliteBoolean = equals(DeliteShort(p))
  def ==(p: DeliteShort)     : DeliteBoolean = equals(p)
  def equals(p: DeliteShort) : DeliteBoolean = run(OP_EQShort(this, p))

  def ==(p: Float)           : DeliteBoolean = equals(DeliteFloat(p))
  def equals(p: Float)       : DeliteBoolean = equals(DeliteFloat(p))
  def ==(p: DeliteFloat)     : DeliteBoolean = equals(p)
  def equals(p: DeliteFloat) : DeliteBoolean = run(OP_EQFloat(this, p))

  def ==(p: Long)           : DeliteBoolean = equals(DeliteLong(p))
  def equals(p: Long)       : DeliteBoolean = equals(DeliteLong(p))
  def ==(p: DeliteLong)     : DeliteBoolean = equals(p)
  def equals(p: DeliteLong) : DeliteBoolean = run(OP_EQLong(this, p))

  def ==(p: Char)           : DeliteBoolean = equals(DeliteChar(p))
  def equals(p: Char)       : DeliteBoolean = equals(DeliteChar(p))
  def ==(p: DeliteChar)     : Boolean = (this.value == p.value)
  def equals(p: DeliteChar) : DeliteBoolean = run(OP_EQChar(this, p))

  def ==(p: Byte)           : DeliteBoolean = equals(DeliteByte(p))
  def equals(p: Byte)       : DeliteBoolean = equals(DeliteByte(p))
  def ==(p: DeliteByte)     : DeliteBoolean = equals(p)
  def equals(p: DeliteByte) : DeliteBoolean = run(OP_EQByte(this, p))

  def >(p: DeliteChar) : DeliteBoolean = run(OP_GTChar(this, p))
  def >(p: DeliteInt)    : DeliteBoolean = run(OP_GTInt(this, p))
  def >(p: DeliteShort)  : DeliteBoolean = run(OP_GTShort(this, p))
  def >(p: DeliteFloat)  : DeliteBoolean = run(OP_GTFloat(this, p))
  def >(p: DeliteLong)   : DeliteBoolean = run(OP_GTLong(this, p))
  def >(p: DeliteDouble)   : DeliteBoolean = run(OP_GTDouble(this, p))
  def >(p: DeliteByte)   : DeliteBoolean = run(OP_GTByte(this, p))

  def >=(p: DeliteChar) : DeliteBoolean = run(OP_GEChar(this, p))
  def >=(p: DeliteInt)    : DeliteBoolean = run(OP_GEInt(this, p))
  def >=(p: DeliteShort)  : DeliteBoolean = run(OP_GEShort(this, p))
  def >=(p: DeliteFloat)  : DeliteBoolean = run(OP_GEFloat(this, p))
  def >=(p: DeliteLong)   : DeliteBoolean = run(OP_GELong(this, p))
  def >=(p: DeliteDouble)   : DeliteBoolean = run(OP_GEDouble(this, p))
  def >=(p: DeliteByte)   : DeliteBoolean = run(OP_GEByte(this, p))

  def toChar : DeliteChar = run(OP_toChar(this))
  def toInt    : DeliteInt    = run(OP_toInt(this))
  def toShort  : DeliteShort  = run(OP_toShort(this))
  def toFloat  : DeliteFloat  = run(OP_toFloat(this))
  def toLong   : DeliteLong   = run(OP_toLong(this))
  def toDouble   : DeliteDouble   = run(OP_toDouble(this))
  def toByte   : DeliteByte   = run(OP_toByte(this))

}