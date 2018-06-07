package learn.song.com.mykt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * kotlin学习
 * */
class MainActivity : AppCompatActivity() {
    var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //伴生对象
        initLearn1()
        //普通类继承 open
        initLearn2()
        //抽象类
        initLearn3()
    }

    /**抽象类*/
    private fun initLearn3() {
        tv_main_3.text = "抽象类"
        tv_main_3.setOnClickListener { tv_main_3.text = Cock().callOut(count++ % 10) }

    }

    /**普通类继承*/
    private fun initLearn2() {

        tv_main_2.text = "普通类继承"
        tv_main_2.setOnClickListener {
            var sexBird = if (count++ % 3 == 0) Bird.MALE else Bird.FEMALE
            var duck = Duck(sex = sexBird)
            tv_main_2.text = duck.getDesc("林子里")
        }

    }

    /**
     * 伴生对象
     * */
    private fun initLearn1() {
        tv_main_1.text = "伴生对象"
        var count: Int = 0
        val sexArray: Array<String> = arrayOf("公", "雄", "母", "雌")
        tv_main_1.setOnClickListener {
            var sexName: String = sexArray[count++ % 4]
            tv_main_1.text = "\"$sexName\"对应的类型时${WildAnimal.judgeSex(sexName)}"
        }

    }

    class WildAnimal(var name: String, val sex: Int = 0) {
        var sexName: String

        init {
            sexName = if (sex == 0) "公" else "母"
        }

        companion object WildAnimal {
            val MALE = 0
            val FEMALE = 1
            val UNKNOWN = -1
            fun judgeSex(sexName: String): Int {
                var sex = when (sexName) {
                    "公", "雄" -> MALE
                    "母", "雌" -> FEMALE
                    else -> UNKNOWN
                }
                return sex
            }
        }
    }

    /**普通类继承*/
    open class Bird(var name: String, val sex: Int = MALE) {
        var sexName: String

        init {
            sexName = getSexName(sex)
        }

        open protected fun getSexName(sex: Int): String {
            return if (sex == 1) "公" else "母"
        }

        fun getDesc(tag: String): String {
            return "欢迎来到${tag},这只${name}是${sexName}的"
        }

        companion object BirdStatic {
            val MALE = 0
            val FEMALE = 1
            val UNKNOWN = -1
            fun judgeSex(sexName: String): Int {
                var sex: Int = when (sexName) {
                    "公", "雄" -> MALE
                    "母", "雌" -> FEMALE
                    else -> UNKNOWN
                }
                return sex
            }

        }
    }

    /**普通类继承*/
    class Duck(name: String = "鸭子", sex: Int = Bird.MALE) : Bird(name, sex) {
        override public fun getSexName(sex: Int): String {
            return if (sex == MALE) "雄" else "雌"
        }

    }

    abstract class Chicken(name: String, sex: Int, var voice: String) : Bird(name, sex) {
        val numberArray: Array<String> = arrayOf("一", "二", "三", "四", "五", "六", "七", "八", "九", "十");
        abstract fun callOut(times: Int): String
    }

    class Cock(name: String = "鸡", sex: Int = Bird.MALE, voice: String = "喔喔喔") : Chicken(name, sex, voice) {
        override fun callOut(times: Int): String {
            var count = when {
                times <= 0 -> 0
                times >= 10 -> 9
                else -> times
            }
            return "$sexName$name${voice}叫了${numberArray[count]}声，原来它在报晓啊"
        }

    }


}
