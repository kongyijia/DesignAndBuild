# DesignAndBuild
software engineering

用户
{
	Double Account：
	Int 等级(总学习时长决定)：
	Time 公共课学习时长
	Int vip（充值决定）: 
	Array 订阅课程（{id，time}）：
	String Description：
}

教练
{
	Int 等级（高/低）0低级（默认）1高级 2特技 3武煜博特傻逼级别
	String Description
}

管理员
{

}

共有
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

V1
登录界面*1 周总
注册*1 圣博
Json接口 武煜博
界面skr：
用户
	课程广场
	我的课程
	个人主页 小c
教练
	我的课程
	视频管理
	个人主页 sz
管理员
	课程广场
	视频管理
	人员管理

