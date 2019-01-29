package darom.bmicalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.prefs.PreferencesFactory

class MainActivity : AppCompatActivity() {

    //데이터 저장하기
    private fun saveData(height: Int, weight: Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this) //프리퍼런스 매니저를 사용해 프리퍼런스 객체를 얻습니다.
        val editor = pref.edit() // 프리퍼런스 객체의 에디터 객체를 얻습니다. 이 객체를 사용해 프리퍼런스에 데이터를 담을 수 있습니다.

        editor.putInt("KEY_HEIGHT", height) // 에디터 객체에 put[데이터 타입] 형식의 메서드를 사용하여 키와 값 형태의 쌍으로 저장을 합니다.
            //put 메서드는 기본 타입은 모두 지원합니다.
            .putInt("KEY_WEIGHT", weight)
            .apply() //마지막으로 설정한 내용을 반영합니다.
    }
    //데이터 불러오기
    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this) //프리퍼런스 객체를 얻습니다.
        val height = pref.getInt("KEY_HEIGHT", 0)   //getInt() 메서드로 키와 몸무게에 저장된 값을 불러옵니다. 저장된 값이 없을 때 기본값 0을 리턴합니다.
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){ //저장된 값이 있다면 저장된 값을 표시합니다.
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이전에 입력한 값을 읽어오기
        loadData()

        //결과 버튼이 클릭되면 할 일을 작성하는 부분
        resultButton.setOnClickListener {
            /*
            // 안드로이드 액티비티를 전활할 때마다 항상 인텐트 객체를 생성하고 startActivity() 메서드를 호출합니다.
        val intent = Intent(this, ResultActivity::class.java)
            //인텐트는 데이터를 담아서 다른 액티비티에 전달하는 역할도 합니다. 다음은 인텐트에 데이터를 담는 코드입니다.
            //입력한 키와 몸무게를 문자열로 변경하여 인텐트에 담고 있습니다.
            intent.putExtra("weight", weightEditText.text.toString())
            intent.putExtra("height", heightEditText.text.toString())
        startActivity(intent)
            */

            //마지막에 입력한 내용 저장
            //결과 버튼을 클릭할 때 화면을 전화하기 전에 saveData() 메서드를 호출하여 프리퍼런스에 키와 몸무게 값을 저장해봅니다.
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())

            //이 부분을 Anko 라이브러리를 사용하면 다음과 같이 작성할 수 있습니다.
            // Anko를 적용한 데이터 담기 : 키와 몸무게 데이터 전달
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }
}
