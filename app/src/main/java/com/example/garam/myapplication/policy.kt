package com.example.garam.myapplication

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class policy : AppCompatActivity() {

    var lists = arrayListOf<polList>()
    private var backKeyPressedTime: Long = 0

    @SuppressLint("ResourceType")
    private fun run(){
        for (k in 1 .. 9) {
            val xml: Document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse("http://api.korea.go.kr/openapi/svc/list?lrgAstCd=&mdmAstCd=&smallAstCd=&jrsdOrgCd=&srhQuery=%EB%85%B8%EC%88%99&pageSize=100&format=xml&serviceKey=h0iRn6zHq237rdbo2KUSy3dz2Nmfech6qVvB0bNCefPcU0uQDmzvu34QHkaeJYqVXk17GTbA4cYcC4ayG9EREg%3D%3D&pageIndex=$k")
            xml.documentElement.normalize()
            var policyList: String? = null
            var polName: String? = null
            var polUrl: String? = null
            val list = xml.getElementsByTagName("svc")

            for (i in 0 until list.length) {
                var n: Node = list.item(i)
                if (n.nodeType == Node.ELEMENT_NODE) {
                    val elem = n as Element

                    val map = mutableMapOf<String, String>()
                    for (j in 0 until elem.attributes.length - 1) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            map.putIfAbsent(
                                elem.attributes.item(j).nodeName,
                                elem.attributes.item(j).nodeValue
                            )
                        }
                    }
                    if ("${elem.getElementsByTagName("jrsdDptAllNm")
                            .item(0).textContent.commonPrefixWith("인천")}" == "인천"
                    ) {
                        Log.e("i 값", "=======${i + 1}=========")
                        Log.e(
                            "1. 서비스 이름 ",
                            "${elem.getElementsByTagName("svcNm").item(0).textContent}"
                        )
                        Log.e(
                            "2. 기관 이름 ",
                            "${elem.getElementsByTagName("jrsdDptAllNm").item(0).textContent}"
                        )
                        Log.e(
                            "3. URL 주소 ",
                            "${elem.getElementsByTagName("svcInfoUrl").item(0).textContent}"
                        )
                        Log.e(
                            "4. 지원 내용 ",
                            "${elem.getElementsByTagName("sportFr").item(0).textContent}"
                        )
                        policyList = elem.getElementsByTagName("sportFr").item(0).textContent
                        polName = elem.getElementsByTagName("jrsdDptAllNm").item(0).textContent
                        polUrl = elem.getElementsByTagName("svcInfoUrl").item(0).textContent
                        lists.add(polList(policyList, polName, polUrl))

                    }
                }
            }
        }
    }
    lateinit var webView: WebView
    lateinit var recycler : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)
        val thread = Thread{
            run()
        }
        thread.start()
        webView = findViewById<WebView>(R.id.WebView)
        webView.webViewClient = WebViewClient()
        var mWebsetting = webView.settings
        mWebsetting.javaScriptEnabled = true
        mWebsetting.setSupportMultipleWindows(false)
        mWebsetting.javaScriptCanOpenWindowsAutomatically = false
        mWebsetting.useWideViewPort = true
        mWebsetting.loadWithOverviewMode = true
        mWebsetting.setSupportZoom(true)
        mWebsetting.builtInZoomControls = true
        mWebsetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        mWebsetting.cacheMode = WebSettings.LOAD_NO_CACHE
        recycler = findViewById<RecyclerView>(R.id.recyclerView)

        val line = findViewById<LinearLayout>(R.id.linearLayout)
        val refresh = findViewById<Button>(R.id.refresh)
        refresh.setOnClickListener {
8
            if (!thread.isAlive) {
                line.visibility = View.INVISIBLE
                val test = StdRecyclerAdapter(lists, this) { polList ->
                    recycler.visibility = View.INVISIBLE
                    webView.visibility = View.VISIBLE
                    webView.loadUrl(polList.url)
                }
                recycler.adapter = test
                test.notifyDataSetChanged()
                recycler.layoutManager = LinearLayoutManager(this)
                recycler.setHasFixedSize(true)
            } else {
                Toast.makeText(this, "잠시 뒤에 다시 눌러주세요",Toast.LENGTH_LONG).show()
            }
        }

        }
      override fun onBackPressed() {
          lateinit var toast: Toast
          if (System.currentTimeMillis() >= backKeyPressedTime + 1500) {
               backKeyPressedTime = System.currentTimeMillis()
               toast = Toast.makeText(this, "종료 하려면 한번 더 누르세요.", Toast.LENGTH_LONG)
               toast.show()
               webView.visibility = View.INVISIBLE
               recycler.visibility = View.VISIBLE
               return
           }
           if (System.currentTimeMillis() < backKeyPressedTime + 1500) {
               finish()
           }

    }
}
