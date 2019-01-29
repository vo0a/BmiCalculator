package darom.bmicalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    //비만도를 계산한 후에 결과를 표시하도록 코드를 작성.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //전달받은 키와 몸무게
        val height = intent.getStringExtra("height").toInt()
        val weight = intent.getStringExtra("weight").toInt()

        //BMI 계산 : 키를 100으로 나눈 제곱을 몸무게에서 나누면 BMI 값이 나옵니다.
        val bmi = weight / Math.pow(height / 100.0, 2.0)

        //결과 표시
        when {
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        //이미지 표시 : BMI 값에 따라서 다른 이미지가 나오도록 구간마다 이미지를 교체해줍니다.
        when {
            bmi >= 23 ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >= 18.5 ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_satisfied_black_24dp)
            else ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }

        //토스트 메시지는 하단에 잠깐 보였다 사라지는 메시지 입니다.
        //Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
        toast("$bmi") // bmi 값이 Double형이므로 $기호를 사용하여 문자열 템플릿에 적용했습니다.
    }
}
