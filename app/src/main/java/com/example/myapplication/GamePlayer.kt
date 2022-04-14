package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.StepAdapter
import com.example.myapplication.drawers.ElementsDrawer
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.drawers.GridDrawer
import com.example.myapplication.drawers.PandaDrawer
import com.example.myapplication.drawers.StepDrawer
import com.example.myapplication.enums.Material
import com.example.myapplication.level.LevelItem
import com.example.myapplication.level.LevelSave
import com.example.myapplication.level.StepItem
import kotlinx.android.synthetic.main.game_layout.*

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE

class GamePlayer: AppCompatActivity(){ //, View.OnClickListener
    private lateinit var myPanda: ImageView
    private var editMode = false


    private val gridDrawer by lazy {
        GridDrawer(container)
    }

    private val elementDrawer by lazy {
        ElementsDrawer(container)
    }

    private  val pandaDrawer by lazy {
        PandaDrawer(container)
    }
    private val levelSave by lazy {
        LevelSave(this)
    }
    private val stepDrawer by lazy {
        StepDrawer(containerStep, this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        onKeyButton()
        editMode = intent.getBooleanExtra("editMode", false)
        elementDrawer.drawElementsList(levelSave.loadLevel())
        switchEditMode()
        myPanda = findViewById(R.id.myPanda)
        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
        container.setOnTouchListener { _, motionEvent ->
            elementDrawer.onTouchContainer(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }

    }

    private fun onKeyButton(){
        upView.setOnClickListener {
            stepDrawer.stepView(UP)
            pandaDrawer.move(myPanda, UP, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
        }
        downView.setOnClickListener {
            stepDrawer.stepView(DOWN)
            pandaDrawer.move(myPanda, DOWN, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
        }
        leftView.setOnClickListener {
            stepDrawer.stepView(LEFT)
            pandaDrawer.move(myPanda, LEFT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
        }
        rightView.setOnClickListener {
            stepDrawer.stepView(RIGHT)
            pandaDrawer.move(myPanda, RIGHT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
        }
        eatView.setOnClickListener {
            stepDrawer.stepView(EAT)
            pandaDrawer.move(myPanda, EAT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
        }

        deleteStep.setOnClickListener { stepDrawer.eraseStep() }

        functionMaterialView.setOnClickListener {  }
        functionView.setOnClickListener { }

        emptyView.setOnClickListener { elementDrawer.currentMaterial = Material.EMPTY }
        stoneView.setOnClickListener { elementDrawer.currentMaterial = Material.STONE }
        treeView.setOnClickListener { elementDrawer.currentMaterial = Material.TREE }
        bambooView.setOnClickListener { elementDrawer.currentMaterial = Material.BAMBOO }

        saveView.setOnClickListener {  levelSave.saveLevel(elementDrawer.elementsOnContainer)  }


    }


//    override fun onClick(p0: View?) {
//        when (p0?.id){
//            R.id.upView -> {
//                pandaDrawer.move(myPanda, UP, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
//                val step = StepItem(R.drawable.icon_up)
//                StepAdapter().addStep(step)
//
//            }
//            R.id.downView -> pandaDrawer.move(myPanda, DOWN, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
//            R.id.leftView -> pandaDrawer.move(myPanda, LEFT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
//            R.id.rightView -> pandaDrawer.move(myPanda, RIGHT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
//            R.id.eatView ->  pandaDrawer.move(myPanda, EAT, elementDrawer.elementsOnContainer,elementDrawer.elementsOnContainer)
//            R.id.functionMaterialView -> {
//                switchEditMode()
//            }
//            R.id.functionView -> {
//                switchEditMode()
//            }
//            R.id.saveView -> {
//                levelSave.saveLevel(elementDrawer.elementsOnContainer)
//                return
//            }
//        }
//    }

    private fun switchEditMode(){
        if (editMode){
            gridDrawer.drawGrid()
            materialContainer.visibility = View.VISIBLE
            stepContainer.visibility = View.GONE
        }else{
            gridDrawer.removeGrid()
            elementDrawer.currentMaterial = Material.NULL
            materialContainer.visibility = View.GONE
            stepContainer.visibility = View.VISIBLE
        }
        editMode = !editMode
    }
}