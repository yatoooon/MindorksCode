package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_modal_bottom_sheet.*
import kotlinx.android.synthetic.main.layout_persistent_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // handle onSlide
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(this@BottomSheetActivity, "STATE_COLLAPSED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(this@BottomSheetActivity, "STATE_EXPANDED", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(this@BottomSheetActivity, "STATE_DRAGGING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(this@BottomSheetActivity, "STATE_SETTLING", Toast.LENGTH_SHORT).show()
                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(this@BottomSheetActivity, "STATE_HIDDEN", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(this@BottomSheetActivity, "OTHER_STATE", Toast.LENGTH_SHORT).show()
                }
            }
        })

        btnBottomSheetPersistent.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            else
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        btnBottomSheetModal.setOnClickListener {
            CustomBottomSheetDialogFragment().apply {
                show(supportFragmentManager, CustomBottomSheetDialogFragment.TAG)
            }
        }
    }

}

class CustomBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {

        const val TAG = "CustomBottomSheetDialogFragment"

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_modal_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firstButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "First Button Clicked", Toast.LENGTH_SHORT).show()
        }
        secondButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Second Button Clicked", Toast.LENGTH_SHORT).show()
        }
        thirdButton.setOnClickListener {
            //handle click event
            Toast.makeText(context, "Third Button Clicked", Toast.LENGTH_SHORT).show()
        }

    }
}