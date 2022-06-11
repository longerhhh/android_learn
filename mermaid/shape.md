# flowchart

```mermaid
graph LR
rectangle[I'm content.]
circle((I'm content.))
stadiumShape([体育场 I'm content.])
subroutine[[子程序 I'm content.]]
cylindrical[(圆柱形 I'm content.)]
asymmetric>非对称图形I'm content.]
rhombus{菱形 I'm content.}
hexagon{{六边形 I'm content.}}
parallelogram[/平行四边形 I'm content./]
parallelogram_alt[\反向平行四边形 I'm content.\]
trapezoid[/梯形\]
trapezoid_alt[\梯形/]
a-->b
c==>d
e---f
g--mid-->h
i--mid---j
k---|mid|l
m-- mid ---n
q-.->|mid|p
r-.mid-.->s
t--> u & v -->w
x & y-->z
```

```mermaid
flowchart LR
a & b --> c & d
e --o f --x g
h <--> i o--o j x--x k
l---->m
n====>o

```

x & y --> z
&前后必须有空格
flowchart LR的ui显示效果更好更多

### 所有连接线

| Length            |    1   |    2    |     3    |
|-------------------|:------:|:-------:|:--------:|
| Normal            |  `---` |  `----` |  `-----` |
| Normal with arrow |  `-->` |  `--->` |  `---->` |
| Thick             |  `===` |  `====` |  `=====` |
| Thick with arrow  |  `==>` |  `===>` |  `====>` |
| Dotted            | `-.-`  | `-..-`  | `-...-`  |
| Dotted with arrow | `-.->` | `-..->` | `-...->` |

### 如果文字中要用到特殊字符，使用`""`包裹
例如
```mermaid
flowchart LR
aaa["what{} () [] hhhh'''''\\\\\\\///////\"]
```

### 使用编码字符
```mermaid
flowchart LR
B["A dec char:#9829;"]
```

### 多子图联动
```mermaid
flowchart TB
    c1-->a2
    subgraph one
    a1-->a2
    end
    subgraph two
    b1-->b2
    end
    subgraph three
    c1-->c2
    end
```

### 外部指引
```mermaid
flowchart TB
    c1-->a2
    subgraph id[ one_graph ]
    a1-->a2
    end
```

### 多图嵌套
```mermaid
flowchart LR
  subgraph TOP
    direction TB
    subgraph B1
        direction RL
        i1 -->f1
    end
    subgraph B2
        direction BT
        i2 -->f2
    end
  end
  A --> TOP --> B
  B1 --> B2

```


```mermaid
graph LR
a[a]-->b[b]
b-->c((aaa))
c-->d([whwhw])
d-->e[/wwwww/]
e--fddd-->f[/fffffffffffff\]
f-.->g(dd)
g-.ffff-.->h[[d]]

a==>i[(ff)]
```
