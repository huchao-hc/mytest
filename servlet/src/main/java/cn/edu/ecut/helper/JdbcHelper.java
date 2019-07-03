package cn.edu.ecut.helper;

import java.sql.*;

public class JdbcHelper {

    private static final JdbcHelper HELPER = new JdbcHelper();

    private JdbcHelper(){
    }

    public static JdbcHelper getInstance() {
        HELPER.load(); // 加载驱动
        HELPER.connect(); // 建立连接
        return HELPER ;
    }

    /*
    private final String driverClassName = "oracle.jdbc.driver.OracleDriver" ;
    private final String url = "jdbc:oracle:thin:@localhost:1521:ecut" ;
    private final String username = "malajava" ;
    private final String password = "malajava" ;
    */

    private final String driverClassName = "com.mysql.cj.jdbc.Driver" ;
    private final String url = "jdbc:mysql://localhost:3306/ecut?serverTimezone=GMT%2B8&useSSL=false" ;
    private final String username = "root" ;
    private final String password = "" ;


    // 声明一个用来表示驱动类尚未加载的成员变量
    private boolean unloaded = true ; // true 表示未加载，false 表示已经加载
    // 声明一个用来加载驱动的方法
    private synchronized void load(){
        // 如果尚未加载驱动
        if( unloaded ) {
            // 则加载驱动
            try {
                Class.forName( driverClassName ); //  Servlet 环境下可能会加载失败
                unloaded = false ;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // 声明一个用来保存Connection对象的成员变量
    private Connection connection ;
    // 用于判断连接对象是否是【无效的】
    private boolean connectionIsInvalid(){

        if( connection == null ) {
            return true ;
        }

        try {
            return !connection.isValid( 1 );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true ;
    }
    // 获取连接
    private void connect(){
        if( this.connectionIsInvalid() ){ // 如果连接( connection )是无效的
            // 就创建新的连接
            try {
                connection = DriverManager.getConnection( url , username , password );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // 为了能够通过 JdbcHelper 实例来获取内部的连接对象，所以增加该方法
    public Connection getConnection(){
        return this.connection ;
    }

    // 用来创建 Statement 对象
    public Statement create(){
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    /**
     * 将第一个参数传入的SQL语句交给数据库服务器预先编译，
     * 同时，通过第二个参数告知数据库服务器是否需要由数据库服务器返回它所产生的键
     * @param SQL 需要交给数据库服务器预先编译的SQL语句
     * @param generate 告知数据库服务器是否需要返回由数据库自动产生的键，true 表示需要，false表示不需要
     * @return 返回可以执行指定 SQL 语句的 PreparedStatement 对象
     */
    public PreparedStatement prepare( String SQL , boolean generate ) {
        PreparedStatement ps = null ;
        try {
            if (generate) {
                ps = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            } else {
                ps = connection.prepareStatement(SQL, Statement.NO_GENERATED_KEYS);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return ps ;
    }

    private void setParameters( PreparedStatement ps , Object[] params ) {
        // 如果存在参数占位符
        if (params != null && params.length > 0) {
            try {
                // 则依次设置参数占位符的值
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用来执行任意给定的 DML 语句 ( INSERT / UPDATE / DELETE )
     * @param SQL 被执行的SQL语句
     * @param params
     * @return
     */
    public int update( final String SQL , Object... params ) {

        String s = SQL ;
        // 如果 SQL 是个 null  或 空串
        if( s == null || ( s = s.trim() ).isEmpty()  ) {
            throw new RuntimeException( "被执行的DML语句不能为空" );
        }

        s = s.toUpperCase(); // 将 s 字符串中所有的字符串一律转换为 大写字符
        if( s.startsWith( "INSERT" ) || s.startsWith( "UPDATE" ) || s.startsWith( "DELETE" ) ) {
            // 因为这里不考虑获取由数据库产生的键，所以第二个参数是 false
            PreparedStatement ps = this.prepare(SQL, false);

            try {
                // 如果存在参数占位符，则依次设置参数占位符的值
                this.setParameters( ps , params );
                return ps.executeUpdate(); // "执行" DML 语句
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.release(ps);
            }
            return -1 ;
        } else {
            throw  new RuntimeException( "你丫执行的不是DML语句！" );
        }
    }

    /**
     * 执行插入单行数据的SQL语句，并返回由数据库产生的主键
     * @param SQL 需要被执行的插入单行数据的SQL语句
     * @param params 被执行的带有参数占位符的各个参数的值
     * @return 如果保存成功则返回由数据库产生的主键，否则返回 -1
     */
    public int insert(  final String SQL , Object... params  ) {

        String s = SQL ;
        // 如果 SQL 是个 null  或 空串
        if( s == null || ( s = s.trim() ).isEmpty()  ) {
            throw new RuntimeException( "被执行的 INSERT 语句不能为空" );
        }

        s = s.toUpperCase(); // 将 s 字符串中所有的字符串一律转换为 大写字符
        if( s.startsWith( "INSERT" ) ) {

            PreparedStatement ps = this.prepare( SQL , true ); // 期望返回由数据库自动产生主键值
            try {
                // 如果存在参数占位符，则依次设置参数占位符的值
                this.setParameters( ps , params );
                int count = ps.executeUpdate(); // "执行" INSERT 语句
                if( count > 0 ) { // 如果 插入操作执行成功，则可以获取由数据库产生的主键
                    ResultSet rs = ps.getGeneratedKeys(); // 通过 PreparedStatement 对象来获取由数据库产生的主键对应的 结果集对象
                    if( rs.next() ) { // 因为约定仅仅插入单行数据，因此至多返回一个 主键
                        int id = rs.getInt( 1 ) ; // 从 结果集中获取 第一列的值 ( 这个值就是由数据库产生的主键 )
                        return id ; // 返回由数据库产生的主键
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.release(ps);
            }
            return -1 ; // 这里返回的 -1 表示失败了
        } else {
            throw  new RuntimeException( "你丫执行的不是 INSERT 语句！" );
        }
    }

    /**
     * 执行查询语句并返回查询结果
     * ( 当 创建结果集对象的 Statement 对象关联的所有结果集都关闭时，自动关闭该Statement对象 )
     * @param SQL 需要被执行的查询语句
     * @param params 如果被执行的查询语句中包含参数占位符，则依次设置这些参数占位符的值
     * @return 返回查询语句的执行结果
     */
    public ResultSet query( final String SQL , Object... params ) {
        String s = SQL ;
        // 如果 SQL 是个 null  或 空串
        if( s == null || ( s = s.trim() ).isEmpty()  ) {
            throw new RuntimeException( "被执行的查询语句不能为空" );
        }
        s = s.toUpperCase(); // 将 s 字符串中所有的字符串一律转换为 大写字符
        if( s.startsWith( "SELECT" ) ) {

            PreparedStatement ps = this.prepare( SQL , false );

            try{
                if( ! ps.isCloseOnCompletion() ) { // 注意 感叹号
                    // 当 当前的 PreparedStatement 关联的所有 ResultSet 都关闭时，自动关闭 PreparedStatement 对象
                    ps.closeOnCompletion(); // 启用 PreparedStatement 对象的 closeOnCompletion 选项
                }
                this.setParameters( ps , params );
               return ps.executeQuery();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
            // 注意，这里还不能关闭 PreparedStatement 对象，因为结果集还要使用它
            // 如果关闭了 PreparedStatement 对象，则无法处理 ResultSet 对象
            return null ;
        } else {
            throw  new RuntimeException( "你丫执行的不是 SELECT 语句！" );
        }
    }

    // 将结果集输出到控制台
    public void show( ResultSet rs ){
        try {
            // 获取结果集元数据 ( meta data )
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取结果集中所包含的列数
            final int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                // 从结果集元数据中获取 第 i 列的 名称 ( 可能是列名 ，也可能是别名，当指定了别名时必须使用别名 )
                String label = rsmd.getColumnLabel(i); // 注意使用 getColumnName 无法获取别名
                System.out.print(label + "\t");
            }
            System.out.println();

            while (rs.next()) {
                // 注意，列的索引 从 1 开始
                for (int i = 1; i <= columnCount; i++) {
                    Object value = rs.getObject(i); // 获取结果集中当前行的第 i 列 的值
                    System.out.print(value);
                    System.out.print("\t");
                }
                System.out.println(); // 结果集中的当前行数据都输出完后，输出一个换行标记
            }
        } catch ( SQLException e ) {
             e.printStackTrace();
        }
    }

    public void release( AutoCloseable ac ) {
        if( ac != null ) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void release(){
        this.release( connection ); // 关闭连接
        connection = null ; // 将 connection 变量的值修改为 null
        System.gc(); // 向 JVM 的 垃圾回收线程 建议 垃圾回收
    }


    private void disableAutoCommit(){
        if( connection != null ){
            try {
                connection.setAutoCommit( false );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void enableAutoCommit(){
        if( connection != null ){
            try {
                connection.setAutoCommit( true );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void beginTransaction(){
        if( connection != null ) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void commit(){
        if( connection != null ) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback(){
        if( connection != null ) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现在同一个事务中执行多个DML操作
     * @param runner 用来执行多个DML语句的对象
     */
    public void execute( Runner runner ){
        // 关闭自动提交
        this.disableAutoCommit();
        // 开启一个新的事务
        this.beginTransaction();
        try {
            runner.doInTransaction();
            // 提交事务
            this.commit();
        } catch ( Exception e ) {
            e.printStackTrace();
            // 回滚事务
            this.rollback();
        }
        // 启用自动提交
        this.enableAutoCommit();
    }

}
