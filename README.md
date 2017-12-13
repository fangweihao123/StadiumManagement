# 移动应用开发中期答辩

## 目前进度
- [x] 模拟登陆
- [x] 查看场馆
- [x] 预约场馆
- [ ] 取消预约
- [x] 展示场馆
- [ ] 推荐场馆
- [ ] 手机绑定
- [ ] 编辑个人信息
- [ ] 分享
- [ ] 定时提醒
- [ ] 计步


## 类的设计

### ButterKnife的使用


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






## 原质化设计(Material Design)的设计规范




## 服务端的搭建和模拟

服务器环境:阿里云上的java+Tomcat+Mysql环境