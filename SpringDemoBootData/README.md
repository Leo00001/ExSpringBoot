
## Rxjs 简单使用

[官网](https://cn.rx.js.org)

Rxjs 5.x与6.x以上区别比较大，6.x以后使用管道来使用操作符号

在Intelllij中使用Rxjs需要配置使用Es6 File->Setting->Languages && Frameworks->Javascript选择Es6

``` 
    // 引入js
    <script src="https://cdn.bootcss.com/rxjs/6.3.3/rxjs.umd.js"></script>
    <script>
            // 引入方法
            const {fromEvent} = rxjs;
            const {throttleTime, map} = rxjs.operators;
            const {create} = rxjs.Observable;
            
            fromEvent(window, "load").subscribe(event => console.log(event));
             
            // 操作符
            fromEvent(docuemnt.querySelector(), "click")
                .pipe(
                    // 防抖处理
                    throttleTime(1000)
                )
                .subscribe(() => console.log("receive"));
                 
    </script>
```