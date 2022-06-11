# dart 教程

## 变量

### number

#### int
整数值不大于 64 位， 具体取决于平台。 在 Dart VM 上， 值的范围从 -263 到 263 - 1. Dart 被编译为 JavaScript 时，使用 JavaScript numbers, 值的范围从 -253 到 253 - 1.

####double
64 位（双精度）浮点数，依据 IEEE 754 标准。

int 和 double 都是 num. 的亚类型。 num 类型包括基本运算 +， -， /， 和 *， 以及 abs ()， ceil ()， 和 floor ()， 等函数方法。 （按位运算符，例如 ?，定义在 int 类中。） 如果 num 及其亚类型找不到你想要的方法， 尝试查找使用 dart:math 库。