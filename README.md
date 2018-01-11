# 移动应用开发中期答辩
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;1552662 方炜豪&emsp;&emsp;&emsp;&emsp;&emsp;1552663 田雍恺
## 目前进度
- [x] 模拟登陆预约场馆
- [x] 查看场馆
- [x] 预约场馆
- [x] 取消预约
- [x] 展示场馆
- [ ] 推荐场馆
- [x] 手机绑定
- [ ] 编辑个人信息
- [ ] 分享
- [x] 定时提醒
- [x] 计步
- [x] 发布动态
- [x] 查看动态


## 类的设计
![](https://github.com/fangweihao123/Photo-Repo/raw/master/class.png)
### ButterKnife的使用
```java
    import butterknife.BindView;
    import butterknife.ButterKnife;
    ......
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.navigation_bar) BottomNavigationBar bottomNavigationBar;
    ......
    ButterKnife.bind(this,v);
    ......
```

### Glide的使用
```java
     private void loadPic(){
        HttpService.sendOkHttpRequest(picUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                try{
                    final String bingPic = response.body().string();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getActivity()).load(bingPic).into(imageView);
                        }
                    });
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

    }
```

### 模拟登录的实现
<font face="微软雅黑" size=5>在使用浏览器过程中，利用fiddler进行抓包，可以获取到模拟登录的过程信息，然后利用okhttp模拟发送请求，利用用户的学号密码将请求发给校园网服务器，得到登录成功的jsessionid，之后就可以利用这个jsessionid进入个人页面。
其中需要注意的是，由于手机端与主机端访问请求头不一致，因此再模拟登录过程中有一步连接中有三个字母不一致，主机端是ssl而手机端是app，这曾经让我们花了很大精力寻求答案。我们的登录流程共有两步，还没在我们这个平台上登陆过的用户，就可以通过通过同济大学的学号
以及校园网密码进行登录，登陆之后需要进行个人信息以及手机号码的绑定，绑定之后就可以用手机号来进行登录，都是为了保障我们这个平台使用人员的限制性。

### 计步功能的实现
计步服务开启放在application中，伴随整个APP的生命周期
加速传感器计步的方法
 根据加速度传感器三轴的平均值得到行走过程中的位置信息，满足走一步的条件就计一步将计步分为三个状态，准备计时、计时中、计步中
 “计时中”是在3.5秒内每隔0.7秒对步数进行一次判断，看步数是否仍然在增长，如果不在增长说明之前是无效的震动并没有走路，得到的步数不计入总步数中；
 反之则将这3.5秒内的步数加入总步数中。之后进入“计步中”状态进行持续计步，并且每隔2秒去判断一次当前步数和2秒前的步数是否相同，如果相同则说明步数不在增长，计步结束
 
 检测波峰
     * 满足四个条件
     * 1、目前的点为下降趋势
     * 2、之前的点为上升趋势
     * 3、到波峰为止，持续上升大于等于第二次
     * 4、波峰值大于1.2g，小于2g
 阈值计算
     * 通过波峰波谷的差值计算阈值
     * 记录四个值，存入tempValue[]数组中
     * 再将数组传入函数averageValue中计算阈值
检测新的步数
     * 1、传入sensor中的数据
     * 2、检测到波峰并且符合时间差以及阈值条件，则判定为一步
     * 3、符合时间差条件，但波峰值大于initialValue，将该差值纳入阈值的计算中

### 本地持久化存储
LiteOrm 是一个数据库 ORM 框架，自动化存储实体和处理关系映射，比系统自带API速度 「 快1倍」！

LiteOrm 是一个速度快、小巧却强大的 Android ORM 框架类库，速度快、体积小、性能高，开发者基本一行代码实现数据库的增删改查操作，以及实体关系的持久化和自动映射。


### Okhttp3的使用和研究
#### 同步调用
<font size=10>同步调用请求就是直接调用RealCall方法的execute()方法，同步调用的缺点就是会导致线程阻塞,界面卡顿，所以不推荐使用</font>
```java
    OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();//在builder的时候就实例化了一个dispatcher
        final Request request = new Request.Builder().get()                 //用到build的设计模式
                .url(_url).build();
    client.newCall(request).execute();
```
#### 异步调用
<font face="微软雅黑" size=5>异步调用请求就是enqueue(new AsyncCall(responseCallback))方法，将RealCall转化为AsyncCall对象，放到线程池中，进行异步处理,最后异步地执行execute()方法</font>
```java
        public static void sendOkHttpRequest(final String _url,okhttp3.Callback callback){                       //发送请求的动作
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();//在builder的时候就实例化了一个dispatcher
        final Request request = new Request.Builder().get()                 //用到build的设计模式
                .url(_url).build();
        client.newCall(request).enqueue(callback);                      
    }
```

##### InterceptorChain 拦截器链
```java
        Response result = getResponseWithInterceptorChain();                //从服务器获得返回结果，但是中间需要经过拦截器链的处理
        ......
        Response getResponseWithInterceptorChain() throws IOException {
        // Build a full stack of interceptors.
            List<Interceptor> interceptors = new ArrayList<>();
            interceptors.addAll(client.interceptors());
            interceptors.add(retryAndFollowUpInterceptor);
            interceptors.add(new BridgeInterceptor(client.cookieJar()));
            interceptors.add(new CacheInterceptor(client.internalCache()));
            interceptors.add(new ConnectInterceptor(client));
            if (!forWebSocket) {
                interceptors.addAll(client.networkInterceptors());
            }
            interceptors.add(new CallServerInterceptor(forWebSocket));

            Interceptor.Chain chain = new RealInterceptorChain(interceptors, null, null, null, 0,
            OverrideginalRequest, this, eventListener, client.connectTimeoutMillis(),
            client.readTimeoutMillis(), client.writeTimeoutMillis());

            return chain.proceed(originalRequest);
        }
        ......
        @Override public Response proceed(Request request) throws IOException {
                 return proceed(request, streamAllocation, httpCodec, connection);
        }
        ......
        public Response proceed(Request request, StreamAllocation streamAllocation, HttpCodec httpCodec,
        RealConnection connection) throws IOException {

            ......
            // Call the next interceptor in the chain.
            RealInterceptorChain next = new RealInterceptorChain(
                interceptors, streamAllocation, httpCodec, connection, index + 1, request);
            Interceptor interceptor = interceptors.get(index);
            Response response = interceptor.intercept(next);
    
            ...... 

            return response;
        }


```
<font face="微软雅黑" size=5>在proceed方法中的核心代码可以看到，proceed实际上也做了两件事：
</font>
- 创建下一个拦截链。传入index + 1使得下一个拦截器链只能从下一个拦截器开始访问
- 执行索引为index的intercept方法，并将下一个拦截器链传入该方法

#### 第一个拦截器 RetryAndFollowUpInterceptor
```java
        public final class RetryAndFollowUpInterceptor implements Interceptor {
            @Override public Response intercept(Chain chain) throws IOException{
                ......
                try {
                //执行下一个拦截器链的proceed方法
                    response = ((RealInterceptorChain) chain).proceed(request, streamAllocation, null, null);
                    releaseConnection = false;
                } catch (RouteException e)
                ......
            }
        }
```
RetryAndFollowUpInterceptor负责两部分逻辑：
- 在网络请求失败后进行重试
- 当服务器返回当前请求需要进行重定向时直接发起新的请求，并在条件允许情况下复用当前连接

BridgeInterceptor主要负责以下几部分内容：
- 设置内容长度，内容编码
- 设置gzip压缩，并在接收到内容后进行解压。省去了应用层处理数据解压的麻烦
- 添加cookie
- 设置其他报头，如User-Agent,Host,Keep-alive等。其中Keep-Alive是实现多路复用的必要步骤

CacheInterceptor的职责很明确，就是负责Cache的管理
- 当网络请求有符合要求的Cache时直接返回Cache
- 当服务器返回内容有改变时更新当前cache
- 如果当前cache失效，删除

ConnectInterceptor为当前请求找到合适的连接，可能复用已有连接也可能是重新创建的连接，返回的连接由连接池负责决定。

CallServerInterceptor负责向服务器发起真正的访问请求，并在接收到服务器返回后读取响应返回。

![](https://github.com/fangweihao123/Photo-Repo/raw/master/interceptorChain.png)

### Gson开源库的使用
Gson 是google解析Json的一个开源框架,同类的框架fastJson,JackJson等等,能够极大地提高对于网络上JSON形式数据的处理效率。其中有一个TypeToken类的，能够将数据处理成各种我们自定义的类，十分方便。




## 原质化设计(Material Design)的设计规范
Material Design,中文名:材料设计语言，是由Google推出的全新的设计语言,谷歌表示,这种设计语言旨在为手机、平板电脑、台式机和“其他平台”提供更一致、更广泛的“外观和感觉”。
![](https://github.com/fangweihao123/Photo-Repo/raw/master/giphy.gif)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/iphone-app-design-search-animation-ramotion.gif)

## 服务端的搭建和模拟

服务器环境:阿里云上的java1.9+Tomcat9.0+Mysql环境
<br>
<br>
*服务端数据库搭建*
<br>
![](https://github.com/fangweihao123/Photo-Repo/raw/master/combine_1.jpg)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/combine_2.jpg)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/vote_info.png)

## 后端环境的搭建
后端使用Springboot框架搭建，数据库使用·mysql，持久层使用hibernate，缓存使用redis，登录验证使用JWT方式将缓存配置在客户端
