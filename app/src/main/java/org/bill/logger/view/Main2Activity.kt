package org.bill.logger.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.bill.logger.R

class Main2Activity : AppCompatActivity() {
    val TAG = "fmb"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

    }

    override fun onResume() {
        super.onResume()

        foo()
    }

    fun foo()
    {
        kotlin.run{
            listOf(1,2,3,4,5).forEach lst@{
                if (it == 3)
                {
                    println("it=="+it)
                    return@run
                }
                print("$it ,")
            }
        }

        println("this point is unreachable")
    }

    fun hasPrefix(x:Any) = when(x)
    {
        is String -> x.startsWith("prefix")
        else -> false
    }

    private fun getx(x:Int):String
    {
        when(x)
        {
            1,3 -> return "A1"
            2 -> return "B2"
            else -> {
                return "unknown"
            }
        }

    }

    private fun decimalDigitValue(c:Char):Int
    {
        Log.i(TAG,"$c")
        if (c !in '0'..'9')
        {
            throw IllegalArgumentException("$TAG Out of range..")
        }
        return c.toInt()
    }


    /**
     * 求和
     */
    fun sum(a:Int,b:Int): Int
    {
        return a + b
    }


}

