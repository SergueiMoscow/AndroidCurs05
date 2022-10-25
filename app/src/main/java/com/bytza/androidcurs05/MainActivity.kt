package com.bytza.androidcurs05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var descrip = "Совершите действие с Activity (поворот экрана или паузу)"

/*
    var verseComplete=arrayOf(
        "У лукоморья дуб зелёный.",
        "Есть Интернет на дубе том",
        "Там виснет в \"аське\" кот учёный,",
        "Отбросив песни на потом",
        "Там на неведаных дорожках",
        "Отлично ловит \"Мегафон\".",
        "Там в бочке с медом \"Старый мельник\".",
        "По морю мчит сам князь Гвидон.",
        "Царевна СМС всем пишет.",
        "А серый волк свой плеер ищет.",
        "Там царь Кощей на сайте чахнет.",
        "Там чудный дух, там \"Ролтон\" пахнет",
    )
*/
    var verseComplete = arrayOf(
    "У лукоморья дуб зелёный;",
    "Златая цепь на дубе том:",
    "И днём и ночью кот учёный",
    "Всё ходит по цепи кругом;",
    "Идёт направо — песнь заводит,",
    "Налево — сказку говорит.",
    "Там чудеса: там леший бродит,",
    "Русалка на ветвях сидит;",
    "Там на неведомых дорожках",
    "Следы невиданных зверей;",
    "Избушка там на курьих ножках",
    "Стоит без окон, без дверей;",
    "Там лес и дол видений полны;",
    "Там о заре прихлынут волны",
    "На брег песчаный и пустой,",
    "И тридцать витязей прекрасных",
    "Чредой из вод выходят ясных,",
    "И с ними дядька их морской;",
    "Там королевич мимоходом",
    "Пленяет грозного царя;",
    "Там в облаках перед народом",
    "Через леса, через моря",
    "Колдун несёт богатыря;",
    "В темнице там царевна тужит,",
    "А бурый волк ей верно служит;",
    "Там ступа с Бабою Ягой",
    "Идёт, бредёт сама собой,",
    "Там царь Кащей над златом чахнет;",
    "Там русский дух… там Русью пахнет!",
    "И там я был, и мёд я пил;",
    "У моря видел дуб зелёный;",
    "Под ним сидел, и кот учёный",
    "Свои мне сказки говорил.",
    )

    var nextVerseLine: Int = 0
    private lateinit var txtDescrip: TextView
    private lateinit var txtVerse: TextView
    private lateinit var txtMethod: TextView
    private lateinit var btnClose: Button
    private lateinit var webView: WebView
    var count = 0

    var LOG_METHOD = "TESTMETHOD"
    var LOG_VERSE = "VERSE"
    var HTML_CONTENT = "htmlContent"
    var LINE_COUNTER = "lineCounter"
    var htmlOutput: String = "<html><style>html,body{height:100%;}body{vertical-align:top;}</style><body>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        if (savedInstanceState != null) {
            htmlOutput = savedInstanceState?.getString(HTML_CONTENT) ?: ""
            nextVerseLine = savedInstanceState?.getInt(LINE_COUNTER) ?: 0
        }
        showVerse("onCreate")

        btnClose.setOnClickListener() {
            finish()
        }

    }

    fun showVerse(action:String) {
        var versePart = ""
//        var html = "<html><body>"
        Log.d(LOG_METHOD, action)
        if (nextVerseLine >= verseComplete.count()) nextVerseLine = 0
        Log.i(LOG_VERSE, verseComplete[nextVerseLine] + " /" + action)
        for (i in 0..nextVerseLine) {
            versePart += (if (versePart === "") "" else "\n") + verseComplete[i] + " /action"  + getAssets()
//            html += "<p> ${verseComplete[i]} <sub><small>$action </small></sub></p>"
        }
        htmlOutput +="<p> ${verseComplete[nextVerseLine]} <sub><small>$action </small></sub></p>"
        nextVerseLine = (if (nextVerseLine >= versePart.count()-1) 0 else nextVerseLine + 1)
        txtVerse.setText(versePart)
        txtMethod.setText(action)
        webView.loadDataWithBaseURL(null, htmlOutput, "text/html","utf-8", null)
    }

    private fun saveInstance(outState: Bundle) {
        outState.putString(HTML_CONTENT, htmlOutput)
        outState.putInt(LINE_COUNTER, nextVerseLine)
    }
    private fun initViews() {
        txtDescrip = findViewById(R.id.txtDescrip)
        txtVerse = findViewById(R.id.txtVerse)
        txtMethod = findViewById(R.id.txtMethod)
        btnClose = findViewById(R.id.btnClose)
        webView = findViewById(R.id.webView)
        txtDescrip.setText(descrip)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_METHOD, "onSavedInstanceState")
        outState.putString(HTML_CONTENT, htmlOutput)
        outState.putInt(LINE_COUNTER, nextVerseLine)
        saveInstance(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(LOG_METHOD, "onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        showVerse("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        showVerse("onRestart")
    }

    override fun onPause() {
        super.onPause()
        showVerse("onPause")
    }

    override fun onStop() {
        super.onStop()
        showVerse("onStop")
    }

    override fun onResume() {
        super.onResume()
        showVerse("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        showVerse("onDestroy")

    }

}
