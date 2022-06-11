import 'dart:math';

class A{
  void a(){
    print('a');
  }
}

class B{
  void b() {
    print('b');
  }
}

mixin C{
  bool isA =false,isB=false;
  void c() {
    if (isA) {
      print('c:a');
    } else if (isB) {
      print('c:b');
    } else {
      print('c:?');
    }
  }
}

mixin E {
  void e() {
    print('e');
  }
}

class D extends A with C,E implements B  {
  @override
  void b() {
    // isA=true;
    c();
    a();
    print(e);
  }
}

main() {
  D().b();
}