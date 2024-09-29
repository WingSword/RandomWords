package org.walks
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

val dataMap = mutableMapOf<String, String>()

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
fun main() {
    val name = "Kotlin"
    //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
    // 查看 IntelliJ IDEA 建议如何修正。
    println("Hello, " + name + "!")

    embeddedServer(Netty, port = 8080) {
        routing {
            // 这里将是处理特定路由的逻辑
            post("/getRandomWord") {
                val parameters = call.receiveParameters()
                val key = parameters["key"]
                val password = parameters["password"]

                if (key!= null && password!= null) {
                    // 获取到了key和password参数将它们组合成唯一标识
                    val uniqueIdentifier = "$key:$password"
                    if (dataMap.containsKey(uniqueIdentifier)) {
                        handleExistingIdentifier(call,uniqueIdentifier)
                    } else {
                        // 如果不存在，生成随机码，这里简单生成一个四位数字的随机码示例
                        handleNewIdentifier(call = call,uniqueIdentifier)
                    }
                } else {
                    if (key == null) {
                        call.respondText("缺少key参数", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                    if (password == null) {
                        call.respondText("缺少password参数", status = HttpStatusCode.BadRequest)
                        return@post
                    }
                }
            }
        }
    }.start(wait = true)
}

private suspend fun handleExistingIdentifier(call: ApplicationCall, uniqueIdentifier: String) {
    val randomCode = dataMap[uniqueIdentifier] !!
    call.respondText(randomCode)
}

private suspend fun handleNewIdentifier(call: ApplicationCall, uniqueIdentifier: String) {
    try {
        // 如果不存在，生成随机码，这里生成一个四位纯数字随机码
        val randomCode = (1000..9999).random(Random.Default).toString()
        dataMap[uniqueIdentifier] = randomCode
        call.respondText(randomCode)
    } catch (e: Exception) {
        call.respondText("随机码生成失败", status = HttpStatusCode.InternalServerError)
    }
}

fun addWordsToMap(map: MutableMap<String, String>) {
    // 食物类
    map["草莓"] = "樱桃"
    map["芒果"] = "木瓜"
    map["榴莲"] = "菠萝蜜"
    map["柿子"] = "西红柿"
    map["黄瓜"] = "青瓜"
    map["茄子"] = "土豆"
    map["冬瓜"] = "南瓜"
    map["白菜"] = "青菜"
    map["洋葱"] = "大蒜"
    map["生姜"] = "辣椒"
    map["油条"] = "麻花"
    map["包子"] = "馒头"
    map["汤圆"] = "元宵"
    map["粽子"] = "月饼"
    map["巧克力"] = "糖果"
    map["饼干"] = "薯片"
    map["酸奶"] = "牛奶布丁"
    map["果汁"] = "汽水"
    map["白酒"] = "啤酒"
    map["红酒"] = "葡萄酒"
    // 动物类
    map["大象"] = "犀牛"
    map["袋鼠"] = "树袋熊"
    map["骆驼"] = "羊驼"
    map["企鹅"] = "北极熊"
    map["海豚"] = "海狮"
    map["鲨鱼"] = "鲸鱼"
    map["蜈蚣"] = "蝎子"
    map["蜘蛛"] = "蚂蚁"
    map["蝴蝶"] = "飞蛾"
    map["蜜蜂"] = "马蜂"
    map["麻雀"] = "乌鸦"
    map["鸽子"] = "鹦鹉"
    map["金鱼"] = "锦鲤"
    map["乌龟"] = "鳖"
    map["蛇"] = "蜥蜴"
    map["狼狗"] = "哈士奇"
    map["花猫"] = "白猫"
    map["兔子"] = "龙猫"
    map["松鼠"] = "花栗鼠"
    map["鸭子"] = "鹅"
    // 生活用品类
    map["牙刷"] = "梳子"
    map["毛巾"] = "手帕"
    map["杯子"] = "碗"
    map["盘子"] = "碟子"
    map["勺子"] = "筷子"
    map["菜刀"] = "水果刀"
    map["剪刀"] = "裁纸刀"
    map["雨伞"] = "太阳伞"
    map["帽子"] = "围巾"
    map["手套"] = "袜子"
    map["皮带"] = "领带"
    map["手表"] = "怀表"
    map["闹钟"] = "手表"
    map["台灯"] = "落地灯"
    map["插座"] = "插线板"
    map["吹风机"] = "电风扇"
    map["熨斗"] = "挂烫机"
    map["洗衣机"] = "烘干机"
    map["冰箱"] = "冰柜"
    map["微波炉"] = "烤箱"
    // 自然类
    map["山峰"] = "山丘"
    map["瀑布"] = "溪流"
    map["沙漠"] = "戈壁"
    map["草原"] = "牧场"
    map["森林"] = "丛林"
    map["花朵"] = "花蕊"
    map["树叶"] = "树枝"
    map["树根"] = "树干"
    map["石头"] = "矿石"
    map["沙子"] = "砂砾"
    map["泥土"] = "壤土"
    map["雨水"] = "露水"
    map["雪花"] = "冰晶"
    map["冰雹"] = "霰"
    map["闪电"] = "雷电"
    map["彩虹"] = "极光"
    map["月亮"] = "月牙"
    map["星星"] = "星座"
    map["太阳"] = "夕阳"
    map["白云"] = "彩云"
    // 娱乐类
    map["唱歌"] = "唱戏"
    map["跳舞"] = "体操"
    map["篮球"] = "排球"
    map["足球"] = "橄榄球"
    map["乒乓球"] = "网球"
    map["游泳"] = "潜水"
    map["爬山"] = "徒步"
    map["钓鱼"] = "打猎"
    map["摄影"] = "摄像"
    map["画画"] = "涂鸦"
    map["看书"] = "看杂志"
    map["听音乐"] = "听广播"
    map["玩游戏"] = "玩玩具"
    map["看电影"] = "看话剧"
    map["旅游"] = "出差"
    map["逛街"] = "逛超市"
    map["打牌"] = "下棋"
    map["唱歌比赛"] = "舞蹈比赛"
    map["游乐园"] = "动物园"
    map["博物馆"] = "科技馆"
    // 学科类
    map["语文"] = "数学"
    map["英语"] = "语文"
    map["物理"] = "化学"
    map["生物"] = "地理"
    map["历史"] = "政治"
    map["音乐"] = "美术"
    map["体育"] = "健康"
    map["科学"] = "技术"
    map["计算机"] = "信息技术"
    map["心理学"] = "社会学"
    map["经济学"] = "管理学"
    map["法学"] = "政治学"
    map["文学"] = "语言学"
    map["数学竞赛"] = "物理竞赛"
    map["英语考试"] = "语文考试"
    map["历史故事"] = "政治事件"
    map["音乐作品"] = "美术作品"
    map["体育项目"] = "运动赛事"
    map["科学实验"] = "技术发明"
    map["计算机软件"] = "信息技术设备"
    // 职业类
    map["医生"] = "护士"
    map["教师"] = "教授"
    map["警察"] = "消防员"
    map["律师"] = "法官"
    map["工人"] = "农民"
    map["演员"] = "歌手"
    map["作家"] = "画家"
    map["记者"] = "编辑"
    map["程序员"] = "工程师"
    map["设计师"] = "建筑师"
    map["厨师"] = "服务员"
    map["司机"] = "快递员"
    map["售货员"] = "收银员"
    map["公务员"] = "事业单位人员"
    map["企业家"] = "商人"
    map["银行职员"] = "保险代理人"
    map["导游"] = "翻译"
    map["摄影师"] = "摄像师"
    map["美容师"] = "美发师"
    map["健身教练"] = "瑜伽教练"
    // 交通工具类
    map["汽车"] = "火车"
    map["公交车"] = "地铁"
    map["自行车"] = "电动车"
    map["飞机"] = "直升机"
    map["轮船"] = "游艇"
    map["摩托车"] = "三轮车"
    map["出租车"] = "网约车"
    map["校车"] = "班车"
    map["救护车"] = "消防车"
    map["警车"] = "军车"
    map["跑车"] = "轿车"
    map["卡车"] = "货车"
    map["自行车道"] = "人行道"
    map["高速公路"] = "国道"
    map["铁路"] = "轨道"
    map["火车站"] = "汽车站"
    map["机场"] = "航站楼"
    map["酒店"] = "宾馆"
    map["餐厅"] = "饭店"
    map["咖啡店"] = "茶馆"
    map["工厂"] = "车间"
    map["仓库"] = "货栈"
    // 建筑类
    map["高楼"] = "大厦"
    map["别墅"] = "洋房"
    map["公寓"] = "宿舍"
    map["商场"] = "超市"
    map["学校"] = "幼儿园"
    map["医院"] = "诊所"
    map["图书馆"] = "书店"
    map["电影院"] = "剧院"
    map["博物馆"] = "展览馆"
    map["体育馆"] = "体育场"
    map["公园"] = "花园"
    map["广场"] = "街道"
    map["桥梁"] = "隧道"
    map["火车站"] = "汽车站"
    map["机场"] = "航站楼"
    map["酒店"] = "宾馆"
    map["餐厅"] = "饭店"
    map["咖啡店"] = "茶馆"
    map["工厂"] = "车间"
    map["仓库"] = "货栈"
    // 其他类
    map["红色"] = "粉色"
    map["蓝色"] = "紫色"
    map["圆形"] = "方形"
    map["三角形"] = "菱形"
    map["直线"] = "曲线"
    map["正数"] = "负数"
    map["奇数"] = "偶数"
    map["加法"] = "减法"
    map["乘法"] = "除法"
    map["主语"] = "谓语"
    map["名词"] = "动词"
    map["形容词"] = "副词"
    map["开心"] = "高兴"
    map["难过"] = "伤心"
    map["愤怒"] = "生气"
    map["紧张"] = "焦虑"
    map["安静"] = "宁静"
    map["热闹"] = "喧闹"
    map["美丽"] = "漂亮"
    map["帅气"] = "英俊"
}

