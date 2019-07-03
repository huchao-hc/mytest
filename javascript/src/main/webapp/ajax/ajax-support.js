( (w)=>{

    // 创建一个纯实例( instance )
    const component = Object.create( null );


    let descriptor = {
        configurable : false ,
        writable : false ,
        enumerable : true  ,
        value : ( method , url , data , success , failed  ) => {

            // 如果 method 不是 string 类型
            // 或者 method 是 string 类型但取值不是 POST 也不是 GET
            if( ( typeof(method) !== "string" ) ||
                ( ( method = method.trim().toLocaleUpperCase() ) !== "POST"
                    && method !== "GET" ) ){
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
        }
    };

    // 为 component 实例 定义 一个属性 ( 这个属性的值是个函数 )
    Object.defineProperty( component , "ajax"  , descriptor );


    descriptor = {
        configurable : false ,
        writable : false ,
        enumerable : true  ,
        value : ( url , data , success , failed  ) => {
            component.ajax( "post" , url , data , success , failed );
        }
    };

    Object.defineProperty( component , "post"  , descriptor );

    descriptor = {
        configurable : false ,
        writable : false ,
        enumerable : true  ,
        value : ( url , data , success , failed  ) => {
            component.ajax( "get" , url , data , success , failed );
        }
    };

    Object.defineProperty( component , "get"  , descriptor );

    if( w.X ) { // 如果 w 实例中已经存在名称为 X 的属性
        // 则将 component 的所有属性拷贝到 w.X 属性中
        Object.assign( w.X , component );
    } else { // 如果 w 实例从来没有绑定过名称为 X 的属性
        // w.X = component ;  // window.X = component ;
        // 为 w 实例定义一个名称为 X 的属性，取值为 component
        Object.defineProperty(w, "X", {enumerable: true, value: component});
    }

} ) ( window );