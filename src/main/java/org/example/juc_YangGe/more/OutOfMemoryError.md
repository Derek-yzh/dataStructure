#一、 GC overhead limit exceeded
###GC回收时间过长时会抛出OutOfMemoryError
    过长的定义是:超过98%的时间用亲做GC并且回收了不到2%的堆内存,
    连续多次GC都只回收了不到2%的极端情况下才会抛出.
###假如不抛出GC overhead limit错误会发生什么情况呢?
    那就是GC清理的这么点内存很快会再次填满,迫使GC再次执行,这样就形成恶性循环,
    CPU使用率一直是100% 而GC却没有任何成果.
    
    
#二、 Direct buffer memory
####写NIO程序经常使用ByteBuffer来读取或者写入数据,这是一种基于通道(Channel)与缓冲区(Buffer)的I/0方式:
    它可以使用Native函数库直接分配堆外内存,然后通过一个存储在Java堆里面的DirectByteBuffer对象作为
    这块内存的引用进行操作.这样能在一些场景中显著提高性能,因为避免了在Java堆和Native堆中来回复制数据.
    
####ByteBuffer.allocate(capability)分配VM堆内存,属于GC管辖范围,由于需要拷贝所以速度相对较慢
####ByteBuffer.allocateDirect(capability)分配OS本地内存,不属于GC管辖范围,由于不需要内存拷贝所以速度相对较快
    但如果不断分配本地内存,堆内存很少使用,那么JWM就不需要执行GC,DirectByteBuffer对象们就不会被回收,
    这时候堆内存充足,但本地内存可能已经使用光了,再次尝试分配本地内存就会出现OutOfMemoryError,那程序就直接崩溃了.


#三、 Unable to create new native thread
###导致原因:
    1.你的应用创建了太多线程了，一个应用进程创建多个线程,超过系统承戴极限
    2.你的服务器并不允许你的应用程序创建这么多线程, Linux系统默认允许单个进程可以创建的线程数是1024个,
    你的应用创建超过这个数量,就会报java.lang.OutOfMemoryError: unable to create new native thread
###解决办法:
    1.想办法降低你应用程序创建线程的数量,分析应用是否真的需要创建这么多线程,如果不是，改代码将线程数降到最低
    2.对于有的应用,确实需要创建很多线程,远超过Linux系统的默认1024个线程的限制，可以通过修改linux服务器配置,扩大Linux默认限制


#四、 MetaSpace
    Java 8及之后的版本使用Metaspace来替代水久代
    Metaspace是方法区在Hotspot中的实现，它与持久代最大的区别在于:Metaspace并不在虚拟机内存中而是使用本地内存
    即在java8中,class metadata(the virtual machines internal presentation of Java class)，被存储在叫做Metaspace的native memory
    永久代java8后被原空间Metaspace取代了了存放了以下信息:
        虚拟机加载的类信息
        常量池
        静态变量
        即时编译后的代码
    模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的

