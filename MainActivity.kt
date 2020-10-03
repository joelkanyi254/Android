package com.kanyideveloper.barcodedetection
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.SparseArray
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val imageview = ImageView(findViewById(R.id.imgview))

        val myBitmap: Bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.puppy)

        val barcodeDetector : BarcodeDetector = BarcodeDetector.Builder(applicationContext)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        if (!barcodeDetector.isOperational){
            txtContent.text = "Check your internet connection"
            return
        }

        button.setOnClickListener {

            imgview.setImageBitmap(myBitmap)

            val frame: Frame = Frame.Builder().setBitmap(myBitmap).build()
            val barcodes: SparseArray<Barcode> = barcodeDetector.detect(frame)

            val thisCode : Barcode = barcodes.valueAt(0)
            txtContent.text  = thisCode.rawValue
        }
    }

}

//Barcode.DATA_MATRIX |