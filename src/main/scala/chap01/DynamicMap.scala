package chap01

import scala.language.dynamics

class MyMap extends Dynamic {
  private val map = Map("foo" -> "1", "bar" -> 2)

  def selectDynamic(fieldName: String) = {
    println(s"selectDynamic->$fieldName called\n")
    map.get(fieldName)
  }

  def applyDynamic(method: String)(args: Any*) {
    println(s"applyDynamic->$method called, args: $args\n")
  }

  def updateDynamic(method: String)(args: Any) {
    println(s"updateDynamic->$method called, args: $args\n")
  }

  def applyDynamicNamed(method: String)(args: (String, Any)*) {
    println(s"applyDynamicNamed->$method called, args: $args")
    for ((key, value) <- args) {
      println(s"key: $key, value: $value")
    }
  }
}

/*通俗点讲动态类型的类必须继承自 Dynamic，
当使用 qual.sel，而 Qual 类未定义 sel 属性或方法时,会调用 selectDynamic(method: String) 方法;
当 qual.name = "Unmi" 时会调用类似 updateDynamic(method: String)(args: Any) 这样的方法;
还有 applyDynamic，applyDynamicNamed 这两个方法的自动调用。

所有的变化就在下面这四个方法中：
selectDynamic
updateDynamic
applyDynamic
applyDynamicNamed

*/
object DynamicMapExample extends App {
  val someMap = new MyMap
  println(someMap.foo) // //calll selectDynamic

  println(someMap.bar)
  println(someMap.selectDynamic("bar"))

  someMap.config("Hello", "Unmi") ////call applyDynamic

  someMap.products = ("iPhone", "Nexus") //call updateDynamic
  someMap.showInfo(screenName = "Unmi", email = "fantasia@sina.com") //call applyDynamicNamed
}

/**
现在来看发生了什么，Person 继承自  Dynamic，并且有引入 scala.language.dynamics。对 p 调用的方法(属性) 都不存在，但是都调用到了正常的动态方法。
所以仍然要对这四个动态方法(确切的讲是四种类型的方法，因为比如你可以定义多个不同的 updateDynamic 方法，其余三个也同此) 分别加以说明：

1. selectDynamic

在调用找不到了无参方法时，会去寻找它，调用效果如下：

p.sayHello  也可以写成  p.selectDynamic("sayHello")

也就是说编译器在看到 p.sayHello 调用会根据  selectDynamic(method: String) 相当于创建了方法 def sayHello = .......，
也就是把动态方法 selectDynamic(method: String) 换成   sayHello 却是。所以说 Scala 的 Dynamic 类中的 xxxDynamic 方法相当是模板方法。

applyDynamic，updateDynamic 和  applyDynamicNamed 这三个方法第二个括号中的参数类型，或个数需根据实际应用来定。
这四个动态方法的第一个括号中的参数都是动态调用时的方法名。

2. applyDynamic

在进行有参数的方法调用时，会去找寻它，调用效果如下：

p.config("Hello", "Unmi") 可以写成 p.applyDynamic("config")("Hello", "Unmi")

还是这么理解: 把这个动态方法定义的方法名和第一个括号与参数替换成调用的方法名就知道怎么回事，例如把：

def applyDynamic(method: String)(args: Any*) 中的 applyDynamic(method: String) 替换成被调用方法名  config，就是：

def config(args: Any*)        //p.config("Hello", "Unmi") 要调用的就是这么个方法

所以第二个括号中的参数由你自己来定，比如说想这么调用 p.config("Hello", 100, 30)，那么你可的动态方法可以这么定义：

def applyDynamic(method: String)(greeting: String, high: Int, low: Int) { ...... }

这个规则同样适用于 updateDynamic 和  applyDynamicNamed 这两个方法。

3. updateDynamic

等号赋值操作时会调用 updateDynamic 方法，调用效果如下：

p.products = ("iPhone", "Nexus")  可写成 p.updateDynamic("products")(("iPhone", "Nexus"))，按照同样的理解方法，相当于 Person 中定义了 def products(args: Any) 方法。

4. applyDynamicNamed

同样是 apply 开头，所以这个方法是对 applyDynamic 方法的补充，即使没有 applyDynamicNamed，单用 applyDynamic 也能达成我们的要求。
applyDynamicNamed 只是让你用命名参数调用时方便，也就是像

p.showInfo(screenName="Unmi", email="fantasia@sina.com") 这样用命名参数的方式来调用动态方法时会调用 updateDynamicNamed 方法。
有了这个方法在命名传递参数就方便处理 key/value 值。

这四个方法在一个动态类中只能分别定义一个版本，否则会产生二义性，这和普通方法的重载不一样的。
柯里化后的函数第二个括号中的参数可根据实际调用来定义，定义成  (args: Any*) 可包打天下。
  **/