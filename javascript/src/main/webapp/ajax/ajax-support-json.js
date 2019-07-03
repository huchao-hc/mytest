( (w)=>{

    let component = {
        "ajax" : function( method , url , data , success , failed ){
            // 如果 method 不是 string 类型  或者 method 是 string 类型但取值不是 POST 也不是 GET
            if( ( typeof(method) !== "string" ) || ( ( method = method.trim().toLocaleUpperCase() ) !== "POST"  && method !== "GET" ) ){
                throw new Error( "浏览器不支持的请求方式" );
            }

            // 1、创建 XMLHttpRequest 对象(class) 的 实例 (instance)
            let $http = new XMLHttpRequest();

            // 2、为相应事件绑定事件监听器
            if( success && typeof( success ) === "function" ) {
                // 监听 load 事件 ( 也就是指定当 $http.readyState === $http.DONE 时执行的操作 )
                $http.addEventListener( "load" , ()=>{
                    // 6、处理响应数据
                    // 使用 JSON 格式声明一个实例，其中封装了响应数据
                    let o = {
                        "status" : $http.status ,
                        "reason" : $http.statusText ,
                        "headers" : $http.getAllResponseHeaders() ,
                        "body" : $http.response
                    };
                    //调用 success 函数并为其传入 参数
                    success( o );
                } , false );
            }

            if( failed && typeof( failed ) === "function" ) {
                $http.addEventListener( "error" , failed , false );
            }

            // 3、打开连接
            $http.open( method , url );

            let body = null ;
            // 4、判断是否是POST请求
            if( method === "POST" ){
                $http.setRequestHeader( "content-type","application/x-www-form-urlencoded" );
                body = ( typeof(data) === "undefined" ) ? null : data ;
            }

            // 5、发送请求
            $http.send( body );
        } ,
        "post" : function ( url , data , success , failed ) {
            this.ajax( "post" , url , data , success , failed );
        },
        "get" : function ( url , data , success , failed ) {
            this.ajax( "get" , url , data , success , failed );
        }
    };

    if( w.X ) {
        for( let name in component ){
            w.X[ name ] = component[ name ];
            console.log( w.X );
        }
    } else {
        w.X = component ;
    }

} ) ( window );