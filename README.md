# 在哥牛逼！
# DesignAndBuild
software engineering

## README release note

[TOC]



### 3/30

- 用户数据中
	- 等级默认1级
	- vip默认1级
	- 公共课学习时长使用**秒数**表示，Int类型

## 数据
### 用户
```text
{
	Double Account：
	Int 等级(总学习时长决定)：默认1级
	Int 公共课学习时长(用秒数表示)
	Int vip（充值决定）: 默认1级
	Array 订阅课程（{id}）：
	String Description：
}
```
示例：
```json
{
	"account": 123.45,
	"level": 1,
	"learningTime": 123,
	"vip": 1,
	"courseSubscription": [
		{
			"id": 123
		},
		{
			"id": 234
		}
	],
	"description": "szsb"
}
```

### 教练
```text
{
	Int 等级（高/低）0低级（默认）1高级 2特级
	String Description
}
```
示例：
```json
{
	"level": 0,
	"description": "szsb" 
}
```

管理员
```text
null
```

### 共有的
```text
{
	Int id:
	String nickName: 
	String password: 
	Int sex: 0female 1male
	String phone:
	String email: 
	Int role: 0管理员 1教练 2用户
	Boolean cancel: 默认false
}
```
示例：
```json
{
	"id": 123456,
	"nickName": "sz",
	"password": "123456",
	"sex": 1,
	"phone": "18600000000",
	"email": "123456789@qq.com",
	"role": 2,
	"cancel": false
}
```

### 注意
> - `共有的`是每个角色共有的属性，以上角色中的示例，在真实情况中应再加上`共有的`
中的属性
> - 例如：*真正的*教练数据应该是：
> ```json
> {
> 	"id": 123456,
> 	"nickName": "sz2",
> 	"password": "1234563",
> 	"sex": 1,
> 	"phone": "18600000120",
> 	"email": "123456349@qq.com",
> 	"role": 1,
> 	"cancel": false,
> 	"level": 0,
> 	"description": "szsb" 
> }
> ```

## V1

- **登录界面** 周总
- **注册** 圣博
- **Json接口** 武煜博
- **界面** skr：
- 用户
	- 课程广场
	- 我的课程
	- **个人主页** 小c
- 教练
	- 我的课程
	- 视频管理
	- **个人主页** sz
- 管理员
	- 课程广场
	- 视频管理
	- 人员管理

### JSON接口说明

#### 数据结构与注意事项
- `Client`是`User`/`Coach`/`Administrator`的父类，包含了上面的`共有`的属性。
- `Course`是为编写`User`的代码需要而写的，本次更新(3/30)不提供针对`course`的增删改查方法。
- 因为`Client`是父类，所以一般而言，不需要在代码中创建与修改该类的实例，仅仅是会在查找数据(find方法)当作返回值，所以建议各位在使用find方法时，时刻注意***多态***的使用。
- 虽然各位可以将`Client`的实例当作一般的数据插入数据库中，但我**不建议**这样做。除了上述需要用到多态的情况，各位在写代码时应只可以用到`User`/`Coach`/`Administrator`这三个类。
- `ClientMapping`是对数据进行操作的类，包括增删改查，适用于以上三种类（和明面上不建议使用的父类）。

#### 基本用法
增：
```java
class Example {
    public void example() {
        User user = new User();
        // setter
        ClientMapping.add(user);
        // 返回值
		// ClientMapping.SUCCESS
		// ClientMapping.DUPLICATE_ID
		// ClientMapping.DUPLICATE_NICKNAME
    }
}
```

删：
```java
class Example {
	public void example() {
		int id = 123;
		ClientMapping.delete(id);
		// 返回值
		// ClientMapping.SUCCESS
		// ClientMapping.CLIENT_NOT_FOUND
	}
}
```

改：
```java
class Example {
	public void example() {
		ClientMapping.modify(user);
		// user是更改之后的实例
		// modify方法实际上是针对实例的替换，将原来的实例替换成user这个新的实例
		// 所以需要将user设置完好。
		
		// 这里我建议搭配下面的find方法使用。
		// 使用find方法找到需要修改的实例，将需要更改的属性set之后，把新的实例传入modify方法中。
		
		// 返回值
		// ClientMapping.SUCCESS
		// ClientMapping.CLIENT_NOT_FOUND
	}
}
```

查：
```java
class Example {
    // 第一种：返回Client
	// 传出的arrayList中所存储的实例，运用了多态，所以保留了子类所有的精确度，可以进行强制转换
	public void example1() {
		HashMap<String, String> map = new HashMap<>();
		// 设置查找条件
		map.put("id", "123");
		map.put("sex", "0");
		// 查找
		ArrayList<Client> clients = ClientMapping.find(map);
	}
	
	// 如果不想用上面的方法，可以选择使用一下几种方法，直接返回关于子类的arraylist，不需要强制转换
	// 分别是findUser/findCoach/findAdministrator三种方法
	// 下面是findUser的例子，其他两个用法与这个一样
	public void example2() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("id", "234");
	    map.put("sex", "1");
	    ArrayList<User> users = ClientMapping.findUser(map);
	}
	
	// 找不到就会返回空的ArrayList
}
```

------

### 整体框架说明

- 主框架`MainFrame`为主控制器，单例实现，全局唯一，其私有属性`client`为当前登录用户，`list`为管理列表，所有的`Controller`均注册其中。
- 一个页面视图或一个功能页面由一个`Controller`控制，具体页面和控制器划分由个人决定。
- `view`只负责页面的布局，组件的大小样式等，`Controller`负责为`view`里的按钮组件等添加监听器，并负责更新视图，控制视图的一切数据获取更改，一个`view`对应一个`key`值。
- 每个控制器均继承`Controller`，在`Controller`生成后，自动注册到主控制器`MainFrame`的管理列表，每个`Controller`有一私有属性`panel`对应相应的页面，均需实现`update`方法。
- 一旦当前登录用户更改，`MainFrame`会自动调用管理列表中所有`Controller`的`update`方法，在视图将跳转至某一视图时，也会先调用对应`Controller`的`update`方法。

#### 主控制器方法说明

- 获取主控制器

  ```
  MainFrame.getInstance()
  ```

- 获取当前登录用户

  ```
  MainFrame.getInstance().getClient()`
  ```

- 更改当前登录用户信息

  ```
  MainFrame.getInstance().setClient(newClient)`
  ```

  ​		其参数接收一个`Client`对象，调用此方法后，会自动调用所有`Controller`的`update`方法

- 页面跳转

  ```
  MainFrame.getInstance().goTo(ViewName)
  ```

  ​		其参数接收一个String 类型，为对应视图的key值，调用此方法后，在视图将跳转至某一视图时，也会先调用对应`Controller`的`update`方法。

#### 页面添加流程

1. 在`util`中的`config`添加key值

   ```
   String INDEX_PANEL_NAME = "index";
   String ENROLL_PANEL_NAME = "enroll";
   String FUNCTION_PANEL_NAME = "function";
   ```

    		由于每个页面的key值，在很多地方均需用到，建议写在这，方面全局统一。

2. 创建对应页面控制器继承`Controller`

       public class IndexController extends Controller {
           private IndexPanel indexPanel;
       	
       	public IndexController() {
           	super(config.INDEX_PANEL_NAME, new IndexPanel());  // key值和对应的页面
           	indexPanel = (IndexPanel) this.panel;    // 强制转换为当前控制器对应的页面类型
           }
       }

3. 在`Control`中的`ControllerFactory`注册对应`key`和`view`

   ```
   public class ControllerFactory {
       public static Controller create(String name){
           switch (name){
               case INDEX_PANEL_NAME:
                   return new IndexController();
               case ENROLL_PANEL_NAME:
                   return new EnrollController();
               case FUNCTION_PANEL_NAME:
                   return new FunctionController();
           }
           return null;
       }
   }
   ```

    		由于页面加载为按需加载，在程序运行时，发现没有该页面时，由工厂创建对应对象。

4. 在`FunctionPanel`中的`button_init`添加对应按钮

   ```
   public void button_init(int role){
       if(role == 2){
           addButton("Your Profile","userProfile", 1);
           addButton("Your Course","userCourse",2);
           addButton("Video Square","userVideoSquare", 1);
       }
       else if(role == 1){
           addButton("Your Profile","coachProfile",1);
           addButton("Your Course","coachCourse",1);
           addButton("Video Management","coachVideoManagement",1);
       }
       else if(role == 0){
           addButton("Video Square","adminVideoSquare",1);
           addButton("Video Management","adminVideoManagement",1);
           addButton("Staff Management","adminStaffManagement",1);
       }
   }
   ```

   ​		修改已有`addButton`或添加`addButton`增加`Button`,第一个参数为按钮上显示的文字，第二个参数为`view`对应的`key`值，第三个参数为按钮的类型，`1`为将按钮加到侧导，其他为将按钮加到顶导。


### 视频播放器框架说明

- 视频播放器为独立框架，集成原VLC组件于bin和lib文件夹，使用时，将bin设为test resource目录并自行导入jar包。
- 视频播放器集成进程`runnable`接口，可作为独立进程运行。
- 对外APi仅保留`VideoFrameController`，仅接受**一个*String***类型作为视频路径。
- 目前已对windows和mac系统优化，但由于VLC版本原因，可能会出现类似以下报错：

> [00007f8ad678ec10] main libvlc error: stale plugins cache: modified bin/darwin/plugins/../plugins/libtospdif_plugin.dylib
> [00007f8ad678ec10] main libvlc error: stale plugins cache: modified bin/darwin/plugins/../plugins/libddummy_plugin.dylib
> [00007f8ad678ec10] main libvlc error: stale plugins cache: modified bin/darwin/plugins/../plugins/libtaglib_plugin.dylib
> [00007f8ad678ec10] main libvlc error: stale plugins cache: modified bin/darwin/plugins/../plugins/liblibass_plugin.dylib
> [00007f8ad678ec10] main libvlc error: stale plugins cache: modified bin/darwin/plugins/../plugins/libspeex_plugin.dylib

- 暂时不影响使用，window系统调用VideoFrameController.claenCache函数即可暂时消除报错，但缓存会继续生成，并不永久解决问题。
- 如果遇到其他错误，请先检查lib和bin中文件完整性，如果错误得不到解决，自行去VLC官网下载3.0版本VLC然后将项目中同名文件夹覆盖。

### 播放器框架函数

- ***供大家使用的Long 毫秒转换为String 格式为【xx:xx:xx】 函数***
```java
    protected String getTime(Long time)
    {
        int dialSeconds = toIntExact(time/1000);
        int dialhous = dialSeconds / 3600;
        int dialMinutes = dialSeconds / 60 % 60;
        dialSeconds %= 60;
        String showMinutes = "";
        String showSeconds;
        String showText;
        if (dialSeconds < 10) {
            showSeconds = "0" + dialSeconds;
        } else {
            showSeconds = Integer.toString(dialSeconds);
        }
        if (dialMinutes < 10) {
            showMinutes = "0" + dialMinutes;
        } else {
            showMinutes = Integer.toString(dialMinutes);
        }
        if (dialhous > 0) {
            showText = dialhous + ":" + showMinutes + ":" + showSeconds;
        } else {
            showText = showMinutes + ":" + showSeconds;
        }
        return showText;
    }
```

- ***重置VLC缓存（windows版本）***
```java
    protected void cleanCache()
```

- 被动搜寻动态库和组件
```java
public void MakeVideoFrameController(String Path)
```

- 添加`Button`监听器
```java
    private void buildListener()
```

- 添加视频水印
```java
    private void buildLogo()
```

- 添加鼠标监听器
```java
    private void videoSurface()
```

- 视频进度条独立后台进程
```java
    private void processBar()
```




#### 视频播放器调用步骤

1.首先在*调用者的类*中添加同步锁实例。
   ```java
    static final Object lock = new Object();
   ```
   
2.开辟新进程实例并添加视频路径。
```java
public static void main(String[] args)
    {
            VideoFrameController test = new VideoFrameController("data/video/1.mp4"); // 路径
            Thread VIDEO = new Thread(test);
            VIDEO.start();
            synchronized (lock) // try catch 块可以放在很外面，但必须包括lock.wait()
            {
                try
                {
                    lock.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                System.out.println("Progress: " + test.getVideoProgress());
                System.out.println("Learningtime :" + test.getLearningTime());
            }
    }
```

3.__注意__ : 由于`Progress`和`Leaningtime`必须在视频播放完成后才能得到，所以 `synchronized` 函数块以下的部分都必须在视频播放器结束后执行，如果并不适合，请将创建视频播放器的代码块（创建`controller`+调用`Progress`/`Learningtime`函数）打包成父进程于其他代码并行，并用`Thread.currentThread()`得到主进程并将创建的进程join到主进程下。

#### 播放器具体功能及快捷键

- GUI按钮不需要解释了

##### *快捷键*

- 键盘*control*

	- 方向键上 ：音量大
	- 方向键下 ：音量小
	- 方向键左 ：向左回退1s
	- 方向键右 ：向右快进1s
	- F1 ： 全屏
	- 空格 ：暂停

- 鼠标*control*
	- 点击进度条即可跳转到该位置
	- 关闭窗口默认视频播放完毕。

#### 获取Learningtime和Progress

- ***只要在播放器释放同步锁后***，随时可以调用 `VideoFrameController.getVideoProgress()`获取视频关闭前**最后**进度。
```   java
public int getVideoProgress() // 返回值为毫秒
    {
        return progress;
    }
```
或 `VideoFrameController.getLearningTime()`获取**总共视频播放时长**。
```java
    public int getLearningTime() // 返回值为毫秒
    {
        int ans = progress - skipping_time;
        if (ans < 0)
            return 0;
        return ans;
    }
```
***不足0s按照0s计算，若学习时长超过视频长度，按照真实学习时长计算***

