# 在哥牛逼！
# DesignAndBuild
software engineering

## README release note
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
    }
}
```

删：
```java
class Example {
	public void example() {
		int id = 123;
		ClientMapping.delete(id);
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
}
```