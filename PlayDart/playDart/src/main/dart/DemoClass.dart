

class Demo{
  int a = 0;
  String s = "a";
  Demo(this.a);
  Demo.origin():this(0);
}

class DemoA extends Demo{
  num b=0;
  DemoA(super.a);
  //The implicitly invoked unnamed constructor from 'Demo' has required parameters.
  DemoA.origin():this(0);
}

class DemoB {
  num a=-1;
  final num b;
  DemoB():b=0{
    print('a=$a');
  }
  DemoB.before():b=100{
    print('a=$a');
  }
  DemoB.be(int c):a=200, b=300{
    print('a=$a');
  }
}

class ImmutableDemo{
  final num a,b;
  static final ImmutableDemo i = ImmutableDemo(0, 0);
  const ImmutableDemo(this.a, this.b);
}

class FactoryDemo{
  String s;
  static const Map<String, FactoryDemo> _cache = {};

  FactoryDemo._internal(this.s);

  factory FactoryDemo(String s) {
    if (_cache.containsKey(s)) {
      return _cache[s]??FactoryDemo._internal(s);
    } else {
      return FactoryDemo._internal(s);
    }
  }
}

class GetSetDemo{
  int a,b;
  GetSetDemo(this.a,this.b);

  int get c => a+b;
  num get d => a-b;
  set c(value) => b=a-c;
}

abstract class ADemo{
  void a();
}

abstract class BDemo{
  void b();
}

abstract class CDemo{
  void a();
}

abstract class DDemo{
  void d();
  void c() {
    print('c');
  }
}

class IDemo extends DDemo implements ADemo,BDemo,CDemo{
  @override
  void d() {

  }

  @override
  void a() {
    // TODO: implement a
  }

  @override
  dynamic noSuchMethod(Invocation invocation) => print('no such method,'
      ' ${invocation.memberName}, ${invocation.namedArguments}');
}

enum EnumDemo{
  a,b,c,d,e,f,g
}

main() {
  // immutableClassTest();
  print(EnumDemo.a==0);
  print(EnumDemo.e==4);
  print(EnumDemo.a.index==0);
}

void immutableClassTest() {
  
  var i1 = ImmutableDemo(0,0);
  const i2 = ImmutableDemo(0, 0);
  const i3 = ImmutableDemo(0, 0);
  const i4 = ImmutableDemo(0, 1);
  print(i1==i2);
  print(i2==i3);
  print(i3==i4);
}