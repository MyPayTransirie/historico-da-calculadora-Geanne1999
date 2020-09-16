package br.uea.mypay.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_tela_historico.*
import org.json.JSONObject

class TelaHistorico : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_historico)

        // concatenando o resultado recebido da outra activity com o ; como solicitado
        /*val hist= intent.getStringExtra("hist")
        Log.i("hist","PEGANDO INFO - $hist")
        val aux = hist?.split(";")
        val histFinal= arrayListOf<String>()
        for(i in 0 until aux!!.size){ // loop para adicionar todas antes do fim do tamanho da lista
            //adicionando item por item para exibir no textview
            histFinal.add(aux.get(i))
        }
        val expressoes = histFinal.joinToString("\n\n")
        txtHistorico.text=expressoes
        */
        // exibição no formato json das infos da tela anterior
        val jsonExpressao =  intent.getStringExtra("json").toString() // Obtendo o JSON como uma String
        Log.i("json","EXPRESSAO EM JSON - $jsonExpressao")
        val json = JSONObject(jsonExpressao) // passando a string para o formato
        val listaExpressoesHist = json.getJSONArray("historico") // array com as expressões
        val respExp = arrayListOf<String>()
        for(i in 0 until listaExpressoesHist.length()){ // loop para adicionar todas antes do fim do tamanho da lista
            //adicionando item por item para exibir no textview
            respExp.add(listaExpressoesHist.getString(i))
        }
        //utilizando o joinToString para passar uma resposta ao textView separada por linha (cada expressao)
        txtHistorico.text = respExp.joinToString("\n\n")

        //obtendo os dados por meio de um array
        //val array = intent.getStringArrayExtra("array")
        //colocando os dados no textView
        //txtHistorico.text = array?.joinToString("\n\n").toString()

        //utilizando o parcelable para obter dados
        //val parcelable = intent.getParcelableExtra<HistoricoExpressoes>("parcelable")
        //Apresentando as expressões na tela
        //if (parcelable != null) {
            //txtHistorico.text = parcelable.historicoExp.joinToString("\n\n")
        //}

    }
}