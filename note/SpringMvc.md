# SpringMVC 核心功能说明



# SpringMVC 主要类说明

## ViewResolver

ViewResolver视图解析，主要处理请求返回数据类型，json、xml、html、
excel、pdf等

下面介绍SpringMVC中几个重要的viewResolver

1. ContentNegotiatingViewResolver

直译就是内容协商解析器，主要处理返回内容的类型。

2. BeanNameViewResolver

实体类名称解析器，从名称可以看出来会匹配名称，Controller中返回的view
名称如果有该同名称的视图解析器，则会使用此解析器解析。

例如：自定义一个HelloView，同时Controller中functionA方法返回helloView,
```
...
public String functionA() {
       ..
    return "helloView";
} 
```
这样就会由HelloView来解析返回客户端的内容。

3. InternalResourceViewResolver

内部资源解析器，准确说就是一个默认的ViewResolver，可以处理任何返回的viewName.
在该类的源代码类注释上有这么说明
```
it's good practice to put JSP files that just serve as views under
 * WEB-INF, to hide them from direct access (e.g. via a manually entered URL).
 * Only controllers will be able to access them then
```
大意就是，这个解析器通常是用作WEB-INF下的jsp文件的解析。所以我们经常会配置这个
解析器里解析jsp文件

4. 