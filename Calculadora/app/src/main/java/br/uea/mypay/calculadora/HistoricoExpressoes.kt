package br.uea.mypay.calculadora

import android.os.Parcelable
import android.os.Parcel
import kotlinx.android.parcel.Parcelize


class HistoricoExpressoes(var historicoExp: ArrayList<String>) : Parcelable {
    constructor(parcel: Parcel) : this(
        arrayListOf<String>().apply {
            parcel.readList(this, String::class.java.classLoader)
        }
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        arrayListOf<String>().apply {
            p0?.writeList(historicoExp)
        }
    }

    companion object CREATOR : Parcelable.Creator<HistoricoExpressoes> {
        override fun createFromParcel(parcel: Parcel): HistoricoExpressoes {
            return HistoricoExpressoes(parcel)
        }

        override fun newArray(size: Int): Array<HistoricoExpressoes?> {
            return arrayOfNulls(size)
        }
    }
}