package br.uea.mypay.calculadora

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var expressao: String = "0" // Expressão atual
    val historico = arrayListOf<String>() //Lista de expressões
    val json = JSONObject("{ 'historico': [] }") //Empacotando o json
    var arrayHistorico: Array<String> = arrayOf() // Array<String> com o histórico

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val expressoes: String

        buttonC.setOnClickListener() {
            expressao="0"
            txtexpressao.text=expressao
        }
        // criando a expressão e colocando a mesma nos formatos do histórico
        buttonIg.setOnClickListener() {
            if(expressao.last().isDigit()){
                txtexpressao.text= eval(expressao).toString()
                val resp= "$expressao = ${txtexpressao.text.toString()}"

                historico.add(resp) // Salvando a reposta num ArrayList<String>

                json.accumulate("historico", resp) //Salvando a resposta num array de JSON

                arrayHistorico = arrayHistorico.plusElement(resp) // Salvando a resposta num array
            }
            else {
                val myToast = Toast.makeText(this, "Erro na expressão!", Toast.LENGTH_SHORT).show()
            }
        }

        btnHistorico.setOnClickListener() {
            val histExp = HistoricoExpressoes(historico) // Variável que implementa Parcelable

            val intent = Intent(applicationContext,TelaHistorico::class.java)

            //separando a lista por ; e enviando para a outra tela
            intent.putExtra("hist", historico.joinToString(";"))

            //JSON como uma string para a tela histórico
            intent.putExtra("json",json.toString())

            //Array<String> para a tela histórico
            intent.putExtra("array", arrayHistorico)

            //Parcelable enviado para a tela histórico
            intent.putExtra("parcelable", histExp)

            startActivity(intent)
        }


    }

    // Função que adiciona a operação selecionada na expressão
    fun onClickOperacoes(v: View){
        if(expressao.last().isDigit()){
            var beta: Button = findViewById(v.id)
            expressao += beta.text
            txtexpressao.text = expressao
        }
        else{
            Toast.makeText(this, "Erro na expressão!", Toast.LENGTH_SHORT).show()
        }
    }

    // função que adiciona o digíto no final da expressão no click
    fun onClick(v: View) {
        val beta: Button = this.findViewById(v.id)
        if(expressao == "0"){
            expressao = beta.text.toString()
            txtexpressao.text = expressao
        }
        else{
            expressao += beta.text
            txtexpressao.text = expressao
        }
    }

    // Função que calcula o resultado das expressões
    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm() // addition
                    else if (eat('-'.toInt())) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) { // functions
                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x =
                        if (func == "sqrt") Math.sqrt(x) else if (func == "sin") Math.sin(
                            Math.toRadians(
                                x
                            )
                        ) else if (func == "cos") Math.cos(
                            Math.toRadians(x)
                        ) else if (func == "tan") Math.tan(Math.toRadians(x)) else throw RuntimeException(
                            "Unknown function: $func"
                        )
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
        }.parse()
    }

}

