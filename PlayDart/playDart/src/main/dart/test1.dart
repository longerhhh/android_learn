import 'dart:core';

import 'DemoClass.dart';

/**
 * main 函数的几种使用方式：
 * void main() {}
 * main() {}
 * void main(List<String> arguments) {}
 */

main() {
  // dartTypeTest();
  // dartFuncTest();
  dartOperationTest();
}

void dartOperationTest() {
  // 常用运算符： +加，-减，*乘，/除，-负（一元运算符），%取余
  int a = 1, b = 2, c= 3, d=4,e=5,f=6,g=7;
  print('e/c=5/3=${e/c}'); // 真实的商，保留小数结果，商是双浮点型
  print('e~/c=5~/3=${e~/c}'); // 商退一法取整
  print('e%c=5%3=${e%c}');
  print('b=$b');
  print('b++:${b++}, $b');
  // 支持自减自增 ++， --
  // a++,++a,a--, --a
  // 关系：==，！=，>，<，>=，<=
  // 类型强转 as，类似kotlin
  // is 判断类型，类似kotlin
  // is! 判断类型不是指定类型，kotlin对应为 !is
  // ??= 变量为空时赋值
  int? m = null;
  print('m=$m');
  print('a=$a');
  a ??= 10;
  print('a=$a');
  m ??= 10;
  print('m=$m');
  // -=,+=,*=,/=,%=,  ~/=,<<=,>>=,  &=,|=,^=
  // ^是异或运算符，两个数按位比较，相同取0，不同取1
  // &	AND
  // |	OR
  // ^	XOR
  // ~expr	Unary bitwise complement (0s become 1s; 1s become 0s) ，按位取反，0-》1，1-》0
  // <<	Shift left
  // >>	Shift right
  a ^= 3;
  print('a=$a');
  // &&, ||, !

  // 三元运算符？：
  // condition ? exp1 : exp2
  // 如果condition为真，执行并返回exp1，否则执行并返回exp2

  // 二元运算符？？
  // exp1 ?? exp2
  // 如果exp1结果非空，返回exp1，否则执行并返回exp2
  String playerName(String name) => name != null ? name : 'Guest';
  String playerName2(String name) => name ?? 'Guest';

  // 严格的来讲， “两个点” 的级联语法不是一个运算符。 它只是一个 Dart 的特殊语法。
  // 级联运算符可以嵌套，类比kotlin高阶函数apply，run一类的
  // ()	Function application	Represents a function call
  // []	List access	Refers to the value at the specified index in the list
  // .	Member access	Refers to a property of an expression;
  //    example: foo.bar selects property barfrom expression foo
  // ?.	Conditional member access	Like ., but the leftmost operand can be null;
  //    example: foo?.bar selects property bar from expression foo unless foo is null
  //    (in which case the value of foo?.bar is null)

  // 条件 if(){}else if(){}else{}
  // for (int i = 0; i < candidates.length; i++) {
  //   var candidate = candidates[i];
  //   if (candidate.yearsExperience < 5) {
  //     continue;
  //   }
  //   candidate.interview();
  // }
  // 如果对象实现了 Iterable 接口 （例如，list 或者 set）。 那么上面示例完全可以用另一种方式来实现：
  //
  // candidates
  //     .where((c) => c.yearsExperience >= 5)
  //     .forEach((c) => c.interview());
  //

  // switch 和 case
  // 在 Dart 中 switch 语句使用 == 比较整数，字符串，或者编译时常量。
  // 比较的对象必须都是同一个类的实例（并且不可以是子类），类必须没有对 == 重写。
  // 枚举类型 可以用于 switch 语句。
  //
  // 提示： 在 Dart 中 Switch 语句仅适用于有限的情况下， 例如在 interpreter 或 scanner 中。
  //
  // 在 case 语句中，每个非空的 case 语句结尾需要跟一个 break 语句。
  // 除 break 以外，还有可以使用 continue, throw，者 return。
  //
  // 当没有 case 语句匹配时，执行 default 代码：

  // 但是， Dart 支持空 case 语句， 允许程序以 fall-through 的形式执行。
  //
  // var command = 'CLOSED';
  // switch (command) {
  //   case 'CLOSED': // Empty case falls through.
  //   case 'NOW_CLOSED':
  //     // Runs for both CLOSED and NOW_CLOSED.
  //     executeNowClosed();
  //     break;
  // }


  // 在非空 case 中实现 fall-through 形式， 可以使用 continue 语句结合 lable 的方式实现:
  //
  // var command = 'CLOSED';
  // switch (command) {
  //   case 'CLOSED':
  //     executeClosed();
  //     continue nowClosed;
  //   // Continues executing at the nowClosed label.
  //
  //   nowClosed:
  //   case 'NOW_CLOSED':
  //     // Runs for both CLOSED and NOW_CLOSED.
  //     executeNowClosed();
  //     break;
  // }
  // case 语句可以拥有局部变量， 这些局部变量只能在这个语句的作用域中可见。

  // assert 语句只在开发环境中有效， 在生产环境是无效的； Flutter 中的 assert 只在 debug 模式 中有效。
  // 开发用的工具，例如 dartdevc 默认是开启 assert 功能。
  // 其他的一些工具， 例如 dart 和 dart2js, 支持通过命令行开启 assert ： --enable-asserts。
  //
  // assert 的第二个参数可以为其添加一个字符串消息。
  // assert 的第一个参数可以是解析为布尔值的任何表达式。 如果表达式结果为 true ， 则断言成功，并继续执行。
  // 如果表达式结果为 false ， 则断言失败，并抛出异常 (AssertionError) 。

  // try - catch - finally
  // try 没有catch到 finally，之后有异常抛出，没异常继续

}

void dartFuncTest() {
  namedOptionalParam(1, d:5);
  positionOptionalParam(1);
  defaultParam(a:10);
  defaultPara2(888);
  defaultPara2();

// 级联调用 cascade
  final d = Demo(0)
  ..a = 5
  ..s='ee';
  print('d.a=${d.a}, d.s = ${d.s}');

  // 函数做参数
  [1,2,3,4,5,6].forEach(printElement);
  fun1((e) => "int to str: $e");

  // 函数赋值给变量
  final f1 = fun1;
  const f2 = fun1;
  var f3 = f1;
  // lambda 或 closure 匿名函数
  final f4 = (a) => "f4: a=$a";
  f1(f4);
  f2((i)=>"i=$i");
  f3((e)=>"f3:e=$e");
  final a=(int i) => "i=$i";

  // 变量作用域类似于c语言 ///////////
  // 所有函数都会返回一个值。 如果没有明确指定返回值， 函数体会被隐式的添加 return null; 语句。
  // 函数可以比较，同一个类的同一个静态方法是相同的，
  // 同一个类产生的不同对象下的同一个非静态方法是不同的，因为他们引用的对象不同
}

String fun1(String intToStr(int e)) {
  var s = intToStr(10000);
  print(s);
  return s;
}

void printElement(int e) {
  print('e=$e');
}

/** 默认参数值
 * 在定义方法的时候，可以使用 = 来定义可选参数的默认值。 默认值只能是编译时常量。如果没有提供默认值，则默认值为 null
 * 默认参数值的参数必须定义在可算参数{}或[]内
 * 不推荐： 旧版本代码中可能使用的是冒号 (:) 而不是 = 来设置参数默认值。 原因是起初命名参数只支持 : 。
 * 这种支持可能会被弃用。 建议 使用 = 指定默认值
 */

// use {} to put default param
void defaultParam({int a=5,b=true}) {
  print('a=$a, b=$b');
}

// use [] to put default param
void defaultPara2([a=5,b=const[1,2,3]]) {
  print('a=$a,b=$b');
}

// 命名可选参数
// required 必选, required is defined in meta package
// 调用函数时，可以使用指定命名参数 paramName: value
void namedOptionalParam(int aa, {int? a, String? b, bool? c, double? d}) {
  print('a=$a,b=$b,c=$c,d=$d');
}

// 位置可选参数
// 将参数放到 [] 中来标记参数是可选的
void positionOptionalParam(int a, [bool? b]) {
  print('a=$a, b=$b');
}

void dartTypeTest() {
  // 基本变量
  print('abc');
  var a = 1;
  print('a=$a');
  a = 2;
  print('a=$a');
  print('a.class=${a.runtimeType}');
  const b = "abc";
  print('b=$b');
  final c = 'first'
      'second'
      'third';
  print('c=$c');
  final d;
  d = '''line one;
  line two;
  line three;
  ''';
  print('d=$d');
  double e = 1.5;
  print('e=$e');
  e = 1.6;
  print('e=$e');
  int f = a;
  print('f=$f');
  String g = c;
  print('g=$g');

  // 基本运算
  print('a.class=${a.runtimeType}');
  print('a+e=${a+e}');
  print('a.class=${a.runtimeType}');
  print('a-e=${a-e}');
  print('a*a=${a*a}');
  print('a+a=${a+a}');
  print('a/e=${a/e}');
  print('a.class=${a.runtimeType}');
  print('a++: ${a++}');
  print('a++: ${a++}');
  print('a++: ${a++}');
  print('a--: ${a--}');
  print('a--: ${a--}');
  print('a--: ${a--}');
  print('a.class=${a.runtimeType}');
  print('a/=a, a=$a');
  var s = r"In a raw string, even \n isn't special.";
  print('$s');

  // 字符串，特殊字符定义，默认UTF-16
  var clapping = '\u{1f44f}';
  print(clapping);
  print(clapping.codeUnits.map((e) => e.toRadixString(16)));
  print(clapping.runes.toList().map((e) => e.toRadixString(16)));

  // runes 定义UTF-32字符，可用于表情包
  Runes input = new Runes(
      '\u2665  \u{1f605}  \u{1f60e}  \u{1f47b}  \u{1f596}  \u{1f44d}');
  print(new String.fromCharCodes(input));

  // 编译时常量，标识符，或运算符，感觉可用于类似自定义运算符，类似kotlin中to这种自定义的运算符
  #radix;
  #bar;
}