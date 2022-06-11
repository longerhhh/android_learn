import com.google.devtools.ksp.symbol.*

class Demo {
}

// 获取所有的成员函数
fun KSClassDeclaration.getDeclaredFunctions(): List<KSFunctionDeclaration> {
    return this.declarations.filterIsInstance<KSFunctionDeclaration>().toList()
}

// 检查类或函数是否为本地
fun KSDeclaration.isLocal(): Boolean {
    return this.parentDeclaration != null && this.parentDeclaration !is KSClassDeclaration
}

// 查找类型别名指向的实际类或类和接口的声明
fun KSTypeAlias.findActualType(): KSClassDeclaration {
    val resolvedType = this.type.resolve().declaration
    return if (resolvedType is KSTypeAlias) {
        resolvedType.findActualType()
    } else {
        resolvedType as KSClassDeclaration
    }
}

// 在文件中批量收集禁止显示的内容
// @file:kotlin.Suppress("Example1", "Example2")
fun KSFile.suppressedNames(): List<String> {
    val ignoredNames = mutableListOf<String>()
    annotations.forEach {
        if (it.shortName.asString() == "Suppress" && it.annotationType.resolve()?.declaration?.qualifiedName?.asString() == "kotlin.Suppress") {
            it.arguments.forEach {
                (it.value as List<String>).forEach { ignoredNames.add(it) }
            }
        }
    }
    return ignoredNames
}