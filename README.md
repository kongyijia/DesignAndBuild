# 在哥牛逼！
# DesignAndBuild
software engineering

## 数据
### 用户
```text
{
	Double Account：
	Int 等级(总学习时长决定)：
	Time 公共课学习时长"HH:mm:ss"
	Int vip（充值决定）: 
	Array 订阅课程（{id}）：
	String Description：
}
```
示例：
```json
{
	"account": 123.45,
	"level": 1,
	"learningTime": "10:02:00",
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
	"sex": "1",
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
> 	"sex": "1",
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


