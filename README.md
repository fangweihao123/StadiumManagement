# 移动应用开发中期答辩
&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;1552662 方炜豪&emsp;&emsp;&emsp;&emsp;&emsp;1552663 田雍恺
## 目前进度
- [x] 模拟登陆预约场馆
- [x] 查看场馆
- [x] 
- [ ] 取消预约
- [x] 展示场馆
- [ ] 推荐场馆
- [ ] 手机绑定
- [ ] 编辑个人信息
- [ ] 分享
- [ ] 定时提醒
- [ ] 计步


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


### Okhttp3的使用和研究
```java
        public static void sendOkHttpRequest(final String _url,okhttp3.Callback callback){                       //发送请求的动作
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();//在builder的时候就实例化了一个dispatcher
        final Request request = new Request.Builder().get()                 //用到build的设计模式
                .url(_url).build();
        client.newCall(request).enqueue(callback);                      
    }
```






## 原质化设计(Material Design)的设计规范
Material Design,中文名:材料设计语言，是由Google推出的全新的设计语言,谷歌表示,这种设计语言旨在为手机、平板电脑、台式机和“其他平台”提供更一致、更广泛的“外观和感觉”。
![](https://github.com/fangweihao123/Photo-Repo/raw/master/giphy.gif)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/iphone-app-design-search-animation-ramotion.gif)

## 服务端的搭建和模拟

服务器环境:阿里云上的java1.9+Tomcat9.0+Mysql环境
<br>
<br>
<br>
*服务端数据库搭建*
<br>
![](https://github.com/fangweihao123/Photo-Repo/raw/master/combine_1.jpg)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/combine_2.jpg)
![](https://github.com/fangweihao123/Photo-Repo/raw/master/vote_info.png)