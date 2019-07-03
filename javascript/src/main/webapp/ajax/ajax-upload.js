( ( w )=>{

    // 创建一个纯实例( instance )
    const component = Object.create( null );

    let descriptor = {
        configurable : false ,
        writable : false ,
        enumerable : true ,
        value :  ( url , files , success , failed ) =>{

            // 检查参数是否符合要求 ( 比如确保 url 非空、files 是个数组并且有数据存在 )

            let $http = new XMLHttpRequest(); // 1、创建 XMLHttpRequest 实例
            // 2、绑定事件监听器
            if( success && typeof(success) === "function"  ) {
                // 为 load 事件绑定事件监听器 ( 实际上就是 $http.readyState === $http.DONE 时执行的操作 )
                $http.addEventListener("load", () => {
                    // 6、处理响应数据
                    // 使用 JSON 格式声明一个实例，其中封装了响应数据
                    let o = {
                        "status": $http.status,
                        "reason": $http.statusText,
                        "headers": $http.getAllResponseHeaders(),
                        "body": $http.response
                    };
                    Object.freeze(o);
                    //调用 success 函数并为其传入 参数
                    success(o);
                }, false);
            }

            if( failed && typeof(failed) === "function") {
                // 为 error 事件绑定事件监听器
                $http.addEventListener( "error" , failed , false );
            }

            $http.open( "POST" , url ); // 3、打开连接

            // 4、将 files 数组中的所有数据添加到 FormData 实例中
            let formData = new FormData();
            files.forEach( ( f ) => {
                formData.append( f.name , f );
            } );

            // 5、发送请求
            $http.send( formData );

        }
    }

    Object.defineProperty( component , "upload"  , descriptor );


    if( w.X ) { // 如果 w 实例中已经存在名称为 X 的属性
        // 则将 component 的所有属性拷贝到 w.X 属性中
        Object.assign( w.X , component );
    } else { // 如果 w 实例从来没有绑定过名称为 X 的属性
        // 为 w 实例定义一个名称为 X 的属性，取值为 component
        Object.defineProperty(w, "X", {enumerable: true, value: component});
    }

} )( window );